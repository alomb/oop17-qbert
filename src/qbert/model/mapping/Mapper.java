package qbert.model.mapping;

import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;

public class Mapper {

    public Position2D getPhysical(Position2D logicPos) {
        int oldX = (int) logicPos.getX();
        int oldY = (int) logicPos.getY();
        int newX = Dimensions.backgroundX + Dimensions.tileWidth / 2 * (oldX + 1) - Dimensions.tileWidth / 2;
        int newY = Dimensions.backgroundY + Dimensions.backgroundHeight - (Dimensions.cubeHeight * (oldY + 1)) - (Dimensions.tileHeight / 2);

        return new Position2D(newX, newY);
    }
}
