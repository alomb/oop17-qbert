package qbert.model;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * The class containing the settings of the current level/round.
 */
public class LevelSettings {

    private final int colorsNumber;
    private final int disksNumber;
    private final int roundScore;
    private final boolean colorReversible;
    private final BufferedImage backgroundImage;
    private final Map<Integer, BufferedImage> colorMap;
    private final BufferedImage targetColor;

    /**
     * @param number the number of colors to be set for each tile
     * @param reverse true if the tile is reversible, false otherwise.
     * @param background the {@link BufferedImage} representing the background image
     * @param colorMap the map containing all the tiles colors
     * @param targetColor the {@link BufferedImage} representing the target color
     */
    public LevelSettings(final int number, final boolean reverse, final BufferedImage background, final Map<Integer, BufferedImage> colorMap, final BufferedImage targetColor) {
        this.colorsNumber = number;
        this.colorReversible = reverse;
        this.backgroundImage = background;
        this.colorMap = colorMap;
        this.targetColor = targetColor;
        this.disksNumber = 3;
        this.roundScore = 1500;
    }

    /**
     * @return the number of colors to be set for each tile for the current level/round
     */
    public int getColorsNumber() {
        return this.colorsNumber;
    }

    /**
     * @return the number of disks to be set for the current level/round
     */
    public int getDisksNumber() {
        return this.disksNumber;
    }

    /**
     * @return the number of points the player gets after winning the round
     */
    public int getRoundScore() {
        return this.roundScore;
    }

    /**
     * @return true if the tile is reversible, false otherwise.
     */
    public boolean isReversible() {
        return this.colorReversible;
    }

    /**
     * @return the {@link BufferedImage} representing the background image
     */
    public BufferedImage getBackgroundImage() {
        return this.backgroundImage;
    }

    /**
     * @return the map containing all the tiles colors of the current level/round
     */
    public Map<Integer, BufferedImage> getColorMap() {
        return this.colorMap;
    }

    /**
     * @return the {@link BufferedImage} representing the target color
     */
    public BufferedImage getTargetColor() {
        return this.targetColor;
    }
}
