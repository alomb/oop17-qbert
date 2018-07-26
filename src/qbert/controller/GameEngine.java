package qbert.controller;

import qbert.model.Game;

import java.util.Optional;

import qbert.input.Command;
import qbert.model.utilities.Dimensions;
import qbert.view.Scene;

/**
 * The class containing the game loop and responsible of communications between View and Model.
 * It also provides a keyboard input management using Command pattern.
 */
public class GameEngine {

    private static final long PERIOD = 20;

    private boolean running;
    private boolean stopped;

    private Scene gameScene;
    private Game game;
    private Optional<Command> currentCommand;

    /**
     * Set currentCommand to empty.
     */
    public GameEngine() {
        this.currentCommand = Optional.empty();
    }

    /**
     * The method used to initialize the game loop. 
     */
    public void setup() {
        game = new Game();

        this.gameScene = new Scene(game.getLevel(), Dimensions.getWindowWidth(), Dimensions.getWindowHeight(), this);

        this.running = true;
        this.stopped = false;
        this.gameRender();
    }

    /**
     * The game loop based on cycles elapsed time that manage game and graphic updates.
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
     * @param current the time at the beginning of the current cycle
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
     * The method used to  execute a command's code if is there.
     */
    private void processInput() {
        if (this.currentCommand.isPresent()) {
            this.currentCommand.get().execute(this.game);
            this.currentCommand = Optional.empty();
        }
    }

    /**
     * @param elapsed the time passed since the last game cycle
     */
    private void gameUpdate(final float elapsed) {
        if (!this.stopped) {
            game.update(elapsed);
        }
    }

    /**
     * The method used to update graphics (View).
     */
    private void gameRender() {
        this.gameScene.render();
    }

    /**
     * @param command a class encapsulating code to execute
     */
    public void notifyCommand(final Command command) {
        this.currentCommand = Optional.of(command);
    }
}
