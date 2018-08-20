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
import qbert.model.scenes.TextPosition;

/**
 * An implementation of {@link Scene} for the gameplay.
 */
public class SceneGame extends SceneImpl {
 
    /**
     * 
     */
    private static final long serialVersionUID = -5098983398542021297L;

    private final Controller controller;

    /**
     * @param w the panel width
     * @param h the panel height
     * @param controller the game controller
     */
    public SceneGame(final int w, final int h, final Controller controller) {
        super(w, h, controller);
        this.setBackground(Color.BLACK);
        this.controller = controller;

        final int letfXOffset = 5;
        final int rightXOffset = 85;
        final int yOffset = 10;

        this.addSection(TextPosition.LEFTSIDE, 
                new GUISectionImpl(Color.WHITE, Optional.empty(), letfXOffset, yOffset, false, TextSize.SMALL));
        this.addSection(TextPosition.RIGHTSIDE,
                new GUISectionImpl(Color.WHITE, Optional.empty(), rightXOffset, yOffset, false, TextSize.SMALL));
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
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            //To win levels for debug
            this.controller.notifyCommand(new Confirm());
        }
    }

    @Override
    public final void keyReleased(final KeyEvent e) {

    }
}
