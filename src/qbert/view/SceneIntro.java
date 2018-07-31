package qbert.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Optional;

import qbert.controller.Controller;
import qbert.controller.Sounds;
import qbert.model.Model;
import qbert.model.TextPosition;
import qbert.model.utilities.Dimensions;

/**
 * An implementation of {@link Scene} for the game introductive scene.
 */
public class SceneIntro extends SceneImpl {

    private final Model introduction;
    private final Controller controller;

    private final Color backgroundColor = new Color(38, 47, 124);
    private final Color yellow = new Color(237, 228, 61);
    private final Color green = new Color(86, 168, 26);

    /**
     * @param introduction the model associated
     * @param w the panel width
     * @param h the panel height
     * @param controller the game controller
     */
    public SceneIntro(final Model introduction, final int w, final int h, final Controller controller) {
        super(w, h);
        this.setBackground(this.backgroundColor);

        this.introduction = introduction;
        this.controller = controller;

        Sounds.playSound("InsertACoin.wav");

        this.addSection(TextPosition.TITLE, new GUISectionImpl(this.yellow, Optional.empty(), 0, -Math.round(Dimensions.getWindowHeight() / 2.5f)));
        this.addSection(TextPosition.RIGHTSIDE, new GUISectionImpl(this.green, Optional.empty(), Math.round(Dimensions.getWindowWidth() / 1.75f), Math.round(Dimensions.getWindowHeight() / 3.5f)));
        this.addSection(TextPosition.FOOT, new GUISectionImpl(this.green, Optional.empty(), 0, Math.round(Dimensions.getWindowHeight() / 2.5f)));
    }

    @Override
    public final void draw(final Graphics g) {

        this.introduction.getRenderables().stream().sorted((a, b) -> a.getZIndex() - b.getZIndex()).forEach(c -> {
            g.drawImage(c.getGraphicComponent().getSprite(), c.getGraphicComponent().getPosition().getX(), c.getGraphicComponent().getPosition().getY(), this);
        });

        this.introduction.getGUI().forEach(gui -> this.drawString(g, gui));
    }
    @Override
    public final void keyTyped(final KeyEvent e) {

    }

    @Override
    public final void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.introduction.confirm();

            if (this.introduction.hasFinished()) {
                this.controller.changeScene();
            }
        }
    }

    @Override
    public final void keyReleased(final KeyEvent e) {

    }
}
