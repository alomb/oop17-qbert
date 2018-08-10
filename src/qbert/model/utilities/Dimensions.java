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
     * Number of columns in the map.
     */
    public static final int MAP_COLUMNS = 27;

    /**
     * Number of rows in the map.
     */
    public static final int MAP_ROWS = 14;

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

    private static int screenHeight;
    private static int screenWidth;
    private static int windowHeight;
    private static int windowWidth;
    private static int deathHeight;
    private static int sideDeathHeight;
    private static int spawningHeight;
    private static int sideSpawningHeight;
    private static int cubeHeight;
    private static int cubeWidth;
    private static int tileHeight;
    private static int tileWidth;
    private static int backgroundHeight;
    private static int backgroundWidth;
    private static int backgroundX;
    private static int backgroundY;

    private static Position2D spawningPointLeft;
    private static Position2D spawningPointRight;
    private static Position2D spawningQBert;

    private static Position2D spawningLogPointLeft;
    private static Position2D spawningLogPointRight;
    private static Position2D spawningLogQBert;

    /**
     * @return the screen height
     */
    public static int getScreenHeight() {
        return screenHeight;
    }

    /**
     * @param sHeight the screen height
     */
    public static void setScreenHeight(final int sHeight) {
         screenHeight = sHeight;
    }

    /**
     * @return the screen width
     */
    public static int getScreenWidth() {
        return screenWidth;
    }

    /**
     * @param sWidth the screen width
     */
    public static void setScreenWidth(final int sWidth) {
         screenWidth = sWidth;
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
     * @return the death height
     */
    public static int getDeathHeight() {
        return deathHeight;
    }

    /**
     * @param deathHeight the death height
     */
    public static void setDeathHeight(final int deathHeight) {
        Dimensions.deathHeight = deathHeight;
    }

    /**
     * @return the death height
     */
    public static int getSideDeathHeight() {
        return sideDeathHeight;
    }

    /**
     * @param sideDeathHeight the death height
     */
    public static void setSideDeathHeight(final int sideDeathHeight) {
        Dimensions.sideDeathHeight = sideDeathHeight;
    }

    /**
     * @return the spawning height
     */
    public static int getSpawningHeight() {
        return spawningHeight;
    }

    /**
     * @param spawningHeight the spawning height
     */
    public static void setSpawningHeight(final int spawningHeight) {
        Dimensions.spawningHeight = spawningHeight;
    }

    /**
     * @return the spawning height
     */
    public static int getSideSpawningHeight() {
        return sideSpawningHeight;
    }

    /**
     * @param sideSpawningHeight the spawning height for characters who spawn laterally
     */
    public static void setSideSpawningHeight(final int sideSpawningHeight) {
        Dimensions.sideSpawningHeight = sideSpawningHeight;
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
     * @return the tile width
     */
    public static int getTileWidth() {
        return tileWidth;
    }

    /**
     * @param tileWidth the tile width
     */
    public static void setTileWidth(final int tileWidth) {
        Dimensions.tileWidth = tileWidth;
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
     * @return the background X axis value
     */
    public static int getBackgroundX() {
        return backgroundX;
    }

    /**
     * @param backgroundX the background X axis value
     */
    public static void setBackgroundX(final int backgroundX) {
        Dimensions.backgroundX = backgroundX;
    }

    /**
     * @return the background Y axis value
     */
    public static int getBackgroundY() {
        return backgroundY;
    }

    /**
     * @param backgroundY the background Y axis value
     */
    public static void setBackgroundY(final int backgroundY) {
        Dimensions.backgroundY = backgroundY;
    }

    /**
     * @return the {@Position2D} representing the left spawning physical position
     */
    public static Position2D getSpawningPointLeft() {
        return spawningPointLeft;
    }

    /**
     * @param spawningPointLeft the {@link Position2D} representing the left spawning physical position
     */
    public static void setSpawningPointLeft(final Position2D spawningPointLeft) {
        Dimensions.spawningPointLeft = spawningPointLeft;
    }

    /**
     * @return the {@Position2D} representing the right spawning physical position
     */
    public static Position2D getSpawningPointRight() {
        return spawningPointRight;
    }

    /**
     * @param spawningPointRight the {@link Position2D} representing the right spawning physical position
     */
    public static void setSpawningPointRight(final Position2D spawningPointRight) {
        Dimensions.spawningPointRight = spawningPointRight;
    }

    /**
     * @return the {@Position2D} representing the Qbert spawning physical position
     */
    public static Position2D getSpawningQBert() {
        return spawningQBert;
    }

    /**
     * @param spawningQBert the {@link Position2D} representing the QBert spawning physical position
     */
    public static void setSpawningQBert(final Position2D spawningQBert) {
        Dimensions.spawningQBert = spawningQBert;
    }

    /**
     * @return the {@Position2D} representing the left spawning logical position
     */
    public static Position2D getSpawningLogPointLeft() {
        return spawningLogPointLeft;
    }

    /**
     * @param spawningLogPointLeft the {@link Position2D} representing the left spawning logical position
     */
    public static void setSpawningLogPointLeft(final Position2D spawningLogPointLeft) {
        Dimensions.spawningLogPointLeft = spawningLogPointLeft;
    }

    /**
     * @return the {@Position2D} representing the right spawning logical position
     */
    public static Position2D getSpawningLogPointRight() {
        return spawningLogPointRight;
    }

    /**
     * @param spawningLogPointRight the {@link Position2D} representing the right spawning logical position
     */
    public static void setSpawningLogPointRight(final Position2D spawningLogPointRight) {
        Dimensions.spawningLogPointRight = spawningLogPointRight;
    }

    /**
     * @return the {@Position2D} representing the Qbert spawning logical position
     */
    public static Position2D getSpawningLogQBert() {
        return spawningLogQBert;
    }

    /**
     * @param spawningLogQBert the {@link Position2D} representing the QBert spawning logical position
     */
    public static void setSpawningLogQBert(final Position2D spawningLogQBert) {
        Dimensions.spawningLogQBert = spawningLogQBert;
    }

    private Dimensions() {

    }
}
