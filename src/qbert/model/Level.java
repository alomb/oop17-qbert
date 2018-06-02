package qbert.model;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import qbert.model.characters.Character;
import qbert.model.characters.Player;
import qbert.model.characters.SamAndSlick;
import qbert.model.components.MapComponent;
import qbert.model.components.PointComponent;
import qbert.model.components.TimerComponent;
import qbert.model.states.DeathState;
import qbert.model.states.LandState;
import qbert.model.states.MoveState;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.model.utilities.Sprites;
import qbert.view.BaseTileGC;
import qbert.view.GenericGC;
import qbert.view.GraphicComponent;
import qbert.view.Renderable;
import qbert.view.RenderableObject;
import qbert.view.characters.CharacterGC;
import qbert.view.characters.CharacterGCImpl;
import qbert.view.characters.DownwardCharacterGCImpl;

public final class Level {

    private Player qbert;
    private int lives;
    private int waitTimer = 0;
    private boolean timerCallback = false;
    private Spawner spawner;
    private PointComponent points;
    private MapComponent map;
    private TimerComponent timer;
    private Renderable background;

    //Level settings
    private LevelSettings settings;
    private int levelNumber;
    private int roundNumber;

    public Level() {
        //Forse da spostare in classe Game
        
        this.levelNumber = 1;
        this.roundNumber = 1;
        this.lives = 3;

        this.spawner = new Spawner(this.getLevelNumber(), this.getRoundNumber());

        this.reset();
    }

    public void reset() {
        this.spawner.getGameCharacters().forEach(c -> c.setCurrentState(new DeathState(c)));
        
        this.settings = this.spawner.getLevelSettings();
        this.map = new MapComponent(settings);
        this.points = new PointComponent();
        this.timer = new TimerComponent();
        this.qbert = this.spawner.spawnQbert();
        
        GraphicComponent backgroundGC = new GenericGC(this.settings.getBackgroundImage(), new Position2D(Dimensions.backgroundX, Dimensions.backgroundY));
        this.background = new RenderableObject(backgroundGC);
        
        //Slick and Sam Test Implementation (Crashes when something dies)
        CharacterGC charG = new DownwardCharacterGCImpl(Sprites.greenBallStanding, Sprites.greenBallMoving, Dimensions.spawningPointRight);
        SamAndSlick sam = new SamAndSlick(new Position2D(7, 5), 0.25f, charG, 1000);
        //this.spawn(sam);
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
        return this.spawner.getGameCharacters(); /////
    }
    
    public int getLevelNumber() {
        return this.levelNumber;
    }

    public int getRoundNumber() {
        return this.roundNumber;
    }

    public int getPoints() {
        return this.points.getPoints();
    }

    //Game?
    public void checkStatus() {
        int coloredTiles = 0;
        for (Tile t : this.map.getTileList()) {
            if (t.getColor() == this.settings.getColorsNumber()) {
                coloredTiles++;
            }
        }

        if (coloredTiles == this.map.getTileList().stream().count()) {
            this.changeRound();
            this.reset();
        }
    }

    public int getLives() {
        return this.lives;
    }

    public void changeRound() {
        if (levelNumber == 9 && roundNumber == 3) {
            System.exit(0);
        }
        if (this.roundNumber >= 1) {
            this.roundNumber = 1;
            this.levelNumber++;
        } else {
            this.roundNumber++;
        }
        
        this.points.gain(points.ROUND_BONUS_SCORE);
        /* SPAWNER */
        this.spawner = new Spawner(this.getLevelNumber(), this.getRoundNumber());
        this.settings = this.spawner.getLevelSettings();
    }

    public void death() {
        this.waitTimer = 2000;
        this.timerCallback = true;
        this.qbert.setCurrentState(new DeathState(this.getQBert()));
    }

    public List<Renderable> getRenderables() {
        return Stream.concat(Stream.of(this.background), Stream.concat(Stream.concat(map.getTileList().stream(), map.getDiskList().stream()), Stream.concat(Stream.of(this.qbert), this.spawner.getGameCharacters().stream()))).collect(Collectors.toList());
    }
    
    public void update(final float elapsed) {
        if (!update) {
            return;
        }

        if (this.waitTimer <= 0) {
            if (this.timerCallback) {
                if (this.lives > 1) {
                    this.lives--;
                    spawner.respawnQbert();
                    this.spawner.getGameCharacters().forEach(c -> c.setCurrentState(new DeathState(c)));
                } else {
                    System.exit(0);
                }
                this.timerCallback = false;
            }

            spawner.update(elapsed);

            this.map.getDiskList().forEach(d -> d.update(elapsed));

            if (updateEntities) {
                //Update Entities
                /* this.spawner.getGameCharacters() = */
                this.spawner.updateGameCharacters(this.spawner.getGameCharacters().stream().peek(e -> {
                    e.update(elapsed);
                    Position2D logicPos = e.getNextPosition();
                    //Check if entity is just landed 
                    if (e.getCurrentState() instanceof LandState) {
                        //Checking if entity is outside the map
                        if (this.map.isOnVoid(logicPos)) {
                            e.setCurrentState(new MoveState.Fall(e));
                            this.points.gain(points.COILY_FALL_SCORE);
                        } else {
                            e.land(this.map, this.points);
                            e.setCurrentState(e.getStandingState());
                        }
                    }
    
                    if (e.isDead()) {
                        //Notify Spawner
                        this.spawner.death(e);
                    } else {
                        //Check if entity is colliding with QBert
                        if (qbert.getCurrentPosition().equals(e.getCurrentPosition()) && !qbert.isMoving() && !e.isMoving()
                                || qbert.getCurrentPosition().equals(e.getNextPosition()) && qbert.getNextPosition().equals(e.getCurrentPosition())) {
    
                            if (!immortality) { 
                                //Debug check
                                e.collide(this.getQBert(), this.points, this.timer);
                            }
                        }
                    }
                }).filter(e -> !e.isDead()).collect(Collectors.toList())); /* togliere parentesi se modifico */
            }


            qbert.update(elapsed);
            Position2D qLogicPos = qbert.getNextPosition();

            if (qbert.isDead()) {
                this.death();
            }
            
            //Check if entity is just landed 
            if (qbert.getCurrentState() instanceof LandState) {
                //Checking if entity is outside the map
                if (this.map.isOnVoid(qLogicPos)) {
                    qbert.setCurrentState(new MoveState.Fall(qbert));
                } else {
                    qbert.land(this.map, this.points);
                    qbert.setCurrentState(qbert.getStandingState());
                    this.checkStatus();
                }
                this.map.checkForDisk(qbert);
            }
        } else {
            this.waitTimer -= elapsed;
        }
    }
    
    //Debug options
    
    public boolean update = true;
    public boolean updateEntities = true;
    public boolean immortality = false;
    
    public void gainLife() {
        lives++;
    }
    
    public void toggleImmortality() {
        this.immortality = !this.immortality;
    }
    
    public void toggleTime() {
        this.update = !this.update;
    }
    
    public void toggleEntities() {
        this.updateEntities = !this.updateEntities;
    }
}
