package qbert.model;

import qbert.model.utilities.Position2D;
import qbert.view.CharacterGraphicComponent;

/**
 * An interface representing a visible and movable game object.
 */
public interface Character extends GameObject {

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
     * @return
     */
    CharacterGraphicComponent getGraphicComponent();

    /**
     * @param graphics
     */
    void setGraphicComponent(CharacterGraphicComponent graphics);

    /**
     * @param dt
     */
    void update(float dt);
}
