package qbert.model.components;

import qbert.model.models.Game;

/**
 * Interface for handling the round winning conditions and actions.
 */
public interface ModeComponent {

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

    void addObserver(Game gameObserver);

    void notifyEndLevel();

}
