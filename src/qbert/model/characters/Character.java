package qbert.model.characters;

import qbert.model.GameObject;
import qbert.model.Level;
import qbert.model.components.MapComponent;
import qbert.model.components.PointComponent;
import qbert.model.states.CharacterState;
import qbert.model.utilities.Position2D;
import qbert.view.characters.CharacterGC;

/**
 * An interface representing a visible and movable game character. It provides
 * the most common functionalities.
 */
public interface Character extends GameObject {

    /**
     * @return the {@link Position2D} where the {@link Character} is moving
     */
    Position2D getNextPosition();

    /**
     * @param nextGridPos the {@link Position2D} where the {@link Character} is moving
     */
    void setNextPosition(Position2D nextGridPos);

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
     */
    void land(MapComponent map);

    /**
     * @param l temporary reference to level for dealing collision between {@link Character} and QBert
     * @param points reference to {@link PointComponent} to eventually score points in entity collisions
     */
    void collide(Level l, PointComponent points);
}
