package qbert.model.mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import qbert.model.Tile;
import qbert.model.utilities.Position2D;

public class Mapper {
    
    private int screenWidth;
    private int screenHeight;

    // TODO: Make these variables dynamic in some way.
    private int topX = 288;
    private int topY = 17;


    public Mapper(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }
    
    public Position2D getPhysical(Position2D logicPos) {
        int oldX = (int)logicPos.getX();
        int oldY = (int)logicPos.getY();
        int newX = this.topX + (-41) * (oldY - 1) + (+82) * (oldX - 1);
        int newY = this.topY + 71 * (oldY - 1);
        
        return new Position2D(newX, newY);
    }
}
