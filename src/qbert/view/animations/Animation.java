package qbert.view.animations;

import qbert.model.utilities.Position2D;

/**
 *
 */
public interface Animation {

    /**
     * @return
     */
    float getAnimationSpeed();

    /**
     * @return
     */
    Position2D getCurrentPosition();

    /**
     * @param currentPosition
     */
    void setCurrentPosition(Position2D currentPosition);

    /**
     * @return
     */
    Position2D getTargetPosition();

    /**
     * @param targetPosition
     */
    void setTargetPosition(Position2D targetPosition);

    /**
     * @return
     */
    boolean hasFinished();

    /**
     * @param animationSpeed
     */
    Position2D updateAnimation(float animationSpeed);

    /**
     * 
     */
    void calculateNext();
}
