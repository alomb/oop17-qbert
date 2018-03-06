package qbert.view;

import qbert.view.animations.Animation;

/**
 * This interface extends {@link GraphicComponent} to manage the animations of a {@link Character}.
 */
public interface CharacterGraphicComponent extends GraphicComponent {

    /**
     * @return the current {@link Animation}
     */
    Animation getCurrentAnimation();

    /**
     * Set the {@link Character} relative standing animation.
     */
    void setStandingAnimation();

    /**
     * Set spawning {@link Position2D} to be the current one.
     */
    void setSpawnPosToCurrentPos();

    /**
     * Set the {@link Character} relative spawning animation.
     */
    void setSpawnAnimation();

    /**
     * Set the {@link Character} relative falling animation.
     */
    void setFallAnimation();

    /**
     * Set the {@link Character} relative moving down-left animation.
     */
    void setMoveDownLeftAnimation();

    /**
     * Set the {@link Character} relative moving down-right animation.
     */
    void setMoveDownRightAnimation();

    /**
     * Set the {@link Character} relative moving up-right animation.
     */
    void setMoveUpLeftAnimation();

    /**
     * Set the {@link Character} relative moving up-right animation.
     */
    void setMoveUpRightAnimation();

    /**
     * This function is called to update the graphics, e.g., the current {@link Animation}.
     * @param graphicsSpeed the graphics' speed calculated from the time passed since the last game cycle 
     */
    void updateGraphics(float graphicsSpeed);

}
