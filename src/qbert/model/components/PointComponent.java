package qbert.model.components;

import qbert.model.characters.Player;

/**
 * Component managing informations about player points.
 */
public interface PointComponent {

    /**
     * Increments the current points value of the given amount.
     * @param amount Number of points gained
     * @param qbert Instance of the player
     */
    void score(int amount, Player qbert);

    /**
     * @return Current player points value
     */
    int getPoints();
}
