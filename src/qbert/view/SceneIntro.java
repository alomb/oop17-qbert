package qbert.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;

import qbert.controller.Controller;
import qbert.model.Introduction;
import qbert.model.utilities.Dimensions;

/**
 * An implementation of {@link Scene} for the game introductive scene.
 */
public class SceneIntro extends SceneImpl {

    private final Introduction introduction;
    private final Controller controller;
    private Font title, text;

    private final int istructionsYOffset = -Math.round(Dimensions.getWindowHeight() / 3.5f);
    private final int istructionsYStep = -Math.round(Dimensions.getWindowHeight() / 50f);

    /**
     * @param w the panel width
     * @param h the panel height
     * @param controller the game controller
     */
    public SceneIntro(final Introduction introduction, final int w, final int h, final Controller controller) {
        super(w, h);
        this.setBackground(new Color(38, 47, 124));
        // Temporary Font
        try {
            final URL url = getClass().getResource("/arcade_n.ttf");
            final File fontFile = new File(url.getPath());
            this.title = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont((float) Dimensions.getWindowHeight() / 10f);
            final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(this.title);
            this.text = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont((float) Dimensions.getWindowHeight() / 50f);
            ge.registerFont(this.text);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.introduction = introduction;
        this.controller = controller;
    }

    @Override
    public final void draw(final Graphics g) {
        g.setColor(new Color(237, 228, 61));
        this.drawCenteredString(g, "Q*bert", 
                new Rectangle(0, -Math.round(Dimensions.getWindowHeight() / 2.5f), Dimensions.getWindowWidth(), Dimensions.getWindowHeight()), title);
 
        g.setColor(new Color(86, 168, 26));

        int istructionsParagraphStep = 0;

        for (int i = 0; i < this.introduction.getInstructionsIndex(); i++) {
            if (i % 3 == 0) {
                istructionsParagraphStep += this.istructionsYStep * 2;
            }
            this.drawCenteredString(g, this.introduction.getInstructions().get(i), 
                    new Rectangle(Math.round(Dimensions.getWindowHeight() / 2.5f), this.istructionsYOffset - istructionsParagraphStep - this.istructionsYStep * i * 2, Dimensions.getWindowWidth(), Dimensions.getWindowHeight()), text);
        }

        this.drawCenteredString(g, "press Enter to continue...", 
                new Rectangle(0, Math.round(Dimensions.getWindowHeight() / 2.5f), Dimensions.getWindowWidth(), Dimensions.getWindowHeight()), text);
    }

    @Override
    public final void keyTyped(final KeyEvent e) {

    }

    @Override
    public final void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (this.introduction.getStep() < this.introduction.getMaxStep()) {
                this.introduction.incrementInstructionsIndex(3);
            } else {
                this.controller.changeScene();
            }
            this.introduction.incrementStep(1);
        }
    }

    @Override
    public final void keyReleased(final KeyEvent e) {

    }

}
