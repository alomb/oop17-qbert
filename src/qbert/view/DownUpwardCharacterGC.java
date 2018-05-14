package qbert.view;

import java.awt.image.BufferedImage;

/**
 * An interface that extends {@link CharacterGraphicComponent} to add new functionalities
 * for upward moving animation.
 */
public interface DownUpwardCharacterGC extends CharacterGraphicComponent {

    /**
     * @return true if the sprite is front oriented
     */
    boolean isFront();

    /**
     * @param front the new sprite front orientation status value
     */
    void setFront(boolean front);

    /**
     * @return true if the sprite is right oriented
     */
    boolean isRight();

    /**
     * @param right the new side orientation status value
     */
    void setRight(boolean right);

    /**
     * @return the current front stand {@link BufferedImage}
     */
    BufferedImage getFrontStandSprite();

    /**
     * @param frontStandSprite the new front stand {@link BufferedImage}
     */
    void setFrontStandSprite(BufferedImage frontStandSprite);

    /**
     * @return the current front move {@link BufferedImage}
     */
    BufferedImage getFrontMoveSprite();

    /**
     * @param frontMoveSprite the new front move {@link BufferedImage}
     */
    void setFrontMoveSprite(BufferedImage frontMoveSprite);

    /**
     * Set the {@link DownUpwardCharacter} relative moving up-right animation.
     */
    void setMoveUpLeftAnimation();

    /**
     * Set the {@link DownUpwardCharacter} relative moving up-right animation.
     */
    void setMoveUpRightAnimation();

}
