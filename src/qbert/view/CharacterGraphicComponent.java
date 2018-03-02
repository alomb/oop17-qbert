package qbert.view;

import qbert.view.animations.Animation;

/**
 * This interface is used to manage the animations of a {@link Character}.
 */
public interface CharacterGraphicComponent extends GraphicComponent {

    /**
     * @return the current {@link Animation}
     */
    Animation getCurrentAnimation();

    /**
     * Set the Character standing animation.
     */
    void setStandingAnimation();

    /**
     * Set the Character spawning animation.
     */
    void setSpawnAnimation();

    /**
     * Set the Character falling animation.
     */
    void setFallAnimation();

    /**
     * Set the Character moving down-left animation.
     */
    void setMoveDownLeftAnimation();

    /**
     * Set the Character moving down-right animation.
     */
    void setMoveDownRightAnimation();

    /**
     * Set the Character moving up-right animation.
     */
    void setMoveUpLeftAnimation();

    /**
     * Set the Character moving up-right animation.
     */
    void setMoveUpRightAnimation();

    /**
     * This function is called to update the graphics, e.g., the current {@link Animation}.
     * @param graphicsSpeed the graphics' speed calculated from the time passed since the last game cycle 
     */
    void updateGraphics(float graphicsSpeed);

}
