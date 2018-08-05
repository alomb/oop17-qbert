package qbert.view.scenes;

import java.awt.Color;
import java.util.Optional;

import qbert.model.models.TextSize;

/**
 * The implementation of {@link GUISection}.
 */
public class GUISectionImpl implements GUISection {

    //Text color properties
    private final Color color;
    private final Optional<Color> selectedColor;

    //Text position properties
    private final int xOffset;
    private final int yOffset;

    //Text style properties
    private final boolean centered;
    private final TextSize textSize;

    /**
     * @param color text color
     * @param selectedColor selected text color
     * @param xOffset X section position offset
     * @param yOffset Y section position offset
     * @param centered true if the text is centered
     * @param textSize the {@link TextSize} of this GUI
     */
    public GUISectionImpl(final Color color, final Optional<Color> selectedColor, 
            final int xOffset, final int yOffset, final boolean centered, final TextSize textSize) {
        this.color = color;
        this.selectedColor = selectedColor;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
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
        return xOffset;
    }

    @Override
    public final int getYOffset() {
        return yOffset;
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
