package qbert.view;

import java.awt.Color;
import java.util.Optional;

public class GUISectionImpl {

    private final Color color;
    private Optional<Color> selectedColor;

    private final int xOffset;
    private final int yOffset;

    public GUISectionImpl(final Color color, final Optional<Color> selectedColor, final int xOffset, final int yOffset) {
        this.color = color;
        this.selectedColor = selectedColor;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public final Color getColor() {
        return color;
    }

    public final Optional<Color> getSelectedColor() {
        return selectedColor;
    }

    public final int getXOffset() {
        return xOffset;
    }

    public final int getYOffset() {
        return yOffset;
    }
}
