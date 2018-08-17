package qbert.view.scenes;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import qbert.model.utilities.Dimensions;

/**
 * The model abstraction of GUI text dimensions (between lines and text's).
 */
public enum TextSize {

    /**
     * Small size.
     */
    SMALL(Dimensions.getWindowHeight() / 60f),

    /**
     * Medium size.
     */
    MEDIUM(Dimensions.getWindowHeight() / 30f),

    /**
     * Large size.
     */
    LARGE(Dimensions.getWindowHeight() / 10f);

    private final float size;
    private Optional<Font> font;

    TextSize(final float size) {
        this.size = size;
        this.font = Optional.empty();

        final InputStream in = this.getClass().getResourceAsStream("/arcade_n.ttf");
        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        try {
            this.font = Optional.of(Font.createFont(Font.TRUETYPE_FONT, in).deriveFont(this.size));
            ge.registerFont(this.font.get());
            in.close();
        } catch (FontFormatException e) {
            Logger.getGlobal().log(Level.WARNING, "Impossibile caricare il font.", e);
        } catch (IOException e) {
            Logger.getGlobal().log(Level.WARNING, "Impossibile caricare il font.", e);
        }
    }

    /**
     * @return the font size
     */
    public float getSize() {
        return this.size;
    }

    /**
     * @return an {@link Optional} containing the font, or empty otherwise
     */
    public Optional<Font> getFont() {
        return this.font;
    }

}
