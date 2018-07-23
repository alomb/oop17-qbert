package qbert.model;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * The interface for the management of the settings of the current level/round.
 */
public interface LevelSettings {

    /**
     * @return the number of colors to be set for each tile for the current level/round
     */
    int getColorsNumber();

    /**
     * @return the number of disks to be set for the current level/round
     */
    int getDisksNumber();

    /**
     * @return the number of points the player gets after winning the round
     */
    int getRoundScore();

    /**
     * @return true if the tile is reversible, false otherwise.
     */
    boolean isReversible();

    /**
     * @return the {@link BufferedImage} representing the background image
     */
    BufferedImage getBackgroundImage();

    /**
     * @return the map containing all the tiles colors of the current level/round
     */
    Map<Integer, BufferedImage> getColorMap();

}
