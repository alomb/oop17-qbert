package qbert.view;

import qbert.view.animations.Animation;

public interface CharacterGraphicComponent extends GraphicComponent {

    /**
     * @return the current {@link Animation}
     */
    Animation getCurrentAnimation();

    /**
     * This function is called to update the graphics, e.g., the current {@link Animation}.
     * @param graphicsSpeed the graphics' speed calculated from the time passed since the last game cycle 
     */
    void updateGraphics(float graphicsSpeed);

}
