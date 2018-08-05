package qbert.model.spawner;

/**
 * The interface for the management of the information of a specific character.
 */
public interface EnemyInfo {

    /**
     * @return the {@link Character} movement speed
     */
    float getSpeed();

    /**
     * @return the current amount of a specific {@link Character} on the map
     */
    int getCurrentQuantity();

    /**
     * @return the maximum amount of a specific {@link Character} on the map
     */
    int getTotalQuantity();

    /**
     * @return the spawning frequency of the character
     */
    int getSpawningTime();

    /**
     * @return the time passed on standing state
     */
    int getStandingTime();

    /**
     * @return the timer of the specific spawned {@link Character}
     */
    int getElapsedTime();

    /**
     * This method increments the current amount of a specific {@link Character} on the map.
     */
    void incCurrentQuantity();

    /**
     * This method decrements the current amount of a specific {@link Character} on the map.
     */
    void decCurrentQuantity();

    /**
     * This method resets the timer of a specific {@Character}.
     */
    void resetElapsedTime();

    /**
     * @param dt the time passed since the last game cycle
     */
    void incElapsedTime(float dt);

}
