package qbert.view.scenes;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Optional;

import qbert.controller.Controller;
import qbert.controller.input.Confirm;
import qbert.controller.input.MoveDown;
import qbert.controller.input.MoveLeft;
import qbert.controller.input.MoveRight;
import qbert.controller.input.MoveUp;
import qbert.model.models.TextPosition;

/**
 * An implementation of {@link Scene} for the game menu scene.
 */
public class SceneGameOver extends SceneImpl {

    private boolean check = true;

    /**
     * 
     */
    private static final long serialVersionUID = -4285473916004337637L;
    private final Controller controller;

    /**
     * @param w
     *            the panel width
     * @param h
     *            the panel height
     * @param controller
     *            the game controller
     */
    public SceneGameOver(final int w, final int h, final Controller controller) {
        super(w, h, controller);
        this.setBackground(Color.black);

        this.controller = controller;

        final int leftXOffset = 40;
        final int centerXOffset = 50;
        final int rightXOffset = 60;
        final int titleYOffset = 10;
        final int titleXOffset = 50;
        final int centerYOffset = 40;
        final int footYOffset = 90;

        this.addSection(TextPosition.TITLE,
                new GUISectionImpl(SceneColor.YELLOW.getColor(), Optional.empty(), titleXOffset, titleYOffset, true, TextSize.LARGE));
        this.addSection(TextPosition.LEFTSIDE,
                new GUISectionImpl(SceneColor.GREEN.getColor(), Optional.of(SceneColor.RED.getColor()), leftXOffset, centerYOffset, false, TextSize.SMALL));
        this.addSection(TextPosition.CENTER,
                new GUISectionImpl(SceneColor.GREEN.getColor(), Optional.of(SceneColor.RED.getColor()), centerXOffset, centerYOffset, false, TextSize.SMALL));
        this.addSection(TextPosition.RIGHTSIDE,
                new GUISectionImpl(SceneColor.GREEN.getColor(), Optional.of(SceneColor.RED.getColor()), rightXOffset, centerYOffset, false, TextSize.SMALL));
        this.addSection(TextPosition.FOOT,
                new GUISectionImpl(SceneColor.GREEN.getColor(), Optional.empty(), centerXOffset, footYOffset, true, TextSize.SMALL));

        this.setVisible(true);

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
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_KP_RIGHT) {
            this.controller.notifyCommand(new MoveRight());
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_KP_LEFT) {
            this.controller.notifyCommand(new MoveLeft());
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER && check) {
            this.controller.notifyCommand(new Confirm());
            check = false;
        }
    }

    @Override
    public final void keyReleased(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            check = true;
        }
    }
}
