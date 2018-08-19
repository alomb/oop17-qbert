package qbert.controller;

import qbert.controller.input.Command;
import qbert.model.models.Model;

import java.util.Optional;

import qbert.view.View;

/**
 * The implementation of {@link Loop} that could be used as an engine to update an associated
 * {@link Model} and render the {@link View}. It also store inputs from {@link Controller} 
 * (from {@link View}) and pass them to current {@link Model}.
 */
public class GameEngine implements Loop {

    private static final long PERIOD = 20;

    private boolean running;
    private boolean stopped;

    private final View gameScene;
    private Model game;
    private Optional<Command> currentCommand;

    /**
     * @param scene the {@link ViewImpl} to render
     */
    public GameEngine(final View scene) {
        this.currentCommand = Optional.empty();
        this.gameScene = scene;
    }

    /**
     * The method used to initialize the game loop. 
     */
    @Override
    public final void setup(final Model game) {
        this.game = game;

        this.running = true;
        this.stopped = false;
    }

    /**
     * The game loop based on cycles elapsed time that manage game and graphic updates.
     */
    @Override
    public final void mainLoop() {
        if (this.game != null) {
            this.render();
            long lastTime = System.currentTimeMillis(); 
            while (this.running) {
                final long current = System.currentTimeMillis();
                final int elapsed = (int) (current - lastTime);
                this.processInput();
                this.gameUpdate(elapsed);
                this.render();
                this.waitForNextFrame(current);
                lastTime = current;
            }
        }
    }

    @Override
    public final void stop() {
        this.running = false;
    }

    @Override
    public final void pause() {
        this.stopped = true;
    }

    @Override
    public final void resume() {
        this.stopped = false;
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
                Thread.currentThread().interrupt();
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
    private void render() {
        if (!this.stopped) {
            this.gameScene.render();
        }
    }

    @Override
    public final void notifyCommand(final Command command) {
        this.currentCommand = Optional.of(command);
    }
}
