package qbert.model.characters;

import qbert.model.Collidable;
import qbert.model.GameObject;
import qbert.model.characters.states.CharacterState;
import qbert.model.components.MapComponent;
import qbert.model.components.PointComponent;
import qbert.model.utilities.Position2D;
import qbert.model.components.graphics.Renderable;
import qbert.model.components.graphics.CharacterGC;

/**
 * An interface representing a visible and movable game character.
 */
public interface Character extends GameObject, Renderable, Collidable {

    /**
     * @return the {@link Position2D} where the {@link Character} is moving
     */
    Position2D getNextPosition();

    /**
     * @param nextGridPos the {@link Position2D} where the {@link Character} is moving
     */
    void setNextPosition(Position2D nextGridPos);

    /**
     * @return how long the {@link Character} normally moves on logic position.
     */
    int getStep();

    /**
     * @return the {@link Character} movement speed
     */
    float getSpeed();

    /**
     * @param speed the new {@link Character} movement speed
     */
    void setSpeed(float speed);

    /**
     * @return the {@link CharacterGC} of this {@link Character}
     */
    CharacterGC getGraphicComponent();

    /**
     * @return true if the {@link Character} is moving
     */
    boolean isMoving();

    /**
     * @return true if the {@link Character} is dead
     */
    boolean isDead();

    /**
     *  @param dead true if the {@link Character} id dead.
     */
    void setDead(boolean dead);

    /**
     * @return the current {@link CharacterState}
     */
    CharacterState getCurrentState();

    /**
     * @param state the new current {@link CharacterState} 
     */
    void setCurrentState(CharacterState state);

    /**
     * @return the standing {@link CharacterState}
     */
    CharacterState getStandingState();

    /**
     * @param dt the time passed since the last game cycle
     */
    void update(float dt);

    /**
     * @param map the component containing informations about tiles status
     * @param points the component used to update game points
     */
    void land(MapComponent map, PointComponent points);
}
