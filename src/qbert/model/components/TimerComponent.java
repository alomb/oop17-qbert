package qbert.model.components;

/**
 * Component managing informations about the game timers and updates all the entities.
 */
public interface TimerComponent {

    /**
     * @param elapsed the time passed since the last game cycle
     */
    void update(float elapsed);

    /**
     * Freezes all the enemy entities for a certain amount of time.
     * @param timeout Amount of time expressed in milliseconds
     */
    void freezeEntities(int timeout);

    /**
     * Freezes everything for a certain amount of time.
     * @param runnable Callback function
     * @param timeout Amount of time expressed in milliseconds
     */
    void freezeEverything(Runnable runnable, int timeout);
}
