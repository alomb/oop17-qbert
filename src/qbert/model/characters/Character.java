package qbert.model.characters;


import qbert.model.GameObject;
import qbert.model.Level;
import qbert.model.Tile;
import qbert.model.states.CharacterState;
import qbert.model.utilities.Position2D;
import qbert.view.CharacterGraphicComponent;

/**
 * An interface representing a visible and movable game object.
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
     * @return the {@link CharacterGraphicComponent} of this {@link Character}
     */
    CharacterGraphicComponent getGraphicComponent();

    /**
     * @param graphics the new {@link CharacterGraphicComponent} of this {@link Character}
     */
    void setGraphicComponent(CharacterGraphicComponent graphics);

    /**
     * @return true if the {@link Character} is moving
     */
    boolean isMoving();

    /**
     * @return true if the {@link Character} is dead
     */
    boolean isDead();

    /**
     * Set the {@link Character} dead.
     */
    void setDead();

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
     * @param t the reference to the tile where the {@link Character} has just landed
     */
    void land(Tile t);


    /**
     * @param l temporary reference to level for dealing collision between {@link Character} and QBert
     */
    void collide(Level l);
}
