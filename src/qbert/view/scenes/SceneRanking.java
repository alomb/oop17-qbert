package qbert.view.scenes;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Optional;

import qbert.controller.Controller;
import qbert.controller.input.Confirm;
import qbert.model.models.TextPosition;

/**
 * An implementation of {@link Scene} for the game menu scene.
 */
public class SceneRanking extends SceneImpl {

    private final Controller controller;

    /**
     * @param w
     *            the panel width
     * @param h
     *            the panel height
     * @param controller
     *            the game controller
     */
    public SceneRanking(final int w, final int h, final Controller controller) {
        super(w, h, controller);
        this.setBackground(Color.BLACK);

        this.controller = controller;

        this.addSection(TextPosition.TITLE, 
                new GUISectionImpl(SceneColor.YELLOW.getColor(), Optional.empty(), 50, 20, true, TextSize.LARGE));
        
        this.addSection(TextPosition.CENTER, 
                new GUISectionImpl(SceneColor.GREEN.getColor(), Optional.of(SceneColor.RED.getColor()), 50, 30, true, TextSize.SMALL));
        
        this.addSection(TextPosition.FOOT, 
                new GUISectionImpl(SceneColor.GREEN.getColor(), Optional.empty(), 50, 80, true, TextSize.SMALL));
    }

    @Override
    public final void keyTyped(final KeyEvent e) {

    }

    @Override
    public final void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.controller.notifyCommand(new Confirm());
        }
    }

    @Override
    public final void keyReleased(final KeyEvent e) {

    }
}
