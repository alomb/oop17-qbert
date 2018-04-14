package qbert.controller;

import qbert.model.Game;

import java.util.Optional;

import qbert.input.Command;
import qbert.model.Dimensions;
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
    private Game game;
    private Optional<Command> currentCommand;

    /**
     * 
     */
    public GameEngine() {
        this.currentCommand = Optional.empty();
    }

    /**
     * @throws Exception 
     * 
     */
    public void setup() throws Exception {
        game = new Game();
        Mapper mapper = new Mapper(Dimensions.screenWidth, Dimensions.screenHeight, Dimensions.windowWidth, Dimensions.windowHeight);

        this.gameScene = new Scene(game.getLevel(), mapper, Dimensions.windowWidth, Dimensions.windowHeight, this);

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
        if (this.currentCommand.isPresent()) {
            this.currentCommand.get().execute(this.game);
            this.currentCommand = Optional.empty();
        }
    }

    /**
     * @param elapsed
     */
    private void gameUpdate(final float elapsed) {
        if (!this.stopped) {
            game.update(elapsed);
        }
    }

    /**
     * 
     */
    private void gameRender() {
        this.gameScene.render();
    }

    /**
     * @param command
     */
    public void notifyCommand(final Command command) {
        this.currentCommand = Optional.of(command);
    }
}
