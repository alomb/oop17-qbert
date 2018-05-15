package qbert.model;

import qbert.model.characters.Character;
import qbert.model.characters.Coily;
import qbert.model.characters.Player;

/**
 * The factory method interface for enemies creation.
 */
public interface EnemyFactory {

    /**
     * @return the {@link Player} representing Qbert
     */
    Player createQbert();

    /**
     * @param speed the {@link Character} movement speed
     * @param standingTime the time passed on standing state
     * @param qbert the {@link Player} reference
     * @return the {@link Coily} representing Coily enemy
     */
    Coily createCoily(float speed, int standingTime, Player qbert);

    /**
     * @param speed the {@link Character} movement speed
     * @param standingTime the time passed on standing state
     * @return the {@link Character} representing RedBall enemy
     */
    Character createRedBall(float speed, int standingTime);
}
