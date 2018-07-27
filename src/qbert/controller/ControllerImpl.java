package qbert.controller;

import org.jdom2.JDOMException;

import qbert.input.Command;
import qbert.model.LevelSettings;

/**
 * The implementation of {@link Controller}.
 */
public class ControllerImpl implements Controller {

    private LevelConfigurationReader lcr;
    private GameEngine gameEngine;

    /**
     * 
     */
    public ControllerImpl() {
        this.lcr = new LevelConfigurationReaderImpl();
    }

    @Override
    public final void setupGameEngine() {
        this.gameEngine = new GameEngine();
        this.gameEngine.setup(this);
        this.gameEngine.mainLoop();
    }

    @Override
    public final LevelSettings getLevelSettings(final int level, final int round) {
        this.lcr = new LevelConfigurationReaderImpl();
        try {
            lcr.readLevelConfiguration(level, round);
        } catch (JDOMException e) {
            e.printStackTrace();
        }
        return lcr.getLevelSettings();
    }

    @Override
    public final void notifyCommand(final Command command) {
        this.gameEngine.notifyCommand(command);
    }
}
