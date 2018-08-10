package qbert.model;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import qbert.controller.Sounds;
import qbert.model.characters.Character;
import qbert.model.characters.Coily;
import qbert.model.characters.Player;
import qbert.model.characters.states.DeathState;
import qbert.model.characters.states.FallState;
import qbert.model.characters.states.LandState;
import qbert.model.characters.states.QbertOnDiskState;
import qbert.model.characters.states.QbertStandingState;
import qbert.model.components.MapComponent;
import qbert.model.components.PointComponent;
import qbert.model.components.TimerComponent;
import qbert.model.models.Game;
import qbert.model.spawner.Spawner;
import qbert.model.spawner.SpawnerImpl;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.view.GenericGC;
import qbert.view.GraphicComponent;
import qbert.view.GraphicComponentImpl;
import qbert.view.Renderable;
import qbert.view.RenderableObject;

public final class Level {

    private Player qbert;
    private int lives;
    private boolean timerCallback = false;
    private Spawner spawner;
    private PointComponent points;
    private MapComponent map;
    private TimerComponent timer;
    private Renderable background;

    private LevelSettings settings;

    private int waitTimer = 0;
    private Game gameObserver;

    public Level(LevelSettings levelSettings, final int lives, final int score) {
        this.lives = lives;

        this.settings = levelSettings;
        this.spawner = new SpawnerImpl(levelSettings.getMapInfo(), levelSettings.getQBertSpeed());

        this.points = new PointComponent();
        this.points.score(score);

        this.map = new MapComponent(settings);

        this.qbert = this.spawner.spawnQbert();
        this.timer = new TimerComponent(qbert, spawner, points, map);

        GraphicComponent backgroundGC = new GenericGC(this.settings.getBackgroundImage(), new Position2D(Dimensions.getBackgroundX(), Dimensions.getBackgroundY()));
        this.background = new RenderableObject(backgroundGC);
    }

    public void addObserver(Game gameObserver) {
        this.gameObserver = gameObserver;
    }

    public void notifyEndLevel() {
        this.gameObserver.changeRound();
    }

    public void resetDisk() {
        this.spawner.getGameCharacters().forEach(c -> {
            if (!(c instanceof Coily)) {
                c.setCurrentState(new DeathState(c)); /////////////////////////
            }
        });
    }

    public MapComponent getMap() {
        return this.map;
    }

    public BufferedImage getBackground() {
        return this.settings.getBackgroundImage();
    }

    public Player getQBert() {
        return this.qbert;
    }

    public List<Character> getEntities() {
        return this.spawner.getGameCharacters();
    }

    public int getPoints() {
        return this.points.getPoints();
    }

    public void checkStatus() {
        int coloredTiles = 0;
        for (Tile t : this.map.getTileList()) {
            if (t.getColor() == this.settings.getColorsNumber()) {
                coloredTiles++;
            }
        }

        if (coloredTiles == this.map.getTileList().stream().count()) {
            this.changeRound();
        }
    }

    public int getLives() {
        return this.lives;
    }

    public void changeRound() {
        this.points.score(this.settings.getRoundScore());
        this.points.score(PointComponent.UNUSED_DISK_SCORE * map.getDiskList().size());

        this.spawner.getGameCharacters().forEach(c -> c.setCurrentState(new DeathState(c)));
        this.notifyEndLevel();
    }

    public void death() {
        this.waitTimer = 2000;
        this.timerCallback = true;
        this.qbert.setCurrentState(new DeathState(this.getQBert()));
    }

    public List<Renderable> getRenderables() {
        return Stream.concat(Stream.concat(Stream.of(this.getTargetColor()), Stream.of(this.background)), Stream.concat(Stream.concat(map.getTileList().stream(), map.getDiskList().stream()), Stream.concat(Stream.of(this.qbert), this.spawner.getGameCharacters().stream()))).collect(Collectors.toList());
    }

    public void update(final float elapsed) {
        if (!update) {
            return;
        }

        if (this.waitTimer <= 0) {
            if (this.timerCallback) {
                this.lives--;
                spawner.respawnQbert();
                this.spawner.getGameCharacters().forEach(c -> c.setCurrentState(new DeathState(c)));
                this.timerCallback = false;
            }

            Position2D qLogicPos = qbert.getNextPosition();

            //Check if entity is just landed 
            if (qbert.getCurrentState() instanceof LandState) {
                //Checking if entity is outside the map
                if (this.map.isOnVoid(qLogicPos)) {
                    qbert.setCurrentState(new FallState(qbert));
                } else {
                    qbert.land(this.map, this.points);
                    qbert.setCurrentState(qbert.getStandingState());
                    this.checkStatus();
                }
                /* se Qbert e' sul disco pulisco la mappa, tranne Coily */
                if (this.map.checkForDisk(qbert)) {
                    this.resetDisk();
                    Sounds.playSound("RidingADisk.wav");
                } else if (qbert.getCurrentState() instanceof FallState) {
                    Sounds.playSound("QBertGoesOverTheEdge.wav");
                }
            }

            timer.update(elapsed);

            if (qbert.isDead()) {
                this.death();
            }

        } else {
            this.waitTimer -= elapsed;
        }
    }

    private Renderable getTargetColor() {
        final Optional<Integer> i = settings.getColorMap().keySet().stream().collect(Collectors.toList()).stream().max((o1, o2) -> o1.compareTo(o2));
        if (i.isPresent()) {
            final GraphicComponent gc = new GraphicComponentImpl(settings.getColorMap().get(i.get()), new Position2D(Math.round(Dimensions.getWindowWidth() / 9f), Math.round(Dimensions.getWindowHeight() / 4f)));
            final Renderable ro = new RenderableObject(gc);
            return ro;
        }

        return null;
    }

    //Debug options

    private boolean update = true;
    private boolean updateEntities = true;
    private boolean immortality = false;

    /**
     * Temporary debug function that adds 1 life.
     */
    public void gainLife() {
        lives++;
    }

    /**
     * Temporary debug function that gives immortality to Qbert.
     */
    public void toggleImmortality() {
        this.immortality = !this.immortality;
    }

    /**
     * Temporary debug function that freezes time.
     */
    public void toggleTime() {
        this.update = !this.update;
    }

    /**
     * Temporary debug function that freezes entities.
     */
    public void toggleEntities() {
        this.updateEntities = !this.updateEntities;
    }
}
