package qbert.model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import qbert.model.states.DeathState;
import qbert.model.states.MoveState;
import qbert.model.utilities.Position2D;
import qbert.view.CharacterGraphicComponent;
import qbert.view.CharacterGraphicComponentImpl;

public class Level {

    private Map<Integer, Map<Integer, Tile>> tiles;
    private List<Character> gameCharacters;
    private int mapHeight = 7;
    private int points;
    private int lives;

    //Level settings
    private int level;
    private int round;
    private int colorsNumber;
    private boolean reversableColors;

    public Level() {
        
        this.createLevelTiles();

        this.level = 1;
        this.round = 1;
        this.lives = 3;

        //Forse da spostare in classe Game
        this.points = 0;

        this.gameCharacters = new ArrayList<>();
        this.reset();

        /* Temp RedBall Spawn */
        BufferedImage res = null;
        final URL spriteUrl = this.getClass().getResource("/redBallStanding.png");
        try {
            res = ImageIO.read(spriteUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CharacterGraphicComponent g = new CharacterGraphicComponentImpl(res, new Position2D(719, 227));
        Character ball = new RedBall(new Position2D(6, 6), 0.5f, g, 1);
        this.spawn(ball);
    }

    public void reset() {
        //Info da importare da classe esterna
        this.colorsNumber = 2;
        this.reversableColors = true;

        this.resetLevelTiles();
    }

    public Tile getTile(final int x, final int y) {
        return tiles.get(x).get(y);
    }

    private void createLevelTiles() {
        tiles = new HashMap<>();

        //TODO: Utilizzare un metodo di creazione dei tile migliore
        for (int i = 1; i <= this.mapHeight; i++) {
            Map<Integer, Tile> tmpMap = new HashMap<>();
            for (int j = 1; j <= this.mapHeight; j++) {
                if (j <= i) {
                    tmpMap.put(j, new Tile(j, i));
                }
                tiles.put(i, tmpMap);
            }
        }
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
    public void observeGameStatus() {
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

    public void update(float elapsed) {
        System.out.println(this.gameCharacters.size());
        this.gameCharacters = this.gameCharacters.stream().peek(e -> {
            e.update(elapsed);

            Position2D logicalPos = e.getCurrentPosition();
            //Checking if entity is outside the map
            if (logicalPos.getY() < 0 /* || logicalPos.getX() < 0|| logicalPos.getX() > ? */) {
                if (!(e.getCurrentState() instanceof MoveState.Fall) && !(e.getCurrentState() instanceof DeathState)) {
                    System.out.println("Setting fall");
                    e.setCurrentState(new MoveState.Fall(e));
                }
            }
        }).filter(e -> !(e.getCurrentState() instanceof DeathState)).collect(Collectors.toList());
    }
}
