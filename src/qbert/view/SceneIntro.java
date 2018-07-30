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
import qbert.controller.Sounds;
import qbert.model.Introduction;
import qbert.model.utilities.Dimensions;

/**
 * An implementation of {@link Scene} for the game introductive scene.
 */
public class SceneIntro extends SceneImpl {

    private final Introduction introduction;
    private final Controller controller;
    private Font title, text;

    private final int istructionsYOffset = +Math.round(Dimensions.getWindowHeight() / 3.5f);
    private final int istructionsYStep = +Math.round(Dimensions.getWindowHeight() / 60f);

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
            this.text = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont((float) Dimensions.getWindowHeight() / 60f);
            ge.registerFont(this.text);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.introduction = introduction;
        this.controller = controller;

        Sounds.playSound("InsertACoin.wav");
    }

    @Override
    public final void draw(final Graphics g) {

        this.introduction.getRenderables().stream().sorted((a, b) -> a.getZIndex() - b.getZIndex()).forEach(c -> {
            g.drawImage(c.getGraphicComponent().getSprite(), c.getGraphicComponent().getPosition().getX(), c.getGraphicComponent().getPosition().getY(), this);
        });

        g.setColor(new Color(237, 228, 61));
        this.drawCenteredString(g, "Q*bert", 
                new Rectangle(0, -Math.round(Dimensions.getWindowHeight() / 2.5f), Dimensions.getWindowWidth(), Dimensions.getWindowHeight()), title);
 
        g.setColor(new Color(86, 168, 26));

        int istructionsParagraphStep = 0;

        g.setFont(text);
        for (int i = 0; i < this.introduction.getInstructionsIndex(); i++) {
            if (i % 3 == 0) {
                istructionsParagraphStep += this.istructionsYStep * 2;
            }

            g.drawString(this.introduction.getInstructions().get(i), Math.round(Dimensions.getWindowWidth() / 1.75f), this.istructionsYOffset + istructionsParagraphStep + this.istructionsYStep * i * 2);
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
            this.introduction.confirm();

            if (this.introduction.hasReachedTheEnd()) {
                this.controller.changeScene();
                Sounds.playSound("GameStartMusic.wav");
            } else {
                Sounds.playSound("QbertHops.wav");
            }
        }
    }

    @Override
    public final void keyReleased(final KeyEvent e) {

    }
}
