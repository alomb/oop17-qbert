package qbert.model.components;

import qbert.model.Level;
import qbert.model.characters.Player;
import qbert.model.spawner.Spawner;

/**
 * Component managing informations about the game timers and updates all the entities.
 */
public class TimerComponentImpl implements TimerComponent {
    /**
     * Default time expressed in milliseconds in which the green ball freezes the other entities when killed.
     */
    public static final int GREEN_BALL_FREEZE_TIME = 8000;

    /**
     * Duration of the death animation expressed in milliseconds.
     */
    public static final int DEATH_ANIMATION_TIME = 4000;

    /**
     * Duration of the round change animation expressed in milliseconds.
     */
    public static final int ROUND_ANIMATION_TIME = 2000;

    private final Player qbert; 
    private final Spawner spawner;
    private final PointComponent points;
    private final MapComponent map;
    private UpdateManager um;

    private Level level;

    /**
     * Constructor of class TimerComponent.
     * @param qbert Instance of {@link Qbert}
     * @param spawner Instance of {@link SpawnerImpl}
     * @param points Instance of {@link PointComponent}
     * @param map Instance of {@link MapComponen}
     */
    public TimerComponentImpl(final Player qbert, final Spawner spawner, final PointComponent points, final MapComponent map, Level level) {
        this.qbert = qbert;
        this.spawner = spawner;
        this.points = points;
        this.map = map;
        this.um = new StandardUpdate(qbert, spawner, points, map, this, level);
    }

    /**
     * @param elapsed the time passed since the last game cycle
     */
    public void update(final float elapsed) {
        um.update(elapsed);
    }

    /**
     * Freezes all the enemy entities for a certain amount of time.
     * @param timeout Amount of time expressed in milliseconds
     */
    public void freezeEntities(final int timeout) {
        this.um = new FreezeEntities(qbert, spawner, points, map, this, level);
        this.setTimeout(() -> {
            this.um = new StandardUpdate(qbert, spawner, points, map, this, level);
        }, timeout);
    }

    /**
     * Freezes everything for a certain amount of time.
     * @param runnable Callback function
     * @param timeout Amount of time expressed in milliseconds
     */
    public void freezeEverything(final Runnable runnable, final int timeout) {
        this.um = new FreezeAll(qbert, spawner, points, map, this, level);
        this.setTimeout(() -> {
            runnable.run();
            this.um = new StandardUpdate(qbert, spawner, points, map, this, level);
        }, timeout);
    }

    /**
     * Utility function that starts a timer which execute a function at the end of the given time.
     * @param runnable Callback function
     * @param timeout Time expressed in milliseconds
     */
    public void setTimeout(final Runnable runnable, final int timeout) {
        new Thread(() -> {
            try {
                Thread.sleep(timeout);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }
}
