package qbert.controller;

import qbert.model.scenes.Model;

/**
 * The responsible of {@link GameStatus} management and {@link Model} replacement.
 * {@link Model}s are instantiated immediately and associated to a specific and
 * unique {@link GameStatus}, changing the current {@link GameStatus} its possible to
 * retrieve the relative {@link Model}.
 */
public interface GameStatusManager {

    /**
     * @return the current {@link GameStatus} associated {@link Model}
     */
    Model getModel();

    /**
     * @return the current {@link GameStatus}
     */
    GameStatus getCurrentStatus();

    /**
     * @param newGameStatus the new {@link GameStatus}
     */
    void setCurrentStatus(GameStatus newGameStatus);

}
