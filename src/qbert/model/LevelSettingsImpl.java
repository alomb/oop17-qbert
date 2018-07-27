package qbert.model;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * The implementation of {@link LevelSettings}.
 */
public final class LevelSettingsImpl implements LevelSettings {

    private final int colorsNumber;
    private final int disksNumber;
    private final int roundScore;
    private final boolean colorReversible;
    private final BufferedImage backgroundImage;
    private final Map<Integer, BufferedImage> colorMap;

    /**
     * @param colorsNumber the number of colors to be set for each tile
     * @param reverse true if the tile is reversible, false otherwise.
     * @param background the {@link BufferedImage} representing the background image
     * @param colorMap the map containing all the tiles colors
     * @param disksNumber the number of the disks of the current level/round
     */
    public LevelSettingsImpl(final int colorsNumber, final boolean reverse, final BufferedImage background, final Map<Integer, BufferedImage> colorMap, final int disksNumber) {
        this.colorsNumber = colorsNumber;
        this.colorReversible = reverse;
        this.backgroundImage = background;
        this.colorMap = colorMap;
        //TODO: Make those parameters dynamic
        this.disksNumber = disksNumber;
        this.roundScore = 1500;
    }

    @Override
    public int getColorsNumber() {
        return this.colorsNumber;
    }

    @Override
    public int getDisksNumber() {
        return this.disksNumber;
    }

    @Override
    public int getRoundScore() {
        return this.roundScore;
    }

    @Override
    public boolean isReversible() {
        return this.colorReversible;
    }

    @Override
    public BufferedImage getBackgroundImage() {
        return this.backgroundImage;
    }

    @Override
    public Map<Integer, BufferedImage> getColorMap() {
        return this.colorMap;
    }
}
