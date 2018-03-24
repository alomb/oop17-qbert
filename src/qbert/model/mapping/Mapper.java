package qbert.model.mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import qbert.model.Tile;
import qbert.model.utilities.Position2D;

public class Mapper {

    private int screenWidth;
    private int screenHeight;

    //TODO: Calculate from sprite dimensions
    private int tileWidth = 43;
    private int tileHeight = 74;
    private int mapWidth = 576;
    private int mapHeight = 499;

    private int windowWidth;
    private int windowHeight;

    // TODO: Make these variables dynamic in some way.
    private int centerX;
    private int topX;
    private int topY;
    
    //TODO: Remove
    private int errorY = 23;
    private int errorX = -12;


    public Mapper(int screenWidth, int screenHeight, int windowWidth, int windowHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        
        this.centerX = this.windowWidth / 2 - this.tileWidth;
        this.topX = (this.windowWidth - this.mapWidth) / 2;
        this.topY = (this.windowHeight - this.mapHeight) / 2;
    }

    public Position2D getPhysical(Position2D logicPos) {
        int oldX = (int) logicPos.getX();
        int oldY = (int) logicPos.getY();
        int newX = this.centerX + (-tileWidth) * (oldY - 1) + (tileWidth * 2) * (oldX - 1) - this.errorX;
        int newY = this.topY + tileHeight * (oldY - 1) - this.errorY;

        return new Position2D(newX, newY);
    }
    
    public Position2D getMapPos() {
        return new Position2D(this.topX, this.topY);
    }
}
