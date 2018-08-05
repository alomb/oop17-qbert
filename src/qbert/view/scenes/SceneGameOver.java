package qbert.view.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Optional;

import javax.swing.JTextField;

import qbert.controller.Controller;
import qbert.controller.input.Confirm;
import qbert.model.models.TextPosition;
import qbert.model.utilities.Dimensions;

/**
 * An implementation of {@link Scene} for the game menu scene.
 */
public class SceneGameOver extends SceneImpl {

    private final Controller controller;

    private final Color backgroundColor = new Color(38, 47, 124);
    private final Color yellow = new Color(237, 228, 61);
    private final Color green = new Color(86, 168, 26);
    private final Color red = new Color(255, 0, 0);
    private JTextField text = new JTextField();
    /**
     * @param w
     *            the panel width
     * @param h
     *            the panel height
     * @param controller
     *            the game controller
     */
    public SceneGameOver(final int w, final int h, final Controller controller) {
        super(w, h);
        this.setBackground(this.backgroundColor);

        this.controller = controller;

        this.addSection(TextPosition.TITLE, 
                new GUISectionImpl(this.yellow, Optional.empty(), 0, -Math.round(Dimensions.getWindowHeight() / 2.5f), true, TextSize.LARGE));
        this.addSection(TextPosition.CENTER, 
                new GUISectionImpl(this.green, Optional.of(this.red), 0, Math.round(Dimensions.getWindowHeight() / 8.5f), true, TextSize.SMALL));
        this.addSection(TextPosition.FOOT, 
                new GUISectionImpl(this.green, Optional.empty(), 0, Math.round(Dimensions.getWindowHeight() / 2.5f), true, TextSize.SMALL));

        this.add(text);
        this.setVisible(true);

    }

    @Override
    public final void draw(final Graphics g) {
        this.controller.getRenderables().stream().sorted((a, b) -> a.getZIndex() - b.getZIndex()).forEach(c -> {
            g.drawImage(c.getGraphicComponent().getSprite(), c.getGraphicComponent().getPosition().getX(),
                    c.getGraphicComponent().getPosition().getY(), this);
        });
        this.controller.getGUI().forEach(gui -> this.drawGUI(g, gui));
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
