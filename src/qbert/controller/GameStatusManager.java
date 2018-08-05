package qbert.controller;

import java.util.HashMap;
import java.util.Map;

import qbert.model.Game;
import qbert.model.Introduction;
import qbert.model.Menu;
import qbert.model.Model;

public class GameStatusManager {

    private final Map<GameStatus, Model> models;
    private GameStatus currentGameStatus;

    public GameStatusManager(final GameStatus firstGameStatus, final Controller controller) {
        this.models = new HashMap<>();

        this.models.put(GameStatus.INTRODUCTION, new Introduction(controller));
        this.models.put(GameStatus.MENU, new Menu(controller));
        this.models.put(GameStatus.GAMEPLAY, new Game(controller));

        if (!this.models.keySet().equals(GameStatus.getAll())) {
            throw new UnsupportedOperationException();
        }

        this.currentGameStatus = firstGameStatus;
    }

    public final Model getModel() {
        return this.models.get(this.currentGameStatus);
    }

    public final GameStatus getCurrentStatus() {
        return this.currentGameStatus;
    }

    public final void setCurrentStatus(final GameStatus newGameStatus) {
        this.currentGameStatus = newGameStatus;
        this.getModel().initialize();
    }
}
