package qbert.view;

import qbert.model.utilities.Sprites;

/**
 * A specific implementation of {@link ColorComposition}.
 */
public class ColorCompositionImpl3 extends ColorCompositionImpl {

    /**
     * 
     */
    public ColorCompositionImpl3() {
        super(Sprites.greenBackground);

        this.getTilesList().add(Sprites.blueTile);
        this.getTilesList().add(Sprites.yellowTile);
        this.getTilesList().add(Sprites.pinkTile);
        this.getTilesList().add(Sprites.greyTile);
    }
}
