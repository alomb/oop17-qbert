package qbert.view;

import java.awt.Graphics;
import java.awt.event.KeyListener;

/**
 * 
 */
public interface Scene extends KeyListener {

    /**
     * A method used when the scene is changed to restore the process of keystroke events.
     */
    void focus();

    /**
     * The method used to render the scene.
     */
    void render();

    /**
     * @param g the {@link Graphics} fo the scene
     */
    void draw(Graphics g);

}
