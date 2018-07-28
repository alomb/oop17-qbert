package qbert.view;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

/**
 * A generic implementation of {@link Scene}.
 */
public abstract class SceneImpl extends JPanel implements Scene {

    /**
     * @param w the panel width
     * @param h the panel height
     */
    public SceneImpl(final int w, final int h) {
        super();
        this.setSize(w, h);
    }

    @Override
    public final void focus() {
        this.addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        requestFocusInWindow();
    }

    @Override
    public final void render() {
        this.repaint();
    }

    @Override
    protected final void paintComponent(final Graphics g) {
        super.paintComponent(g);
        this.draw(g);
    }

    @Override
    public abstract void draw(Graphics g);

    @Override
    public abstract void keyTyped(KeyEvent e);

    @Override
    public abstract void keyPressed(KeyEvent e);

    @Override
    public abstract void keyReleased(KeyEvent e);

    public final void drawCenteredString(final Graphics g, final String text, final Rectangle rect, final Font font) {
        final FontMetrics metrics = g.getFontMetrics(font);

        final int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        final int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);

        g.drawString(text, x, y);
    }
}
