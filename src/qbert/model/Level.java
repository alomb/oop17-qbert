package qbert.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import qbert.model.characters.Character;
import qbert.model.characters.Coily;
import qbert.model.characters.Qbert;
import qbert.model.map.MapComponent;
import qbert.model.map.Mapper;
import qbert.model.states.CharacterState;
import qbert.model.states.DeathState;
import qbert.model.states.LandState;
import qbert.model.states.MoveState;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.model.utilities.Sprites;
import qbert.view.CharacterGraphicComponent;
import qbert.view.DownwardCGC;
import qbert.view.DownwardUpwardCGC;

public final class Level {

    private List<Character> gameCharacters;
    private Qbert qbert;
    private int points;
    private int lives;
    private int waitTimer = 0;
    private boolean timerCallback = false;
    private Spawner spawner;
    private MapComponent map;

    //Level settings
    private LevelSettings settings;
    private int level;
    private int round;

    public Level() {
        //Forse da spostare in classe Game
        this.points = 0;
        
        this.level = 1;
        this.round = 1;
        this.lives = 3;

        this.gameCharacters = new ArrayList<>();
        this.spawner = new Spawner(this);

        this.reset();
    }

    public void reset() {
        this.gameCharacters.forEach(c -> c.setDead(true));
        this.settings = new LevelSettings(spawner.getColorsNumber(), spawner.isReversible(), Sprites.blueBackground);
        this.map = new MapComponent(settings);
        this.qbert = (Qbert) this.spawner.spawnQbert();
    }

    public Tile getTile(final Position2D pos) {
        return this.map.getTile(pos);
    }
    
    public MapComponent getMap() {
        return this.map;
    }

    public BufferedImage getBackground() {
        return this.settings.getBackgroundImage();
    }

    
    public Character getQBert() {
        return this.qbert;
    }

    public List<Character> getEntities() {
        return this.gameCharacters;
    }

    public void spawn(Character entity) {
        //Temporary spawning position wild card
        entity.setCurrentPosition(new Position2D(-1, -1));
        this.gameCharacters.add(entity);
    }
    
    public void score(int points) {
        this.points += points;
    }

    public int getLevel() {
        return this.level;
    }

    public int getRound() {
        return this.round;
    }

    public int getPoints() {
        return this.points;
    }

    //Game?
    public void checkStatus() {
        int coloredTiles = 0;
        for (Tile t : this.map.getTileList()) {
            if (t.getColor() == this.settings.getColorNumber()) {
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
        if (level == 9 && round == 3) {
            System.exit(0);
        }
        if (this.round >= 1) {
            this.round = 1;
            this.level++;
        } else {
            this.round++;
        }
        /* SPAWNER */
        System.out.println("LEVEL" + this.level + "ROUND" + this.round); 
        this.spawner = new Spawner(this);
    }

    public void death() {
        this.waitTimer = 2000;
        this.timerCallback = true;
        this.qbert.setCurrentState(new DeathState(this.getQBert()));
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
                    this.gameCharacters.forEach(c -> c.setDead(true));
                } else {
                    System.exit(0);
                }
                this.timerCallback = false;
            }

            qbert.update(elapsed);
            Position2D qLogicalPos = qbert.getNextPosition();
            //Check if entity is just landed 
            if (qbert.getCurrentState() instanceof LandState) {
                //Checking if entity is outside the map
                if (Mapper.isOutOfMap(qLogicalPos)) {
                    qbert.setCurrentState(new MoveState.Fall(qbert));
                } else {
                    qbert.land(this.map.getTile(qLogicalPos));
                    qbert.setCurrentState(qbert.getStandingState());
                    this.checkStatus();
                }
            }

            if (qbert.isDead()) {
                this.death();
            }

            if (!updateEntities) {
                return;
            }

            spawner.update(elapsed);

            //Update Entities
            this.gameCharacters = this.gameCharacters.stream().peek(e -> {
                e.update(elapsed);
                Position2D logicPos = e.getNextPosition();
                //Check if entity is just landed 
                if (e.getCurrentState() instanceof LandState) {
                    //Checking if entity is outside the map
                    if (Mapper.isOutOfMap(logicPos)) {
                        e.setCurrentState(new MoveState.Fall(e));
                    } else {
                        e.land(this.map.getTile(logicPos));
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
                            e.collide(this);
                        }
                    }
                }
            }).filter(e -> !e.isDead()).collect(Collectors.toList());
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
