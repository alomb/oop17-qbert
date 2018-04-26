package qbert.model;

import qbert.model.utilities.Position2D;

/**
 * A very generic class for objects which have a {@link Position2D} to save their position in a space.
 */
public interface GameObject {

    /**
     * @return the current {@link Position2D} of the game object
     */
    Position2D getCurrentPosition();

    /**
     * @param newPos the new {@link Position2D} of the game object 
     */
    void setCurrentPosition(Position2D newPos);

}
