package qbert.view;

import qbert.model.utilities.Sprites;

/**
 * A specific implementation of {@link ColorComposition}.
 */
public class ColorCompositionImpl1 extends ColorCompositionImpl {

    /**
     * 
     */
    public ColorCompositionImpl1() {
        super(Sprites.blueBackground);

        this.getTilesList().add(Sprites.yellowTile);
        this.getTilesList().add(Sprites.pinkTile);
        this.getTilesList().add(Sprites.greyTile);
        this.getTilesList().add(Sprites.blueTile);
    }
}
