package qbert.view;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * The implementation of {@link ColorComposition}.
 */
public class ColorCompositionImpl implements ColorComposition {

    private final BufferedImage background;
    private final List<BufferedImage> tilesList;

    /**
     * @param background the background image to be set
     */
    public ColorCompositionImpl(final BufferedImage background) {
        this.background = background;
        this.tilesList = new ArrayList<>();
    }

    @Override
    public final Map<Integer, BufferedImage> getColorComposition(final int n) {
        Collections.shuffle(tilesList);

        final int num = n > tilesList.size() ? (tilesList.size() - 1) : n;

        final AtomicInteger ai = new AtomicInteger();
        return this.tilesList.stream()
                             .limit(num + 1)
                             .collect(Collectors.toMap(e -> ai.getAndIncrement(), e -> e));
    }

    @Override
    public final BufferedImage getBackgroundImage() {
        return this.background;
    }

    @Override
    public final List<BufferedImage> getTilesList() {
        return this.tilesList;
    }
}
