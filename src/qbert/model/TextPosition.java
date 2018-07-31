package qbert.model;

/**
 * Labels used by model to represent scene GUI slots. A scene could have different GUI styles 
 * in each of those positions.
 */
public enum TextPosition {

    /**
     * The title of the scene.
     */
    TITLE,

    /**
     * The body of the scene.
     */
    CENTER,

    /**
     * The body of the scene left oriented.
     */
    LEFTSIDE,

    /**
     * The title of the scene right oriented.
     */
    RIGHTSIDE,

    /**
     * The page foot.
     */
    FOOT;
}
