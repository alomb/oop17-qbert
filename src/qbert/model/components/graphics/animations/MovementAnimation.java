package qbert.model.components.graphics.animations;

import java.util.Iterator;

import qbert.model.utilities.Position2D;

/**
 * An interface that models an animation like an iterator of {@link Position2D}.
 */
public interface MovementAnimation extends Iterator<Position2D> {

    /**
     * Call the {@link Iterator}'s next() setting the speed.
     * @param animationCycles the number of times to update the animation
     * @return the next {@link Position2D} of the animation
     */
    Position2D updateAnimation(int animationCycles);
}
