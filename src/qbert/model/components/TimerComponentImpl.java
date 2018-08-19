package qbert.model.components;

import qbert.model.LevelStatus;
import qbert.model.characters.Player;
import qbert.model.spawner.Spawner;
import qbert.model.update.FreezeAll;
import qbert.model.update.FreezeEntities;
import qbert.model.update.FreezeNone;
import qbert.model.update.UpdateStrategy;

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
    public static final int DEATH_ANIMATION_TIME = 2000;

    /**
     * Duration of the round change animation expressed in milliseconds.
     */
    public static final int ROUND_ANIMATION_TIME = 2000;

    private final Player qbert; 
    private final Spawner spawner;
    private final PointComponent points;
    private final MapComponent map;
    private final LevelStatus status;
    private UpdateStrategy um;

    /**
     * @param qbert Instance of {@link Qbert}
     * @param spawner Instance of {@link SpawnerImpl}
     * @param points Instance of {@link PointComponent}
     * @param map Instance of {@link MapComponent}
     * @param status Instance of {@link LevelStatus}
     */
    public TimerComponentImpl(final Player qbert, final Spawner spawner, final PointComponent points, final MapComponent map, final LevelStatus status) {
        this.qbert = qbert;
        this.spawner = spawner;
        this.points = points;
        this.map = map;
        this.status = status;
        this.um = new FreezeNone(qbert, spawner, points, map, this, status);
    }

    @Override
    public final void update(final float elapsed) {
        um.update(elapsed);
    }


    @Override
    public final void freezeEntities(final int timeout) {
        this.um = new FreezeEntities(qbert, spawner, points, map, this, status);
        this.setTimeout(() -> {
            this.um = new FreezeNone(qbert, spawner, points, map, this, status);
        }, timeout);
    }

    @Override
    public final void freezeEverything(final Runnable runnable, final int timeout) {
        this.um = new FreezeAll(qbert, spawner, points, map, this, status);
        this.setTimeout(() -> {
            runnable.run();
            this.um = new FreezeNone(qbert, spawner, points, map, this, status);
        }, timeout);
    }

    /**
     * Utility function that starts a timer which execute a function at the end of the given time.
     * @param runnable Callback function
     * @param timeout Time expressed in milliseconds
     */
    private void setTimeout(final Runnable runnable, final int timeout) {
        new Thread(() -> {
            try {
                Thread.sleep(timeout);
                runnable.run();
            } catch (Exception e) { 
                runnable.run();
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
