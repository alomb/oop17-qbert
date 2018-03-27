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
import qbert.view.CharacterGraphicComponentImpl;

public final class Level {

    private Map<Integer, Map<Integer, Tile>> tiles;
    private List<Character> gameCharacters;
    private Qbert qbert;
    private int points;
    private int lives;
    private Spawner spawner;

    //Level settings
    private BufferedImage background;
    private int level;
    private int round;
    private int colorsNumber;
    private boolean reversableColors;
    
    private int totalTime = 0;
    private boolean spawned = false;

    public Level() {
        this.createLevelTiles();

        this.level = 1;
        this.round = 1;
        this.lives = 3;

        //Forse da spostare in classe Game
        this.points = 0;

        this.gameCharacters = new ArrayList<>();
        this.reset();
    }

    public void reset() {
        //Info da importare da classe esterna
        this.colorsNumber = 2;
        this.reversableColors = true;
        this.background = Sprites.blueBackground;

        this.resetLevelTiles();
    }

    public Tile getTile(final int x, final int y) {
        return tiles.get(x).get(y);
    }
    
    public BufferedImage getBackground() {
        return this.background;
    }

    private void createLevelTiles() {
        tiles = new HashMap<>();
        Map<Integer, Tile> tmp = new HashMap<>();
        tmp.put(0, new Tile(0, 0));
        tiles.put(0, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(1, 1));
        tiles.put(1, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(2, 0));
        tmp.put(2, new Tile(2, 2));
        tiles.put(2, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(3, 1));
        tmp.put(3, new Tile(3, 3));
        tiles.put(3, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(4, 0));
        tmp.put(2, new Tile(4, 2));
        tmp.put(4, new Tile(4, 4));
        tiles.put(4, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(5, 1));
        tmp.put(3, new Tile(5, 3));
        tmp.put(5, new Tile(5, 5));
        tiles.put(5, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(6, 0));
        tmp.put(2, new Tile(6, 2));
        tmp.put(4, new Tile(6, 4));
        tmp.put(6, new Tile(6, 6));
        tiles.put(6, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(7, 1));
        tmp.put(3, new Tile(7, 3));
        tmp.put(5, new Tile(7, 5));
        tiles.put(7, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(8, 0));
        tmp.put(2, new Tile(8, 2));
        tmp.put(4, new Tile(8, 4));
        tiles.put(8, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(9, 1));
        tmp.put(3, new Tile(9, 3));
        tiles.put(9, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(10, 0));
        tmp.put(2, new Tile(10, 2));
        tiles.put(10, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(11, 1));
        tiles.put(11, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(12, 0));
        tiles.put(12, tmp);
    }

    private void resetLevelTiles() {
        this.getTiles().stream().forEach(t -> {
            t.resetColor();
        });
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

    public int getStage() {
        return ((this.level - 1) * 3) + this.round;
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

    public boolean step(Tile t) {
        if (t.getColor() < this.colorsNumber) {
            t.incrementColor();
            return true;
        } else {
            if (this.reversableColors) {
                t.resetColor();
            }
            return false;
        }
    }

    //Game?
    public void checkStatus() {
        int coloredTiles = 0;
        for (Tile t : this.getTiles()) {
            if (t.getColor() == this.colorsNumber) {
                coloredTiles++;
            }
        }

        if (coloredTiles == this.getTiles().stream().count()) {
            this.changeStage();
            this.reset();
        }
    }

    public int getLives() {
        return this.lives;
    }

    public void changeStage() {
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
        this.totalTime += elapsed;
        if (this.totalTime > 1000 && !this.spawned) {
            this.spawned = true;

            /* Temp RedBall Spawn */
            CharacterGraphicComponent g = new CharacterGraphicComponentImpl(Sprites.RedBallStanding, Dimensions.spawingPointLeft);
            Character ball = new RedBall(new Position2D(5, 5), 0.35f, g, 1000);
            this.spawn(ball);
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
                    this.step(this.getTile((int) logicalPos.getX(), (int) logicalPos.getY()));
                    e.setCurrentState(e.getStandingState());
                }
            }
            
            if (e.getCurrentState() instanceof DeathState) {
                //Notify Spawner
                this.spawned = false;
            }
        }).filter(e -> !(e.getCurrentState() instanceof DeathState)).collect(Collectors.toList());
    }
}
