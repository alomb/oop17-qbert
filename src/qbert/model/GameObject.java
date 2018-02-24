package qbert.model;

import qbert.model.utilities.Position2D;

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
