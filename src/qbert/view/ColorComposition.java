package qbert.view;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * The interface for the management of the combination of the background and tiles colors in the level/round.
 */
public interface ColorComposition {

    /**
     * @param n the number of the tiles colors to be set in the current round
     * @return the map containing all the tiles colors of the current round
     */
    Map<Integer, BufferedImage> getColorComposition(int n);

    /**
     * @return the {@link BufferedImage} representing the background image
     */
    BufferedImage getBackgroundImage();

    /**
     * @return the {@link BufferedImage} representing the target color
     */
    BufferedImage getTargetColor();

}
