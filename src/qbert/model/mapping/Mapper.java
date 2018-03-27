package qbert.model.mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import qbert.model.Dimensions;
import qbert.model.Tile;
import qbert.model.utilities.Position2D;

public class Mapper {

    private int mapWidth = 601;
    private int topX;
    private int topY;


    public Mapper(int screenWidth, int screenHeight, int windowWidth, int windowHeight) {
        this.topX = (Dimensions.windowWidth - this.mapWidth) / 2;
        this.topY = (Dimensions.windowHeight - Dimensions.backgroundHeight) / 2;
    }

    public Position2D getPhysical(Position2D logicPos) {
        int oldX = (int) logicPos.getX();
        int oldY = (int) logicPos.getY();
        int newX = this.topX + Dimensions.tileWidth / 2 * (oldX + 1) - Dimensions.tileWidth / 2;
        int newY = this.topY + Dimensions.backgroundHeight - (Dimensions.cubeHeight * (oldY + 1)) - (Dimensions.tileHeight / 2);

        return new Position2D(newX, newY);
    }
  
    public Position2D getMapPos() {
        return new Position2D(this.topX, this.topY);
    }
}
