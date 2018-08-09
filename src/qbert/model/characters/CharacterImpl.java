package qbert.model.characters;

import qbert.model.characters.states.CharacterState;
import qbert.model.characters.states.FallState;
import qbert.model.components.MapComponent;
import qbert.model.components.PointComponent;
import qbert.model.components.TimerComponent;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.view.characters.CharacterGC;

/**
 * A generic implementation of the {@link Character} interface that provides many common functionalities for
 * characters, it uses {@link CharacterState} to manage different behaviors, and a {@link GraphicComponent} for graphics tasks.
 * 
 * Subclasses must decide at least one state for standard behavior (normally called standing state) and could change
 * interaction with terrain that normally isn't provided (land method).
 * 
 * Subclasses could change collision behavior modifying collide and land methods that normally don't provide any specific operations.
 */
public abstract class CharacterImpl implements Character {

    private Position2D currentPos;
    private Position2D nextPos;

    private float characterSpeed;
    private final CharacterGC graphics;

    private boolean dead;
    private CharacterState currentState;
    private final int step;

    /**
     * @param startPos the logical first {@link Position2D} in the map
     * @param speed the movements speed of the character
     * @param graphics the relative {@link CharacterGC} to manage graphics
     */
    public CharacterImpl(final Position2D startPos, final float speed, final CharacterGC graphics) {
        this.currentPos = new Position2D(startPos);
        this.characterSpeed = speed;
        this.graphics = graphics;
        this.dead = false;
        this.nextPos = new Position2D(startPos);
        this.step = 2;
    }

    @Override
    public final Position2D getCurrentPosition() {
        return this.currentPos;
    }

    @Override
    public final void setCurrentPosition(final Position2D currentGridPos) {
        this.currentPos = new Position2D(currentGridPos);
    }

    @Override
    public final Position2D getNextPosition() {
        return this.nextPos;
    }

    @Override
    public final void setNextPosition(final Position2D nextGridPos) {
        this.nextPos = new Position2D(nextGridPos);
    }

    @Override
    public final int getStep() {
        return step;
    }

    @Override
    public final float getSpeed() {
        return this.characterSpeed;
    }

    @Override
    public final void setSpeed(final float speed) {
        this.characterSpeed = speed;
    }

    @Override
    public final CharacterGC getGraphicComponent() {
        return this.graphics;
    }

    @Override
    public final boolean isMoving() {
        return !this.currentPos.equals(this.nextPos);
    }

    @Override
    public final boolean isDead() {
        return this.dead;
    }

    @Override
    public final void setDead(final boolean dead) {
        this.dead = dead;
    }

    @Override
    public final CharacterState getCurrentState() {
        return this.currentState;
    }

    @Override
    public void setCurrentState(final CharacterState state) {
        this.currentState = state;
    }

    @Override
    public abstract CharacterState getStandingState();

    @Override
    public final void update(final float dt) {
        this.currentState.update(dt);
    }

    @Override
    public void land(final MapComponent map, final PointComponent points) {
        //Do nothing
    }

    @Override
    public void collide(final Player qbert, final PointComponent points, final TimerComponent timer) {
        //Do nothing
    }

    @Override
    public final int getZIndex() {
        if (this.getCurrentState() instanceof FallState) {
            return Dimensions.MAP_BEHIND_INDEX;
        } else {
            return Dimensions.MAP_ROWS - this.getCurrentPosition().getY();
        }
    }
}
