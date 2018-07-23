package qbert.model.components;

import java.util.stream.Collectors;

import qbert.model.Spawner;
import qbert.model.characters.Coily;
import qbert.model.characters.Player;
import qbert.model.states.FallState;
import qbert.model.states.LandState;
import qbert.model.utilities.Position2D;

/**
 * Component managing informations about the game timers and updates all the entities.
 */
public class TimerComponent {
    /**
     * Default time expressed in milliseconds in which the green ball freezes the other entities when killed.
     */
    public static final int GREEN_BALL_FREEZE_TIME = 8000;

    private final Player qbert; 
    private final Spawner spawner;
    private final PointComponent points;
    private final MapComponent map;

    private boolean pauseEntities;
    private int timer;

    /**
     * Constructor of class TimerComponent.
     * @param qbert Instance of {@link Qbert}
     * @param spawner Instance of {@link Spawner}
     * @param points Instance of {@link PointComponent}
     * @param map Instance of {@link MapComponen}
     */
    public TimerComponent(final Player qbert, final Spawner spawner, final PointComponent points, final MapComponent map) {
        this.qbert = qbert;
        this.spawner = spawner;
        this.points = points;
        this.map = map;
    }

    /**
     * @param elapsed the time passed since the last game cycle
     */
    public void update(final float elapsed) {
        this.timer -= elapsed;
        if (this.timer < 0) {
            this.timer = 0;
            this.timeout();
        }

        if (!pauseEntities) {
            this.updateEntities(elapsed);
        }

        this.updateDisks(elapsed);
        this.updateQbert(elapsed);
    }

    /**
     * @param elapsed the time passed since the last game cycle
     */
    public void updateQbert(final float elapsed) {
        qbert.update(elapsed);
    }

    /**
     * @param elapsed the time passed since the last game cycle
     */
    public void updateEntities(final float elapsed) {
        spawner.update(elapsed);

        spawner.updateGameCharacters(spawner.getGameCharacters().stream().peek(e -> {
            e.update(elapsed);
            final Position2D logicPos = e.getNextPosition();
            //Check if entity is just landed 
            if (e.getCurrentState() instanceof LandState) {
                //Checking if entity is outside the map
                if (this.map.isOnVoid(logicPos)) {
                    e.setCurrentState(new FallState(e));

                    if (e instanceof Coily) {
                        this.points.score(PointComponent.COILY_FALL_SCORE);
                    }
                } else {
                    e.land(this.map, this.points);
                    e.setCurrentState(e.getStandingState());
                }
            }

            if (e.isDead()) {
                //Notify Spawner
                spawner.death(e);
            } else {
                //Check if entity is colliding with QBert
                //TODO: Exclude collision detection from time freezing
                if (qbert.getCurrentPosition().equals(e.getCurrentPosition()) && !qbert.isMoving() && !e.isMoving()
                        || qbert.getCurrentPosition().equals(e.getNextPosition()) && qbert.getNextPosition().equals(e.getCurrentPosition())) {
                    e.collide(qbert, this.points, this);
                }
            }
        }).filter(e -> !e.isDead()).collect(Collectors.toList())); /* togliere parentesi se modifico */
    }

    /**
     * @param elapsed the time passed since the last game cycle
     */
    public void updateDisks(final float elapsed) {
        this.map.getDiskList().forEach(d -> d.update(elapsed));
    }

    /**
     * Freezes all the enemy entities for a certain amount of time.
     * @param timeout Amount of time expressed in milliseconds
     */
    public void freezeEntities(final int timeout) {
        this.timer = timeout;
        this.pauseEntities = true;
    }

    private void timeout() {
        pauseEntities = false;
    }
}
