package qbert.model;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import qbert.controller.Controller;
import qbert.model.characters.Character;
import qbert.model.characters.Coily;
import qbert.model.characters.Player;
import qbert.model.characters.states.DeathState;
import qbert.model.characters.states.FallState;
import qbert.model.characters.states.LandState;
import qbert.model.components.MapComponent;
import qbert.model.components.PointComponent;
import qbert.model.components.TimerComponent;
import qbert.model.models.Game;
import qbert.model.spawner.Spawner;
import qbert.model.spawner.SpawnerImpl;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.model.components.graphics.GenericGC;
import qbert.model.components.graphics.GraphicComponent;
import qbert.model.components.graphics.GraphicComponentImpl;
import qbert.model.components.graphics.Renderable;
import qbert.model.components.graphics.RenderableObject;

public final class Level {

    private Player qbert;
    private int lives;
    private Spawner spawner;
    private PointComponent points;
    private MapComponent map;
    private TimerComponent timer;
    private Renderable background;

    private LevelSettings settings;
    private Controller controller;

    private Game gameObserver;

    public Level(LevelSettings levelSettings, final int lives, final int score, final Controller controller) {
        this.lives = lives;

        this.settings = levelSettings;
        this.controller = controller;
        this.spawner = new SpawnerImpl(levelSettings.getMapInfo(), levelSettings.getQBertSpeed(), controller);

        this.points = new PointComponent();
        this.points.score(score);

        this.map = new MapComponent(settings);

        this.qbert = this.spawner.spawnQbert();
        this.timer = new TimerComponent(qbert, spawner, points, map, this);

        GraphicComponent backgroundGC = new GenericGC(this.settings.getBackgroundImage(), new Position2D(Dimensions.getBackgroundX(), Dimensions.getBackgroundY()));
        this.background = new RenderableObject(backgroundGC);
    }

    public void addObserver(Game gameObserver) {
        this.gameObserver = gameObserver;
    }

    public void notifyEndLevel() {
        this.gameObserver.changeRound();
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

        //TODO: Start of animation
        timer.freezeEverything(() -> {
            //TODO: End of animation
            this.notifyEndLevel();
        }, TimerComponent.ROUND_WAIT_TIME);
    }

    public List<Renderable> getRenderables() {
        return Stream.concat(Stream.concat(Stream.of(this.getTargetColor()), Stream.of(this.background)), Stream.concat(Stream.concat(map.getTileList().stream(), map.getDiskList().stream()), Stream.concat(Stream.of(this.qbert), this.spawner.getGameCharacters().stream()))).collect(Collectors.toList());
    }

    public void update(final float elapsed) {
        timer.update(elapsed);
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
}
