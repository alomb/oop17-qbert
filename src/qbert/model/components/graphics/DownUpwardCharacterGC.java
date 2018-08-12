package qbert.model.components.graphics;

import qbert.model.sprites.OneSideCharacterSprites;

/**
 * An interface that extends {@link CharacterGC} to add new functionalities
 * for upward moving animation.
 */
public interface DownUpwardCharacterGC extends CharacterGC {

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
     * @return the {@link OneSideCharacterSprites} containing the front sprites
     */
    OneSideCharacterSprites getFrontSprites();

    /**
     * @param frontSprites the new {@link OneSideCharacterSprites} for front sprites
     */
    void setFrontSprites(OneSideCharacterSprites frontSprites);

    /**
     * Set the {@link DownUpwardCharacter} relative moving up-right animation.
     */
    void setMoveUpLeftAnimation();

    /**
     * Set the {@link DownUpwardCharacter} relative moving up-right animation.
     */
    void setMoveUpRightAnimation();

}
