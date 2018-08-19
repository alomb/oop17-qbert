package qbert.model.update;

import qbert.model.characters.Player;
import qbert.model.components.MapComponent;
import qbert.model.components.ModeComponent;
import qbert.model.components.PointComponent;
import qbert.model.components.TimerComponent;
import qbert.model.spawner.Spawner;

/**
 * Update Implementation.
 * In this implementation everything is updated except enemies.
 */
public class FreezeEntities extends UpdateManager {

    /**
     * @param qbert Instance of {@link Qbert}
     * @param spawner Instance of {@link SpawnerImpl}
     * @param points Instance of {@link PointComponent}
     * @param map Instance of {@link MapComponent}
     * @param timer Instance of {@link TimerComponent}
     * @param mode Instance of {@link MapComponent}
     */
    public FreezeEntities(final Player qbert, final Spawner spawner, final PointComponent points, final MapComponent map, final TimerComponent timer,
            final ModeComponent mode) {
        super(qbert, spawner, points, map, timer, mode);
    }

    /**
     * @param elapsed the time passed since the last game cycle
     */
    public void update(final float elapsed) {
        this.getQbert().update(elapsed);

        this.updateCollisions(elapsed);

        this.updateQbert(elapsed);
        this.updateDisks(elapsed);
    }
}
