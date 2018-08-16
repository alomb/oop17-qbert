package qbert.model.components;

import java.util.stream.Collectors;

import qbert.model.Level;
import qbert.model.characters.Coily;
import qbert.model.characters.DownUpwardCharacter;
import qbert.model.characters.Player;
import qbert.model.characters.Snake;
import qbert.model.characters.states.DeathState;
import qbert.model.characters.states.FallState;
import qbert.model.characters.states.LandState;
import qbert.model.spawner.Spawner;
import qbert.model.utilities.Position2D;

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

    private boolean pauseEntities;
    private boolean pauseEverything;
    private boolean laterPauseEntities;

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

        //TODO: Remove
        this.level = level;
        this.laterPauseEntities = false;

        this.pauseEntities = false;
        this.pauseEverything = false;
    }

    /**
     * @param elapsed the time passed since the last game cycle
     */
    public void update(final float elapsed) {
        if (pauseEverything) {
            return;
        } 

        qbert.update(elapsed);
        if (!pauseEntities) {
            spawner.getGameCharacters().stream().forEach(e -> {
                e.update(elapsed);
            });
        }
        if (spawner.getCoily().isPresent()) {
            spawner.getCoily().get().update(elapsed);
        }

        this.updateCollisions(elapsed);

        this.updateQbert(elapsed);
        this.updateDisks(elapsed);
        if (!pauseEntities) {
            this.updateEntities(elapsed);
        }

        if (this.laterPauseEntities) {
            this.laterPauseEntities = false;
            this.pauseEntities = true;
        }
    }

    /**
     * @param elapsed the time passed since the last game cycle
     */
    private void updateCollisions(final float elapsed) {
        spawner.updateGameCharacters(spawner.getGameCharacters().stream().peek(e -> {
            //Check if entity is colliding with QBert
            if (qbert.getCurrentPosition().equals(e.getNextPosition()) && qbert.getNextPosition().equals(e.getCurrentPosition())
                    || (qbert.getCurrentPosition().equals(e.getCurrentPosition()) && qbert.getNextPosition().equals(e.getNextPosition()) && pauseEntities)
                    || ((qbert.getCurrentPosition().getX() - 1 == e.getCurrentPosition().getX() ||  qbert.getCurrentPosition().getX() + 1 == e.getCurrentPosition().getX()) && qbert.getCurrentPosition().getY() + 1 == e.getCurrentPosition().getY() && !e.isMoving() && !qbert.isMoving())) {
                e.collide(qbert, this.points, this);
            }
        }).filter(e -> !e.isDead()).collect(Collectors.toList()));

        if (spawner.getCoily().isPresent()) {
            Snake e = spawner.getCoily().get();
            if (qbert.getCurrentPosition().equals(e.getNextPosition()) && qbert.getNextPosition().equals(e.getCurrentPosition())
                    || (qbert.getCurrentPosition().equals(e.getCurrentPosition()) && qbert.getNextPosition().equals(e.getNextPosition()) && pauseEntities)
                    || ((qbert.getCurrentPosition().getX() - 1 == e.getCurrentPosition().getX() ||  qbert.getCurrentPosition().getX() + 1 == e.getCurrentPosition().getX()) && qbert.getCurrentPosition().getY() + 1 == e.getCurrentPosition().getY() && !e.isMoving() && !qbert.isMoving())) {
                e.collide(qbert, this.points, this);
            }
        }
    }

    /**
     * @param elapsed the time passed since the last game cycle
     */
    private void updateQbert(final float elapsed) {
        if (qbert.isDead()) {
            qbert.setCurrentState(new DeathState(qbert));
                this.pauseEverything = true;
                this.setTimeout(() -> {
                    //TODO: Temporary
                    qbert.looseLife();
                    spawner.updateGameCharacters(spawner.getGameCharacters().stream().peek(e -> {
                        e.setCurrentState(new DeathState(e));
                        spawner.death(e);
                    }).filter(e -> !e.isDead()).collect(Collectors.toList()));
                    if (spawner.getCoily().isPresent()) {
                        spawner.death(spawner.getCoily().get());
                        spawner.killCoily();
                    }
                    spawner.respawnQbert();
                    this.pauseEverything = false;
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
                boolean found = false;
                for (final qbert.model.characters.Character e : spawner.getGameCharacters()) {
                    if (qbert.getNextPosition().equals(e.getCurrentPosition()) && (e.getCurrentState() instanceof LandState || !e.isMoving())) {
                        e.collide(qbert, this.points, this);
                        found = true;
                        break;
                    }
                }
                if (!found) {
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
    private void updateEntities(final float elapsed) {
        spawner.update(elapsed);

        spawner.updateGameCharacters(spawner.getGameCharacters().stream().peek(e -> {
            final Position2D logicPos = e.getNextPosition();

            //Check if entity is just landed 
            if (e.getCurrentState() instanceof LandState) {

                //Checking if entity collides with Qbert falling out the map sides
                if (((qbert.getCurrentPosition().getX() - 1 == e.getNextPosition().getX() ||  qbert.getCurrentPosition().getX() + 1 == e.getNextPosition().getX()) && qbert.getCurrentPosition().getY() + 1 == e.getNextPosition().getY()) && !qbert.isMoving()) {
                    e.collide(qbert, this.points, this);
                }

                //Checking if entity is outside the map
                if (this.map.isOnVoid(logicPos)) {
                    e.setCurrentState(new FallState(e));
                    e.setCurrentState(new DeathState(e));

                    if (e instanceof Coily) {
                        this.points.score(PointComponentImpl.COILY_FALL_SCORE, qbert);
                    }
                } else {
                    if (qbert.getCurrentPosition().equals(e.getNextPosition()) && !qbert.isMoving()) {
                        e.collide(qbert, this.points, this);
                    } else {
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

                //Checking if entity collides with Qbert falling out the map sides
                if (((qbert.getCurrentPosition().getX() - 1 == e.getNextPosition().getX() ||  qbert.getCurrentPosition().getX() + 1 == e.getNextPosition().getX()) && qbert.getCurrentPosition().getY() + 1 == e.getNextPosition().getY()) && !qbert.isMoving()) {
                    e.collide(qbert, this.points, this);
                }

                //Checking if entity is outside the map
                if (this.map.isOnVoid(logicPos)) {
                    e.setCurrentState(new FallState(e));
                } else {
                    if (qbert.getCurrentPosition().equals(e.getNextPosition()) && !qbert.isMoving()) {
                        e.collide(qbert, this.points, this);
                    } else {
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
    private void updateDisks(final float elapsed) {
        this.map.getDiskList().forEach(d -> d.update(elapsed));
    }

    /**
     * Freezes all the enemy entities for a certain amount of time.
     * @param timeout Amount of time expressed in milliseconds
     */
    public void freezeEntities(final int timeout) {
        this.laterPauseEntities = true;
        this.setTimeout(() -> {
            this.pauseEntities = false;
        }, timeout);
    }

    /**
     * Freezes everything for a certain amount of time.
     * @param runnable Callback function
     * @param timeout Amount of time expressed in milliseconds
     */
    public void freezeEverything(final Runnable runnable, final int timeout) {
        this.pauseEverything = true;
        this.setTimeout(() -> {
            runnable.run();
            this.pauseEverything = false;
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
