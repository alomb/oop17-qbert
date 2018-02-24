package qbert.model;

import qbert.model.utilities.Position2D;

/**
 * An interface representing a game object.
 */
public interface Character extends GameObject{

    /**
     * @return
     */
    Position2D getNextPosition();

    /**
     * @param nextGridPos
     */
    void setNextPosition(Position2D nextGridPos);

    /**
     * @return
     */
    float getSpeed();

    /**
     * @param speed
     */
    void setSpeed(float speed);

    /**
     * @param dt
     */
    void update(float dt);
}
