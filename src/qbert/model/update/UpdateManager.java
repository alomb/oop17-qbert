package qbert.model.update;

import java.util.stream.Collectors;

import qbert.model.Level;
import qbert.model.characters.DownUpwardCharacter;
import qbert.model.characters.Player;
import qbert.model.characters.states.DeathState;
import qbert.model.characters.states.FallState;
import qbert.model.characters.states.LandState;
import qbert.model.components.MapComponent;
import qbert.model.components.PointComponent;
import qbert.model.components.PointComponentImpl;
import qbert.model.components.StandardCollision;
import qbert.model.components.TimerComponent;
import qbert.model.components.TimerComponentImpl;
import qbert.model.spawner.Spawner;
import qbert.model.utilities.Position2D;

/**
 * Implementation of the updates of the various entities.
 */
public abstract class UpdateManager implements UpdateStrategy {

    private final Player qbert; 
    private final Spawner spawner;
    private final PointComponent points;
    private final MapComponent map;
    private final TimerComponent timer;

    private Level level;

    /**
     * @param qbert Instance of {@link Qbert}
     * @param spawner Instance of {@link SpawnerImpl}
     * @param points Instance of {@link PointComponent}
     * @param map Instance of {@link MapComponen}
     */
    public UpdateManager(final Player qbert, final Spawner spawner, final PointComponent points, final MapComponent map, final TimerComponent timer, Level level) {
        this.qbert = qbert;
        this.spawner = spawner;
        this.points = points;
        this.map = map;
        this.timer = timer;

        //TODO: Remove
        this.level = level;
    }

    /**
     * @return instance of {@link Player}
     */
    public Player getQbert() {
        return this.qbert;
    }

    /**
     * @return instance of {@link Spawner}
     */
    public Spawner getSpawner() {
        return this.spawner;
    }

    /**
     * @return instance of {@link PointComponent}
     */
    public PointComponent getPointComponent() {
        return this.points;
    }

    /**
     * @return instance of {@link MapComponent}
     */
    public MapComponent getMapComponent() {
        return this.map;
    }

    /**
     * @return instance of {@link TimerComponent}
     */
    public TimerComponent getTimerComponent() {
        return this.timer;
    }

    /**
     * @param elapsed the time passed since the last game cycle
     */
    protected void updateCollisions(final float elapsed) {
        spawner.updateGameCharacters(spawner.getGameCharacters().stream().peek(e -> {
            e.checkCollision(qbert, points, timer, new StandardCollision(false));

            if (e.isDead()) {
                //Notify Spawner
                spawner.death(e);
            }
        }).filter(e -> !e.isDead()).collect(Collectors.toList()));

        if (spawner.getCoily().isPresent()) {
            spawner.getCoily().get().checkCollision(qbert, points, timer, new StandardCollision(false));
        }
    }

    /**
     * @param elapsed the time passed since the last game cycle
     */
    protected void updateQbert(final float elapsed) {
        if (qbert.isDead()) {
            qbert.setCurrentState(new DeathState(qbert));
                timer.freezeEverything(() -> {
                    //TODO: Temporary
                    qbert.looseLife();
                    spawner.updateGameCharacters(spawner.getGameCharacters().stream().peek(e -> {
                        e.setCurrentState(new DeathState(e));
                        spawner.death(e);
                    }).filter(e -> !e.isDead()).collect(Collectors.toList()));
                    if (spawner.getCoily().isPresent()) {
                        spawner.killCoily();
                    }
                    spawner.respawnQbert();
                }, TimerComponentImpl.DEATH_ANIMATION_TIME);
        }

        final Position2D qLogicPos = qbert.getNextPosition();

        //Check if entity is just landed 
        if (qbert.getCurrentState() instanceof LandState) {
            //Checking if entity is outside the map
            if (this.map.isOnVoid(qLogicPos)) {
                if (this.map.checkForDisk(qbert)) {
                    this.spawner.getGameCharacters().forEach(c -> {
                        c.setCurrentState(new DeathState(c)); /////////////////////////
                    });
                    this.qbert.getPlayerSoundComponent().setOnDiskSound();
                } else {
                    qbert.setCurrentState(new FallState(qbert));
                }
            } else {
                spawner.getGameCharacters().forEach(e -> 
                    e.checkCollision(qbert, points, timer, (qbert, entity) -> 
                        qbert.getNextPosition().equals(e.getCurrentPosition()) 
                        && (e.getCurrentState() instanceof LandState || !e.isMoving())
                    )
                );

                if (!qbert.isDead()) {
                    qbert.land(this.map, this.points);
                    qbert.setCurrentState(qbert.getStandingState());
                    //TODO: Remove
                    level.checkStatus();
                }
            }
        }
    }

    /**
     * @param elapsed the time passed since the last game cycle
     */
    protected void updateEntities(final float elapsed) {
        spawner.update(elapsed);

        spawner.updateGameCharacters(spawner.getGameCharacters().stream().peek(e -> {
            final Position2D logicPos = e.getNextPosition();

            //Check if entity is just landed 
            if (e.getCurrentState() instanceof LandState) {
                //Checking if entity collides with Qbert falling out the map sides
                e.checkCollision(qbert, points, timer, (qbert, entity) -> 
                    ((qbert.getCurrentPosition().getX() - 1 == entity.getNextPosition().getX() 
                    ||  qbert.getCurrentPosition().getX() + 1 == entity.getNextPosition().getX()) 
                    && qbert.getCurrentPosition().getY() + 1 == entity.getNextPosition().getY()) 
                    && !qbert.isMoving()
                );

                //Checking if entity is outside the map
                if (this.map.isOnVoid(logicPos)) {
                    e.setCurrentState(new FallState(e));
                } else {
                    if (!e.checkCollision(qbert, points, timer, (qbert, entity) -> qbert.getCurrentPosition().equals(entity.getNextPosition()) && !qbert.isMoving())) {
                        e.land(this.map, this.points);
                        e.setCurrentState(e.getStandingState());
                    }
                }
            }

            if (e.isDead()) {
                //Notify Spawner
                spawner.death(e);
            }
        }).filter(e -> !e.isDead()).collect(Collectors.toList())); /* togliere parentesi se modifico */

        if (spawner.getCoily().isPresent()) {
            final DownUpwardCharacter e = spawner.getCoily().get();
            final Position2D logicPos = e.getNextPosition();

            //Check if entity is just landed 
            if (e.getCurrentState() instanceof LandState) {
                //Checking if entity is outside the map
                if (this.map.isOnVoid(logicPos)) {
                    e.setCurrentState(new FallState(e));
                    this.points.score(PointComponentImpl.COILY_FALL_SCORE, qbert);
                } else {
                    if (!e.checkCollision(qbert, points, timer, (qbert, entity) -> qbert.getCurrentPosition().equals(entity.getNextPosition()) && !qbert.isMoving())) {
                        e.land(this.map, this.points);
                        e.setCurrentState(e.getStandingState());
                    }
                }
            }

            if (e.isDead()) {
                //Notify Spawner
                spawner.death(e);
            }
        }
    }

    /**
     * @param elapsed the time passed since the last game cycle
     */
    protected void updateDisks(final float elapsed) {
        this.map.getDiskList().forEach(d -> d.update(elapsed));
    }
}
