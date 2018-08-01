package qbert.view.scenes;

import java.awt.Color;
import java.util.Optional;

/**
 * The implementation of {@link GUISection}.
 */
public class GUISectionImpl implements GUISection {

    private final Color color;
    private final Optional<Color> selectedColor;

    private final int xOffset;
    private final int yOffset;

    /**
     * @param color text color
     * @param selectedColor selected text color
     * @param xOffset X section position offset
     * @param yOffset Y section position offset
     */
    public GUISectionImpl(final Color color, final Optional<Color> selectedColor, final int xOffset, final int yOffset) {
        this.color = color;
        this.selectedColor = selectedColor;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
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
}
