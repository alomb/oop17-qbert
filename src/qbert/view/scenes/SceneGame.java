package qbert.view.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Optional;

import qbert.controller.Controller;
import qbert.controller.input.Confirm;
import qbert.controller.input.MoveDown;
import qbert.controller.input.MoveLeft;
import qbert.controller.input.MoveRight;
import qbert.controller.input.MoveUp;
import qbert.model.models.TextPosition;
import qbert.model.models.TextSize;
import qbert.model.utilities.Dimensions;

/**
 * An implementation of {@link Scene} for the gameplay.
 */
public class SceneGame extends SceneImpl {
 
    private final Controller controller;

    private final Color backgroundColor = new Color(0, 0, 0);
    private final Color textColor = new Color(255, 255, 255);

    /**
     * @param w the panel width
     * @param h the panel height
     * @param controller the game controller
     */
    public SceneGame(final int w, final int h, final Controller controller) {
        super(w, h);
        this.setBackground(this.backgroundColor);
        this.controller = controller;

        this.addSection(TextPosition.LEFTSIDE, 
                new GUISectionImpl(this.textColor, Optional.empty(), Math.round(Dimensions.getWindowWidth() / 10f), Math.round(Dimensions.getWindowHeight() / 8f), false, TextSize.SMALL));
        this.addSection(TextPosition.RIGHTSIDE, 
                new GUISectionImpl(this.textColor, Optional.empty(), Math.round(Dimensions.getWindowWidth() / 1.25f), Math.round(Dimensions.getWindowHeight() / 8f), false, TextSize.SMALL));
    }

    @Override
    public final void draw(final Graphics g) {
        this.controller.getRenderables().stream().sorted((a, b) -> a.getZIndex() - b.getZIndex()).forEach(c -> {
            g.drawImage(c.getGraphicComponent().getSprite(), c.getGraphicComponent().getPosition().getX(), c.getGraphicComponent().getPosition().getY(), this);
        });

        this.controller.getGUI().forEach(gui -> this.drawGUI(g, gui));
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
