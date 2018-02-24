package qbert.controller;

import qbert.view.Scene;

/**
 *
 */
public class GameEngine {

    private static final long PERIOD = 20;

    private boolean running;
    private boolean stopped;

    /**
     * 
     */
    public GameEngine() {

    }

    /**
     * 
     */
    public void setup() {
        Scene view = new Scene(800, 800);
        view.render();

        this.running = true;
        this.stopped = false;
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

    }
}
