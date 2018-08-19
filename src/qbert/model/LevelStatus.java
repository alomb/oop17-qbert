package qbert.model;

import qbert.model.components.TimerComponent;
import qbert.model.models.Game;

/**
 * Interface for handling the round winning conditions and actions.
 */
public interface LevelStatus {

    /**
     * Checks if the player has won the round.
     * @param timer {@link TimerComponent} used to freeze the game in case the round is won.
     */
    void checkStatus(TimerComponent timer);

    /**
     * Prepares the next round to be loaded.
     * @param timer {@link TimerComponent} used to freeze the game.
     */
    void changeRound(TimerComponent timer);

    /**
     * @param gameObserver Instance of class {@link Game} than needs to be notified at the end of a level.
     */
    void addObserver(Game gameObserver);

    /**
     * Informs @{link Game} of the end of the current round.
     */
    void notifyEndLevel();

}
