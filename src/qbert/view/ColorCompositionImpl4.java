package qbert.view;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import qbert.model.utilities.Sprites;

/**
 * An implementation of {@link ColorComposition}.
 */
public class ColorCompositionImpl4 implements ColorComposition {

    private final BufferedImage background;
    private BufferedImage targetColor;
    private final Set<BufferedImage> tilesSet = new HashSet<>();

    /**
     * 
     */
    public ColorCompositionImpl4() {
        this.background = Sprites.greyBackground;

        this.tilesSet.add(Sprites.blueTile);
        this.tilesSet.add(Sprites.yellowTile);
        this.tilesSet.add(Sprites.greenTile);
        this.tilesSet.add(Sprites.pinkTile);
        this.tilesSet.add(Sprites.beigeTile);
        this.tilesSet.add(Sprites.purpleTile);
    }

    @Override
    public final Map<Integer, BufferedImage> getColorComposition(final int n) {
        final Map<Integer, BufferedImage> colorMap = new HashMap<>();

        final Iterator<BufferedImage> it = this.tilesSet.iterator();
        for (int i = 0; i <= n; i++) {
            colorMap.put(i, it.next());
        }
        this.targetColor = colorMap.get(n);
        return colorMap;
    }

    @Override
    public final BufferedImage getBackgroundImage() {
        return this.background;
    }

    @Override
    public final BufferedImage getTargetColor() {
        return this.targetColor;
    }

}
