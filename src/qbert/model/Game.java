package qbert.model;

import java.util.List;

import qbert.controller.Controller;
import qbert.model.characters.Player;
import qbert.model.states.MoveState;
import qbert.view.Renderable;

public class Game implements Model {

    private static final int LEVELSNUMBER = 9;
    private static final int ROUNDSNUMBER = 4;

    private Level gameLevel;
    private Controller controller;

    private int levelNumber;
    private int roundNumber;
    private int lives;
    private int score;

    public Game(Controller controller) {
        this.controller = controller;
        this.levelNumber = 1;
        this.roundNumber = 1;
        this.lives = 3;
        this.score = 0;
        createNewLevel();
    }

    public void initializeLevel() {
        this.gameLevel.addObserver(this);
    }

    public Level getLevel() {
        return gameLevel;
    }

    @Override
    public void update(final float elapsed) {
        gameLevel.update(elapsed);
    }

    public final void createNewLevel() {
        final LevelSettings ls = controller.getLevelSettings(levelNumber, roundNumber);
        this.gameLevel = new Level(ls, lives, score);
        this.gameLevel.addObserver(this);
    }

    public void changeRound() {
        if (levelNumber == LEVELSNUMBER && roundNumber == ROUNDSNUMBER) {
            System.exit(0);
        }
        if (this.roundNumber >= ROUNDSNUMBER) {
            this.roundNumber = 1;
            this.levelNumber++;
        } else {
            this.roundNumber++;
        }

        this.lives = gameLevel.getLives();
        this.score = gameLevel.getPoints();
        this.createNewLevel();
    }

    public List<Renderable> getRenderables() {
        return this.gameLevel.getRenderables();
    }

    public int getPoints() {
        return this.gameLevel.getPoints();
    }

    public int getLevelNumber() {
        return this.levelNumber;
    }

    public int getRoundNumber() {
        return this.roundNumber;
    }

    public int getLives() {
        return this.gameLevel.getLives();
    }

    @Override
    public final void moveDown() {
        final Player qbert = this.getLevel().getQBert();
        if (!qbert.isMoving() && !qbert.isDead()) {
            qbert.setCurrentState(new MoveState.DownLeft(qbert));
        }
    }

    @Override
    public final void moveLeft() {
        final Player qbert = this.getLevel().getQBert();
        if (!qbert.isMoving() && !qbert.isDead()) {
            qbert.setCurrentState(new MoveState.UpLeft(qbert));
        }
    }

    @Override
    public final void moveRight() {
        final Player qbert = this.getLevel().getQBert();
        if (!qbert.isMoving() && !qbert.isDead()) {
            qbert.setCurrentState(new MoveState.DownRight(qbert));
        }
    }

    @Override
    public final void moveUp() {
        final Player qbert = this.getLevel().getQBert();
        if (!qbert.isMoving() && !qbert.isDead()) {
            qbert.setCurrentState(new MoveState.UpRight(qbert));
        }
    }

    @Override
    public void confirm() {

    }
}
