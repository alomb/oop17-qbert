package qbert.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import qbert.model.states.DeathState;
import qbert.model.states.LandState;
import qbert.model.states.MoveState;
import qbert.model.utilities.Position2D;
import qbert.view.CharacterGraphicComponent;
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

    public Level() {
        this.level = 1;
        this.round = 1;
        this.lives = 3;

        //Forse da spostare in classe Game
        this.points = 0;

        this.gameCharacters = new ArrayList<>();
        this.spawner = new Spawner(this);

        //Test qbert spawn
        CharacterGraphicComponent qg = new DownwardUpwardCGC(Sprites.qbertFrontMoving, Sprites.qbertFrontMoving, Sprites.qbertFrontMoving, Sprites.qbertFrontMoving, 
                new Position2D(Dimensions.windowWidth / 2 - Sprites.qbertFrontMoving.getWidth() / 2, Dimensions.backgroundY - Dimensions.tileHeight / 2)
        );
        Qbert q = new Qbert(new Position2D(6, 6), 0.35f, qg);
        this.spawnQbert(q);

        this.reset();
    }

    public void reset() {
        this.settings = new LevelSettings(spawner.getColorsNumber(), spawner.isReverable(), Sprites.blueBackground);

        this.createLevelTiles(settings);
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
        this.gameCharacters.add(entity);
    }
    
    public void spawnQbert(Qbert qbert) {
        this.qbert = qbert;
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
        if (this.round > 2) {
            this.round = 1;
            this.level++;
        } else {
            this.round++;
        }
    }

    public void death() {
        if (this.lives > 1) {
            this.lives--;
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
            if (qLogicalPos.getY() < 0 || qLogicalPos.getX() + qLogicalPos.getY() == 14 || qLogicalPos.getY() - qLogicalPos.getX() == 2) {
                qbert.setCurrentState(new MoveState.Fall(qbert));
            } else {
                qbert.land(this.getTile((int) qLogicalPos.getX(), (int) qLogicalPos.getY()));
                qbert.setCurrentState(qbert.getStandingState());
            }
        }

        //Update Entities
        this.gameCharacters = this.gameCharacters.stream().peek(e -> {
            e.update(elapsed);
            Position2D logicalPos = e.getCurrentPosition();
            //Check if entity is just landed 
            if (e.getCurrentState() instanceof LandState) {
                //Checking if entity is outside the map
                if (logicalPos.getY() < 0 || logicalPos.getX() + logicalPos.getY() == 14 || logicalPos.getY() - logicalPos.getX() == 2) {
                    e.setCurrentState(new MoveState.Fall(e));
                } else {
                    e.land(this.getTile((int) logicalPos.getX(), (int) logicalPos.getY()));
                    e.setCurrentState(e.getStandingState());
                }
            }

            if (e.getCurrentState() instanceof DeathState) {
                //Notify Spawner
            }
        }).filter(e -> !(e.getCurrentState() instanceof DeathState)).collect(Collectors.toList());
    }
}
