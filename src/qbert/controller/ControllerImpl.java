package qbert.controller;

import org.jdom2.JDOMException;

import qbert.input.Command;
import qbert.model.Game;
import qbert.model.Introduction;
import qbert.model.LevelSettings;
import qbert.view.View;

/**
 * The implementation of {@link Controller}.
 */
public class ControllerImpl implements Controller {

    private LevelConfigurationReader lcr;
    private GameEngine gameEngine;

    private final Game game;
    private final Introduction introduction;
    private final View view;

    /**
     * 
     */
    public ControllerImpl() {
        this.lcr = new LevelConfigurationReaderImpl();
        this.game = new Game(this);
        this.introduction = new Introduction();
        this.view = new View(this, game, introduction);
    }

    @Override
    public final void setupGameEngine() {
        this.gameEngine = new GameEngine();
        this.view.setScene("SceneIntro");
        this.gameEngine.setup(this.view.getScene(), introduction);
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
    public final void changeScene() {
        game.initializeLevel();
        this.view.setScene("SceneGame");
        this.gameEngine.setup(this.view.getScene(), game);
    }
}
