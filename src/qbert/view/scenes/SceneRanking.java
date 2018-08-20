package qbert.view.scenes;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Optional;

import qbert.controller.Controller;
import qbert.controller.input.Confirm;
import qbert.model.scenes.TextPosition;

/**
 * An implementation of {@link Scene} for the game menu scene.
 */
public class SceneRanking extends SceneImpl {

    /**
     * 
     */
    private static final long serialVersionUID = 6864029186611923616L;
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

        final int xOffset = 50;
        final int titleYOffset = 10;
        final int centerYOffset = 30;
        final int footYOffset = 90;

        this.addSection(TextPosition.TITLE, 
                new GUISectionImpl(SceneColor.YELLOW.getColor(), Optional.empty(), xOffset, titleYOffset, true, TextSize.LARGE));
        this.addSection(TextPosition.CENTER, 
                new GUISectionImpl(SceneColor.GREEN.getColor(), Optional.empty(), xOffset, centerYOffset, true, TextSize.SMALL));
        this.addSection(TextPosition.FOOT, 
                new GUISectionImpl(SceneColor.GREEN.getColor(), Optional.empty(), xOffset, footYOffset, true, TextSize.SMALL));
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
