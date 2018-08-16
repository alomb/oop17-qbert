package qbert.view.scenes;

import java.awt.Color;
import java.util.Optional;

import qbert.model.utilities.Dimensions;

/**
 * The implementation of {@link GUISection}.
 */
public class GUISectionImpl implements GUISection {

    //Text color properties
    private final Color color;
    private final Optional<Color> selectedColor;

    //Text position properties
    private final float xOffset;
    private final float yOffset;

    //Text style properties
    private final boolean centered;
    private final TextSize textSize;

    private static final int HUNDRED_PERCENT = 100;
    private static final int FIFTY_PERCENT = 50;

    /**
     * @param color text color
     * @param selectedColor selected text color
     * @param xOffset X section position offset in percentage of the window width
     * @param yOffset Y section position offset in percentage of the window height
     * @param centered true if the text is centered
     * @param textSize the {@link TextSize} of this GUI
     */
    public GUISectionImpl(final Color color, final Optional<Color> selectedColor, 
            final float xOffset, final float yOffset, final boolean centered, final TextSize textSize) {
        this.color = color;
        this.selectedColor = selectedColor;
        this.xOffset = (xOffset < 0 ? 0 : xOffset) > HUNDRED_PERCENT ? HUNDRED_PERCENT : xOffset;
        this.yOffset = (yOffset < 0 ? 0 : yOffset) > HUNDRED_PERCENT ? HUNDRED_PERCENT : yOffset;
        this.centered = centered;
        this.textSize = textSize;
    }

    @Override
    public final Color getColor() {
        return color;
    }

    @Override
    public final Optional<Color> getSelectedColor() {
        return selectedColor;
    }

    @Override
    public final int getXOffset() {
        float finalXOffset = this.xOffset;
        if (this.centered) {
            finalXOffset -= FIFTY_PERCENT;
        }
        return Math.round(finalXOffset * Dimensions.getWindowWidth() / HUNDRED_PERCENT);
    }

    @Override
    public final int getYOffset() {
        float finalYOffset = this.yOffset;
        if (this.centered) {
            finalYOffset -= FIFTY_PERCENT;
        }
        return Math.round(finalYOffset * Dimensions.getWindowHeight() / HUNDRED_PERCENT);
    }

    @Override
    public final boolean isCentered() {
        return this.centered;
    }

    @Override
    public final TextSize getSize() {
        return this.textSize;
    }
}
