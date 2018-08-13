package qbert.controller;

import java.util.HashMap;
import java.util.Map;

import qbert.LoggerManager;
import qbert.model.models.Game;
import qbert.model.models.GameOver;
import qbert.model.models.Introduction;
import qbert.model.models.Menu;
import qbert.model.models.Model;
import qbert.model.models.Ranking;

/**
 * The implementation of {@link GameStatusManager}.
 */
public class GameStatusManagerImpl implements GameStatusManager {

    private final Map<GameStatus, Model> models;
    private GameStatus currentGameStatus;

    /**
     * @param firstGameStatus the first {@link GameStatus} of the application
     * @param controller the game {@link Controller} to be passed to models
     */
    public GameStatusManagerImpl(final GameStatus firstGameStatus, final Controller controller) {
        this.models = new HashMap<>();

        this.models.put(GameStatus.INTRODUCTION, new Introduction(controller));
        this.models.put(GameStatus.MENU, new Menu(controller));
        this.models.put(GameStatus.RANKING, new Ranking(controller));
        this.models.put(GameStatus.GAMEPLAY, new Game(controller));
        this.models.put(GameStatus.GAMEOVER, new GameOver(controller));

        this.currentGameStatus = firstGameStatus;

        if (!this.models.keySet().equals(GameStatus.getAll())) {
            controller.abort();
            LoggerManager.getInstance().info(this.getClass().getName(), "Not all the game status have been initialized. Program aborted");
        }
    }

    @Override
    public final Model getModel() {
        return this.models.get(this.currentGameStatus);
    }

    @Override
    public final GameStatus getCurrentStatus() {
        return this.currentGameStatus;
    }

    @Override
    public final void setCurrentStatus(final GameStatus newGameStatus) {
        this.currentGameStatus = newGameStatus;
        this.getModel().initialize();
    }
}
