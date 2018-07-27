package qbert.model;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Map;

import qbert.model.components.PointComponent;

/**
 * The implementation of {@link LevelSettings}.
 */
public final class LevelSettingsImpl implements LevelSettings {

    private final int colorsNumber;
    private final int disksNumber;
    private final int roundScore;
    private final boolean colorReversible;
    private final float qBertSpeed;
    private final BufferedImage backgroundImage;
    private final Map<Integer, BufferedImage> colorMap;
    private final Map<String, EnemyInfoImpl> mapInfo;

    /**
     * @param colorsNumber the number of colors to be set for each tile
     * @param reverse true if the tile is reversible, false otherwise.
     * @param background the {@link BufferedImage} representing the background image
     * @param colorMap the map containing all the tiles colors
     * @param disksNumber the number of the disks of the current level/round
     * @param mapInfo the map containing enemies information
     * @param qBertSpeed the player speed
     */
    public LevelSettingsImpl(final int colorsNumber, final boolean reverse, final BufferedImage background, 
            final Map<Integer, BufferedImage> colorMap, final int disksNumber, final Map<String, EnemyInfoImpl> mapInfo, final float qBertSpeed) {
        this.colorsNumber = colorsNumber;
        this.colorReversible = reverse;
        this.backgroundImage = background;
        this.colorMap = colorMap;
        this.disksNumber = disksNumber;
        this.mapInfo = mapInfo;
        new PointComponent();
        this.roundScore = PointComponent.ROUND_SCORE;
        this.qBertSpeed = qBertSpeed;
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

    @Override
    public Map<String, EnemyInfoImpl> getMapInfo() {
        return Collections.unmodifiableMap(mapInfo);
    }

    @Override
    public float getQBertSpeed() {
        return this.qBertSpeed;
    }
}
