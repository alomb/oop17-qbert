package qbert.model.characters;

import qbert.model.Tile;
import qbert.model.states.CharacterState;
import qbert.model.utilities.Position2D;
import qbert.view.CharacterGraphicComponent;

/**
 * A generic implementation of the {@link Character} interface that provides many common functionalities for
 * characters, it uses {@link CharacterState} to manage different behaviors, and a {@link GraphicComponent} for graphics tasks.
 * Subclasses should decide at least one state for standard behavior (normally called standing state) and could change
 * interaction with terrain that normally isn't provided (land method).
 */
public abstract class CharacterImpl implements Character {

    private Position2D currentPos;
    private Position2D nextPos;

    private float characterSpeed;
    private CharacterGraphicComponent graphics;

    private boolean dead;
    private CharacterState currentState;

    /**
     * @param startPos the logical first {@link Position2D} in the map
     * @param speed the movements speed of the character
     * @param graphics the relative {@link CharacterGraphicComponent} to manage graphics
     */
    public CharacterImpl(final Position2D startPos, final float speed, final CharacterGraphicComponent graphics) {
        this.currentPos = startPos;
        this.characterSpeed = speed;
        this.graphics = graphics;
        this.dead = false;
        this.nextPos = new Position2D(startPos);
    }

    @Override
    public final Position2D getCurrentPosition() {
        return this.currentPos;
    }

    @Override
    public final void setCurrentPosition(final Position2D currentGridPos) {
        this.currentPos = currentGridPos;
    }

    @Override
    public final Position2D getNextPosition() {
        return this.nextPos;
    }

    @Override
    public final void setNextPosition(final Position2D nextGridPos) {
        this.nextPos = nextGridPos;
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
    public final CharacterGraphicComponent getGraphicComponent() {
        return this.graphics;
    }

    @Override
    public final void setGraphicComponent(final CharacterGraphicComponent graphics) {
        this.graphics = graphics;
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
    public final void setDead() {
        this.dead = true;
    }

    @Override
    public final CharacterState getCurrentState() {
        return this.currentState;
    }

    @Override
    public final void setCurrentState(final CharacterState state) {
        this.currentState = state;
    }

    @Override
    public abstract CharacterState getStandingState();

    @Override
    public final void update(final float dt) {
        this.currentState.update(dt);
    }

    @Override
    public void land(final Tile t) {
        //Do nothing
    }
}