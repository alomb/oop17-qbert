package qbert.controller;

import qbert.model.Game;
import qbert.model.Level;
import qbert.model.mapping.Mapper;
import qbert.view.Scene;

/**
 *
 */
public class GameEngine {

    private static final long PERIOD = 20;

    private boolean running;
    private boolean stopped;

    private Scene gameScene;
    private Level gameLevel;

    /**
     * 
     */
    public GameEngine() {

    }

    /**
     * 
     */
    public void setup() {
        //TODO: Remove
        int windowWidth = 1500;
        int windowHeight = 1000;

        Game game = new Game();
        Mapper mapper = new Mapper(game.getScreenWidth(), game.getScreenHeight(), windowWidth, windowHeight);
        Level gameLevel = new Level();
        this.gameScene = new Scene(gameLevel, mapper, windowWidth, windowHeight);

        this.running = true;
        this.stopped = false;
        this.gameRender();
    }

    /**
     * 
     */
    public void mainLoop() {
        long lastTime = System.currentTimeMillis(); 
        while (this.running) {
            final long current = System.currentTimeMillis();
            final int elapsed = (int) (current - lastTime);
            this.processInput();
            this.gameUpdate(elapsed);
            this.gameRender();
            this.waitForNextFrame(current);
            lastTime = current;
        }
    }

    /**
     * @param current
     */
    private void waitForNextFrame(final long current) {
        final long dt = System.currentTimeMillis() - current;
        if (dt < GameEngine.PERIOD) {
            try {
                Thread.sleep(GameEngine.PERIOD - dt);
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 
     */
    private void processInput() {

    }

    /**
     * @param elapsed
     */
    private void gameUpdate(final float elapsed) {
        if (!this.stopped) {

        }
    }

    /**
     * 
     */
    private void gameRender() {
        this.gameScene.render();
    }
}
