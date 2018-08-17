package qbert.view.scenes;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Optional;

import javax.swing.JTextField;

import qbert.controller.Controller;
import qbert.controller.input.Confirm;
import qbert.controller.input.MoveDown;
import qbert.controller.input.MoveUp;
import qbert.model.models.TextPosition;

/**
 * An implementation of {@link Scene} for the game menu scene.
 */
public class SceneGameOver extends SceneImpl {

    private final Controller controller;
    private final JTextField text = new JTextField();

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

        this.addSection(TextPosition.TITLE,
                new GUISectionImpl(SceneColor.YELLOW.getColor(), Optional.empty(), 50, 20, true, TextSize.LARGE));
        this.addSection(TextPosition.CENTER,
                new GUISectionImpl(SceneColor.GREEN.getColor(), Optional.of(SceneColor.RED.getColor()), 50, 50, true, TextSize.SMALL));
        this.addSection(TextPosition.FOOT,
                new GUISectionImpl(SceneColor.GREEN.getColor(), Optional.empty(), 50, 20, true, TextSize.SMALL));

        this.add(text);
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
        }
    }

    @Override
    public final void keyReleased(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.controller.notifyCommand(new Confirm());
        }
    }
}
