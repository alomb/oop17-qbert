package qbert.model.spawner;

import qbert.model.characters.Character;
import qbert.model.characters.Snake;
import qbert.model.characters.Player;

/**
 * The factory method interface for enemies creation.
 */
public interface EnemyFactory {

    /**
     * @param speed the QBert speed
     * @return the {@link Player} representing {@link Qbert}
     * @param qbertLives number of lives the {@link Player} is starting the level with
     */
    Player createQbert(float speed, int qbertLives);

    /**
     * @param speed the {@link Character} movement speed
     * @param standingTime the time passed on standing state
     * @param qbert the {@link Player} reference
     * @return the {@link Snake} representing {@link Coily} enemy
     */
    Snake createCoily(float speed, int standingTime, Player qbert);

    /**
     * @param speed the {@link Character} movement speed
     * @param standingTime the time passed on standing state
     * @return the {@link Character} representing {@link RedBall} enemy
     */
    Character createRedBall(float speed, int standingTime);

    /**
     * @param speed the {@link Character} movement speed
     * @param standingTime the time passed on standing state
     * @return the {@link Character} representing {@link GreenBall}
     */
    Character createGreenBall(float speed, int standingTime);

    /**
     * @param speed the {@link Character} movement speed
     * @param standingTime the time passed on standing state
     * @return the {@link Character} representing {@link SamAndSlick}
     */
    Character createSamAndSlick(float speed, int standingTime);

    /**
     * @param speed the {@link Character} movement speed
     * @param standingTime the time passed on standing state
     * @return the {@link Character} representing {@link Wrongway}
     */
    Character createWrongway(float speed, int standingTime);

    /**
     * @param speed the {@link Character} movement speed
     * @param standingTime the time passed on standing state
     * @return the {@link Character} representing {@link Ugg}
     */
    Character createUgg(float speed, int standingTime);
}
