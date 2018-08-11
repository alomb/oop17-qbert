package qbert.view.characters;

import qbert.model.utilities.Position2D;

/**
 * An interface that models an animation like an iterator of {@link Position2D}.
 */
public interface Animation {

    /**
     * @return true if the {@link Animation} is ended
     */
    boolean hasFinished();

    /**
     * A sort of {@link Iterator}'s next() depending on the speed.
     * @param animationSpeed the speed of the animation
     * @return the next {@link Position2D} of the animation
     */
    Position2D updateAnimation(float animationSpeed);
}
