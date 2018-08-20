package qbert.view.scenes;

import java.awt.event.KeyEvent;
import java.util.Optional;

import qbert.controller.Controller;
import qbert.controller.input.Confirm;
import qbert.controller.input.MoveDown;
import qbert.controller.input.MoveUp;
import qbert.model.scenes.TextPosition;

/**
 * An implementation of {@link Scene} for the game menu scene.
 */
public class SceneMenu extends SceneImpl {

    /**
     * 
     */
    private static final long serialVersionUID = -4717362331792893224L;

    private final Controller controller;

    /**
     * @param w
     *            the panel width
     * @param h
     *            the panel height
     * @param controller
     *            the game controller
     */
    public SceneMenu(final int w, final int h, final Controller controller) {
        super(w, h, controller);
        this.setBackground(SceneColor.BLUE.getColor());

        this.controller = controller;

        final int xOffset = 50;
        final int titleYOffset = 10;
        final int centerYOffset = 50;
        final int footYOffset = 90;

        this.addSection(TextPosition.TITLE,
                new GUISectionImpl(SceneColor.YELLOW.getColor(), Optional.empty(), xOffset, titleYOffset, true, TextSize.LARGE));
        this.addSection(TextPosition.CENTER, 
                new GUISectionImpl(SceneColor.GREEN.getColor(), Optional.of(SceneColor.RED.getColor()), xOffset, centerYOffset, true, TextSize.MEDIUM));
        this.addSection(TextPosition.FOOT,
                new GUISectionImpl(SceneColor.GREEN.getColor(), Optional.empty(), xOffset, footYOffset, true, TextSize.SMALL));
    }

    @Override
    public final void keyTyped(final KeyEvent e) {

    }

    @Override
    public final void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_KP_UP) {
            this.controller.notifyCommand(new MoveUp());
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_KP_DOWN) {
            this.controller.notifyCommand(new MoveDown());
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.controller.notifyCommand(new Confirm());
        }
    }

    @Override
    public final void keyReleased(final KeyEvent e) {

    }
}
