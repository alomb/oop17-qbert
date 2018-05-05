package qbert.model;

import qbert.model.characters.Character;

/**
 * The factory method interface for enemies creation.
 */
public interface EnemyFactory {

    /**
     * @return the {@link Character} representing Qbert
     */
    Character createQbert();

    /**
     * @param speed the {@link Character} movement speed
     * @param standingTime the time passed on standing state
     * @param qbert the {@link Qbert} reference
     * @return the {@link Character} representing Coily enemy
     */
    Character createCoily(float speed, int standingTime, Character qbert);

    /**
     * @param speed the {@link Character} movement speed
     * @param standingTime the time passed on standing state
     * @return the {@link Character} representing RedBall enemy
     */
    Character createRedBall(float speed, int standingTime);
}
