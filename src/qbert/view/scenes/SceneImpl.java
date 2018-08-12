package qbert.view.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.swing.JPanel;

import qbert.model.models.GUILogic;
import qbert.model.models.TextPosition;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;

/**
 * A generic implementation of {@link Scene}.
 */
public abstract class SceneImpl extends JPanel implements Scene {

    private Font smallFont;
    private final float smallSize = Dimensions.getWindowHeight() / 60f;

    private Font mediumFont;
    private final float mediumSize = Dimensions.getWindowHeight() / 30f;

    private Font largeFont;
    private final float largeSize = Dimensions.getWindowHeight() / 10f;

    private final Map<TextPosition, Optional<GUISectionImpl>> sections;

    /**
     * @param w the panel width
     * @param h the panel height
     */
    public SceneImpl(final int w, final int h) {
        super();
        this.setSize(w, h);

        try {
            final URL url = getClass().getResource("/arcade_n.ttf");
            final File fontFile = new File(url.getPath());
            final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            this.largeFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(this.largeSize);
            ge.registerFont(this.largeFont);
            this.mediumFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(this.mediumSize);
            ge.registerFont(this.mediumFont);
            this.smallFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(this.smallSize);
            ge.registerFont(this.smallFont);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.sections = new HashMap<>();
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
        //Fixes some OSes bug where graphics scheduling gets slowed down
        Toolkit.getDefaultToolkit().sync();
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

    private void drawCenteredString(final Graphics g, final String text, final Position2D offset, final Font font) {
        final FontMetrics metrics = g.getFontMetrics(font);
        final Rectangle rect = new Rectangle(offset.getX(), offset.getY(), Dimensions.getWindowWidth(), Dimensions.getWindowHeight());

        final int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        final int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);

        g.drawString(text, x, y);
    }

    @Override
    public final void drawGUI(final Graphics g, final GUILogic gui) {
        if (this.sections.get(gui.getPosition()).isPresent()) {

            final GUISection section = this.sections.get(gui.getPosition()).get();

            g.setFont(this.getFont(section.getSize()));
            final Color color = section.getColor();
            final Optional<Color> selectedColor = section.getSelectedColor().isPresent()
                            ? Optional.of(this.getSection(gui.getPosition()).get().getSelectedColor().get()) : Optional.empty();

            g.setColor(color);
            for (int i = 0; i < gui.getData().size(); i++) {
                if (!gui.getSelected().contains(i)) {
                    this.drawLine(g, gui, section, i);
                }
            }

            if (selectedColor.isPresent()) {
                g.setColor(selectedColor.get());
                for (int i = 0; i < gui.getData().size(); i++) {
                    if (gui.getSelected().contains(i)) {
                        this.drawLine(g, gui, section, i);
                    }
                }
            }
        }
    }

    @Override
    public final Optional<GUISectionImpl> getSection(final TextPosition position) {
        if (this.sections.containsKey(position) && this.sections.get(position).isPresent()) {
            return this.sections.get(position);
        }
        return Optional.empty();
    }

    @Override
    public final void addSection(final TextPosition position, final GUISectionImpl section) {
        this.sections.put(position, Optional.of(section));
    }

    /**
     * @param size the {@link TextSize} the size label
     * @return the size in pixel
     */
    private Font getFont(final TextSize size) {
        switch (size) {
            case SMALL:
                return this.smallFont;
            case MEDIUM:
                return this.mediumFont;
            case LARGE:
                return this.largeFont;
            default:
                return this.smallFont;
        }
    }

    /**
     * @param g the {@link Graphics} used
     * @param gui the {@link GUILogic} containing the data
     * @param section the {@link GUISection} containing the style
     * @param index the line index
     */
    private void drawLine(final Graphics g, final GUILogic gui, final GUISection section, final int index) {
        final int xOffset = section.getXOffset();
        final int yOffset =  section.getYOffset();

        if (section.isCentered()) {
            this.drawCenteredString(g, gui.getData().get(index), 
                    new Position2D(xOffset, yOffset + g.getFont().getSize() * index * 2), g.getFont());
        } else {
            g.drawString(gui.getData().get(index), xOffset, yOffset + g.getFont().getSize() * index * 2);
        }
    }
}
