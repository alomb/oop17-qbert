package qbert.view.scenes;

import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.Optional;

import qbert.model.scenes.TextPosition;

/**
 * The interface for a common application scene.
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
     * @param g the {@link Graphics} for the scene
     */
    void draw(Graphics g);

    /**
     * @param position the {@link TextPosition} an index of the map ({@link TextPosition}, {@link Optional}<{@link GUISectionImpl}>)
     * @return the {@link Optional} of {@link GUISectionImpl} mapped to the inserted {@link TextPosition}. If there isn't the key or there isn't 
     * the value return {@link Optional}.empty()
     */
    Optional<GUISection> getSection(TextPosition position);

    /**
     * @param position a new {@link TextPosition}
     * @param section the relative {@link GUISectionImpl}
     */
    void addSection(TextPosition position, GUISectionImpl section);

}
