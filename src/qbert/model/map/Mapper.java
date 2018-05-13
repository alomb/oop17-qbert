package qbert.model.map;

import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;

public class Mapper {
    
    public static final int MAP_LEFT_TOP_EDGE = 2;
    public static final int MAP_RIGHT_TOP_EDGE = 14;
    public static final int MAP_BOTTOM_EDGE = 0;

    public Position2D getPhysical(Position2D logicPos) {
        int oldX = (int) logicPos.getX();
        int oldY = (int) logicPos.getY();
        int newX = Dimensions.backgroundX + Dimensions.tileWidth / 2 * (oldX + 1) - Dimensions.tileWidth / 2;
        int newY = Dimensions.backgroundY + Dimensions.backgroundHeight - (Dimensions.cubeHeight * (oldY + 1)) - (Dimensions.tileHeight / 2);

        return new Position2D(newX, newY);
    }
    
    public static boolean isOutOfMap(Position2D logicPos) {
        return logicPos.getY() < Mapper.MAP_BOTTOM_EDGE 
                || logicPos.getX() + logicPos.getY() == Mapper.MAP_RIGHT_TOP_EDGE 
                || logicPos.getY() - logicPos.getX() == Mapper.MAP_LEFT_TOP_EDGE;
    }
}
