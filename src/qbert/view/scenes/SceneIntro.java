package qbert.view.scenes;

import java.awt.event.KeyEvent;
import java.util.Optional;

import qbert.controller.Controller;
import qbert.controller.input.Confirm;
import qbert.model.models.TextPosition;

/**
 * An implementation of {@link Scene} for the game introductive scene.
 */
public class SceneIntro extends SceneImpl {

    /**
     * 
     */
    private static final long serialVersionUID = -1970238458959862998L;

    private final Controller controller;

    /**
     * @param w the panel width
     * @param h the panel height
     * @param controller the game controller
     */
    public SceneIntro(final int w, final int h, final Controller controller) {
        super(w, h, controller);
        this.setBackground(SceneColor.BLUE.getColor());

        this.controller = controller;

        final int xOffset = 50;
        final int titleYOffset = 10;
        final int centerYOffset = 22;
        final int footYOffset = 90;

        this.addSection(TextPosition.TITLE,
                new GUISectionImpl(SceneColor.YELLOW.getColor(), Optional.empty(), xOffset, titleYOffset, true, TextSize.LARGE));
        this.addSection(TextPosition.RIGHTSIDE,
                new GUISectionImpl(SceneColor.GREEN.getColor(), Optional.empty(), xOffset, centerYOffset, false, TextSize.SMALL));
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
