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
import qbert.model.mapping.Mapper;
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

    private Map<Integer, Map<Integer, Tile>> tiles;
    private List<Character> gameCharacters;
    private Qbert qbert;
    private int points;
    private int lives;
    private Spawner spawner;

    //Level settings
    private LevelSettings settings;
    private int level;
    private int round;
    
    //Debug
    public boolean immortality = false;

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
        this.settings = new LevelSettings(spawner.getColorsNumber(), spawner.isReversible(), Sprites.blueBackground);
        this.createLevelTiles(settings);
        this.spawnQbert();
    }

    public Tile getTile(final int x, final int y) {
        return tiles.get(x).get(y);
    }

    public BufferedImage getBackground() {
        return this.settings.getBackgroundImage();
    }

    private void createLevelTiles(LevelSettings settings) {
        tiles = new HashMap<>();
        Map<Integer, Tile> tmp = new HashMap<>();
        tmp.put(0, new Tile(0, 0, settings));
        tiles.put(0, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(1, 1, settings));
        tiles.put(1, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(2, 0, settings));
        tmp.put(2, new Tile(2, 2, settings));
        tiles.put(2, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(3, 1, settings));
        tmp.put(3, new Tile(3, 3, settings));
        tiles.put(3, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(4, 0, settings));
        tmp.put(2, new Tile(4, 2, settings));
        tmp.put(4, new Tile(4, 4, settings));
        tiles.put(4, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(5, 1, settings));
        tmp.put(3, new Tile(5, 3, settings));
        tmp.put(5, new Tile(5, 5, settings));
        tiles.put(5, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(6, 0, settings));
        tmp.put(2, new Tile(6, 2, settings));
        tmp.put(4, new Tile(6, 4, settings));
        tmp.put(6, new Tile(6, 6, settings));
        tiles.put(6, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(7, 1, settings));
        tmp.put(3, new Tile(7, 3, settings));
        tmp.put(5, new Tile(7, 5, settings));
        tiles.put(7, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(8, 0, settings));
        tmp.put(2, new Tile(8, 2, settings));
        tmp.put(4, new Tile(8, 4, settings));
        tiles.put(8, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(9, 1, settings));
        tmp.put(3, new Tile(9, 3, settings));
        tiles.put(9, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(10, 0, settings));
        tmp.put(2, new Tile(10, 2, settings));
        tiles.put(10, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(11, 1, settings));
        tiles.put(11, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(12, 0, settings));
        tiles.put(12, tmp);

        this.resetLevelTiles();
    }

    private void resetLevelTiles() {
        this.getTiles().stream().forEach(t -> {
            t.resetColor();
        });
    }

    public Character getQBert() {
        return this.qbert;
    }

    public List<Character> getEntities() {
        return this.gameCharacters;
    }

    public List<Tile> getTiles() {
        return this.tiles
                .entrySet()
                .stream()
                .flatMap((Map.Entry<Integer, Map<Integer, Tile>> me) -> me.getValue()
                        .entrySet()
                        .stream()
                        .map(Map.Entry::getValue))
                .collect(Collectors.toList());
    }

    public void spawn(Character entity) {
        //Temporary spawning position wild card
        entity.setCurrentPosition(new Position2D(-1, -1));
        this.gameCharacters.add(entity);
    }

    public void spawnQbert(Qbert qbert) {
        this.qbert = qbert;
    }

    //Test qbert spawn
    public void spawnQbert() {
        CharacterGraphicComponent qg = new DownwardUpwardCGC(Sprites.qbertFrontStanding, Sprites.qbertFrontMoving, Sprites.qbertBackStanding, Sprites.qbertBackMoving, 
                new Position2D(Dimensions.windowWidth / 2 - Sprites.qbertFrontMoving.getWidth() / 2, Dimensions.backgroundY - Sprites.qbertFrontStanding.getHeight())
        );
        Qbert q = new Qbert(new Position2D(6, 6), 0.35f, qg);
        this.spawnQbert(q);
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
        for (Tile t : this.getTiles()) {
            if (t.getColor() == this.settings.getColorNumber()) {
                coloredTiles++;
            }
        }

        if (coloredTiles == this.getTiles().stream().count()) {
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
        if (this.lives > 1) {
            this.lives--;
            this.spawnQbert();
        } else {
            System.exit(0);
        }
    }

    public void update(final float elapsed) {
        spawner.update(elapsed);

        qbert.update(elapsed);
        Position2D qLogicalPos = qbert.getCurrentPosition();
        //Check if entity is just landed 
        if (qbert.getCurrentState() instanceof LandState) {
            //Checking if entity is outside the map
            if (Mapper.isOutOfMap(qLogicalPos)) {
                qbert.setCurrentState(new MoveState.Fall(qbert));
            } else {
                qbert.land(this.getTile((int) qLogicalPos.getX(), (int) qLogicalPos.getY()));
                qbert.setCurrentState(qbert.getStandingState());
                this.checkStatus();
            }
        }

        if (qbert.isDead()) {
            this.death();
        }

        //Update Entities
        this.gameCharacters = this.gameCharacters.stream().peek(e -> {
            e.update(elapsed);
            Position2D logicPos = e.getCurrentPosition();
            //Check if entity is just landed 
            if (e.getCurrentState() instanceof LandState) {
                //Checking if entity is outside the map
                if (Mapper.isOutOfMap(logicPos)) {
                    e.setCurrentState(new MoveState.Fall(e));
                } else {
                    e.land(this.getTile((int) logicPos.getX(), (int) logicPos.getY()));
                    e.setCurrentState(e.getStandingState());
                }
            }

            if (e.isDead()) {
                //Notify Spawner
                this.spawner.death(e);
            }

            //Check if entity is colliding with QBert
            if (qbert.getCurrentPosition().equals(e.getCurrentPosition()) && !e.isMoving() && !e.isMoving()
                    || qbert.getCurrentPosition().equals(e.getNextPosition()) && qbert.getNextPosition().equals(e.getCurrentPosition())) {
                
                if (!immortality) //Debug check
                    e.collide(this);
            }
        }).filter(e -> !e.isDead()).collect(Collectors.toList());
    }
    
    //Debug functions
    
    public void gainLife() {
        lives++;
    }
    
    public void toggleImmortality() {
        this.immortality = !this.immortality;
    }
}
