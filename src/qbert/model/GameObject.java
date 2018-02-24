package qbert.model;

import model.utilities.Position2D;

/**
 *
 */
public interface GameObject {

    /**
     * @return
     */
    Position2D getCurrentPosition();

    /**
     * @param currentGridPos
     */
    void setCurrentPosition(Position2D currentGridPos);

}
