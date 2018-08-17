package qbert.view.characters;

import java.util.Iterator;

import qbert.model.utilities.Position2D;

/**
 * An interface that models an animation like an iterator of {@link Position2D}.
 */
public interface MovementAnimation extends Iterator<Position2D> {

    /**
     * Call the {@link Iterator}'s next() setting the speed.
     * @param animationSpeed the speed of the animation
     * @return the next {@link Position2D} of the animation
     */
    Position2D updateAnimation(float animationSpeed);
}
