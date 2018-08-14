package qbert.model.utilities;

/**
 * The class containing the logical and physical positions of the elements on the screen.
 */
public final class Dimensions {

    /**
     * Index representing the lowest Y axis value before the bottom limit of the map.
     */
    public static final int MAP_BOTTOM_EDGE = 1;

    /**
     * Number of columns used define all walkable positions in the map.
     */
    public static final int MAP_COLUMNS = 27;

    /**
     * Number of rows used define all walkable positions in the map.
     */
    public static final int MAP_ROWS = 14;

    /**
     * Number of columns of upper tiles in the map.
     */
    public static final int MAP_TILES_COLUMNS = 7;

    /**
     * Number of rows of upper tiles in the map.
     */
    public static final int MAP_TILES_ROWS = 7;

    /**
     * Index representing the Z axis positioned behind the map.
     */
    public static final int MAP_BEHIND_INDEX = -1;

    /**
     * Index representing the X axis value of the left spawning position.
     */
    public static final int MAP_SPAWNING_POINT_LEFT_X = 11;

    /**
     * Index representing the Y axis value of the left spawning position.
     */
    public static final int MAP_SPAWNING_POINT_LEFT_Y = 11;

    /**
     * Index representing the X axis value of the right spawning position.
     */
    public static final int MAP_SPAWNING_POINT_RIGHT_X = 15;

    /**
     * Index representing the Y axis value of the right spawning position.
     */
    public static final int MAP_SPAWNING_POINT_RIGHT_Y = 11;

    /**
     * Index representing the X axis value of the QBert spawning position.
     */
    public static final int MAP_SPAWNING_QBERT_X = 13;

    /**
     * Index representing the Y axis value of the QBert spawning position.
     */
    public static final int MAP_SPAWNING_QBERT_Y = 13;

    /**
     * A position that doesn't exist in the grid.
     */
    public static final Position2D UNDEFINED_POSITION = new Position2D(-MAP_COLUMNS, -MAP_ROWS);

    private static int windowHeight;
    private static int windowWidth;
    private static int cubeHeight;
    private static int cubeWidth;
    private static int tileHeight;
    private static int backgroundHeight;
    private static int backgroundWidth;
    private static Position2D backgroundPos;

    private Dimensions() {

    }

    /**
     * @return the window height
     */
    public static int getWindowHeight() {
        return windowHeight;
    }

    /**
     * @param wHeight the windows height
     */
    public static void setWindowHeight(final int wHeight) {
         windowHeight = wHeight;
    }

    /**
     * @return the window width
     */
    public static int getWindowWidth() {
        return windowWidth;
    }

    /**
     * @param wWidth the window width
     */
    public static void setWindowWidth(final int wWidth) {
         windowWidth = wWidth;
    }

    /**
     * @return the cube height
     */
    public static int getCubeHeight() {
        return cubeHeight;
    }

    /**
     * @param cubeHeight the cube height
     */
    public static void setCubeHeight(final int cubeHeight) {
        Dimensions.cubeHeight = cubeHeight;
    }

    /**
     * @return the cube width
     */
    public static int getCubeWidth() {
        return cubeWidth;
    }

    /**
     * @param cubeWidth the cube width
     */
    public static void setCubeWidth(final int cubeWidth) {
        Dimensions.cubeWidth = cubeWidth;
    }

    /**
     * @return the tile height
     */
    public static int getTileHeight() {
        return tileHeight;
    }

    /**
     * @param tileHeight the tile height
     */
    public static void setTileHeight(final int tileHeight) {
        Dimensions.tileHeight = tileHeight;
    }

    /**
     * @return the background height
     */
    public static int getBackgroundHeight() {
        return backgroundHeight;
    }

    /**
     * @param backgroundHeight the background height
     */
    public static void setBackgroundHeight(final int backgroundHeight) {
        Dimensions.backgroundHeight = backgroundHeight;
    }

    /**
     * @return the background width
     */
    public static int getBackgroundWidth() {
        return backgroundWidth;
    }

    /**
     * @param backgroundWidth the background width
     */
    public static void setBackgroundWidth(final int backgroundWidth) {
        Dimensions.backgroundWidth = backgroundWidth;
    }

    /**
     * @return the background position
     */
    public static Position2D getBackgroundPos() {
        return backgroundPos;
    }

    /**
     * @param backgroundPos the background position
     */
    public static void setBackgroundPos(final Position2D backgroundPos) {
        Dimensions.backgroundPos = backgroundPos;
    }
}
