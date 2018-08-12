package qbert.view.scenes;

import java.awt.Color;
import java.util.Optional;

/**
 * A data container for GUI graphical details. A {@link Scene} could use more {@link GUISection}
 * to have different styles for different parts of the GUI e.g: Title, Body..
 */
public interface GUISection {

    /**
     * @return the main text color.
     */
    Color getColor();

    /**
     * @return the color for selected text
     */
    Optional<Color> getSelectedColor();

    /**
     * @return the section X position offset in the screen
     */
    int getXOffset();

    /**
     * @return the section Y position offset in the screen
     */
    int getYOffset();

    /**
     * @return true if the text is centered
     */
    boolean isCentered();

    /**
     * @return the section {@link TextSize}
     */
    TextSize getSize();

}
