package qbert.view.scenes;

import java.awt.Color;

/**
 * @author Alessandro
 *
 */
public enum SceneColor {

    /**
     * The blue tone used in the application.
     */
    BLUE(new Color(38, 47, 124)),

    /**
     * The yellow tone used in the application.
     */
    YELLOW(new Color(237, 228, 61)),

    /**
     * The red tone used in the application.
     */
    RED(new Color(255, 0, 0)),

    /**
     * The green tone used in the application.
     */
    GREEN(new Color(86, 168, 26));

    private Color color;

    SceneColor(final Color color) {
        this.color = color;
    }

    Color getColor() {
        return this.color;
    }

}
