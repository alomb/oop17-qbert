package qbert.view.animations;

import qbert.model.utilities.Position2D;

/**
 * An interface that models an animation like an iterator of {@link Position2D}.
 */
public interface Animation {

    /**
     * @return the current {@link Position2D} of the animation
     */
    Position2D getCurrentPosition();

    /**
     * @param currentPosition set the current {@link Position2D} of the animation
     */
    void setCurrentPosition(Position2D currentPosition);

    /**
     * @return true if the {@link Animation} is ended
     */
    boolean hasFinished();

    /**
     * A sort of iterator's next() depending on the speed.
     * @param animationSpeed the speed of the animation
     * @return the next {@link Position2D} of the animation
     */
    Position2D updateAnimation(float animationSpeed);
}
