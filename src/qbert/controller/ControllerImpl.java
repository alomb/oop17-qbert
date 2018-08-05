package qbert.controller;

import java.util.List;

import org.jdom2.JDOMException;

import qbert.controller.input.Command;
import qbert.model.LevelSettings;
import qbert.model.models.GUILogic;
import qbert.view.Renderable;
import qbert.view.View;
import qbert.view.ViewImpl;

/**
 * The implementation of {@link Controller}.
 */
public class ControllerImpl implements Controller {

    private LevelConfigurationReader lcr;
    private final GameEngine gameEngine;
    private final GameStatusManager statusManager;

    private final View view;

    /**
     * @param firstGameStatus the first application's {@link GameStatus}
     */
    public ControllerImpl(final GameStatus firstGameStatus) {
        this.lcr = new LevelConfigurationReaderImpl();
        this.statusManager = new GameStatusManager(firstGameStatus, this);

        this.view = new ViewImpl(this);
        this.gameEngine = new GameEngine(this.view);
    }

    @Override
    public final void setupGameEngine() {
        this.changeScene(this.statusManager.getCurrentStatus());
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

    @Override
    public final void changeScene(final GameStatus newGameStatus) {
        this.statusManager.setCurrentStatus(newGameStatus);
        this.view.setScene(this.statusManager.getCurrentStatus().name());
        this.gameEngine.setup(this.statusManager.getModel());
    }

    @Override
    public final List<GUILogic> getGUI() {
        return this.statusManager.getModel().getGUI();
    }

    @Override
    public final List<Renderable> getRenderables() {
        return this.statusManager.getModel().getRenderables();
    }
}
