package qbert.model;

import java.awt.image.BufferedImage;

public class LevelSettings {
    private int colorNumber;
    private boolean colorReversable;
    private BufferedImage backgroundImage;

    public LevelSettings(int number, boolean reverse, BufferedImage background) {
        this.colorNumber = number;
        this.colorReversable = reverse;
        this.backgroundImage = background;
    }
    
    public int getColorNumber() {
        return this.colorNumber;
    }

    public boolean getColorReversable() {
        return this.colorReversable;
    }

    public BufferedImage getBackgroundImage() {
        return this.backgroundImage;
    }
}
