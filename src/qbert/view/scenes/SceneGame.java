package qbert.view.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import qbert.controller.Controller;
import qbert.input.MoveDown;
import qbert.input.MoveLeft;
import qbert.input.MoveRight;
import qbert.input.MoveUp;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Sprites;

/**
 * An implementation of {@link Scene} for the gameplay.
 */
public class SceneGame extends SceneImpl {

    private Font custom; 
    private final BufferedImage lifeSprite;

    private final Controller controller;

    /**
     * @param w the panel width
     * @param h the panel height
     * @param controller the game controller
     */
    public SceneGame(final int w, final int h, final Controller controller) {
        super(w, h);
        this.controller = controller;
        this.setBackground(Color.black);

        // Temporary Font
        try {
            final URL url = getClass().getResource("/arcade_n.ttf");
            final File fontFile = new File(url.getPath());
            this.custom = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont((float) Dimensions.getWindowHeight() / 50);
            final GraphicsEnvironment ge = 
                GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(this.custom);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Temporary Sprite Loading
        this.lifeSprite = Sprites.life;
    }

    @Override
    public final void draw(final Graphics g) {
        this.controller.getRenderables().stream().sorted((a, b) -> a.getZIndex() - b.getZIndex()).forEach(c -> {
            g.drawImage(c.getGraphicComponent().getSprite(), c.getGraphicComponent().getPosition().getX(), c.getGraphicComponent().getPosition().getY(), this);
        });

        /*
        // Info rendering
        g.setColor(new Color(255, 255, 255));
        g.setFont(this.custom);
        g.drawString("Player:", 40, 40);
        g.drawString("" + this.controller.getModel().getPoints(), 40, 80);

        g.drawString("Level:", 1200, 300);
        g.drawString("" + this.controller.getModel().getLevelNumber(), 1400, 300);

        g.drawString("Round:", 1200, 330);
        g.drawString("" + this.controller.getModel().getRoundNumber(), 1400, 330);

        for (int i = 0, posY = 0; i < this.controller.getModel().getLives(); i++, posY += this.lifeSprite.getHeight() + 10) {
            g.drawImage(this.lifeSprite, 60, 190 + posY, this);
        }

        g.drawString("Hi-Score:", 1000, 40);
        g.drawString("1500", 1200, 40);

        //Temporary debug options
        int unit = Dimensions.getWindowWidth() / 48;
        g.drawString("DEBUG:", unit * 2, unit * 20);
        g.drawString("L - Gain Life", unit * 2, unit * 20 + unit);
        g.drawString("D - Loose Life", unit * 2, unit * 20 + unit * 2);
        g.drawString("N - Go to next level", unit * 2, unit * 20 + unit * 6);
        g.drawString("Q - Quit", unit * 2, unit * 20 + unit * 7);*/
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
        }
    }

    @Override
    public final void keyReleased(final KeyEvent e) {

    }
}
