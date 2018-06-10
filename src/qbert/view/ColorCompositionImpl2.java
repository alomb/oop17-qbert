package qbert.view;

import qbert.model.utilities.Sprites;

/**
 * A specific implementation of {@link ColorComposition}.
 */
public class ColorCompositionImpl2 extends ColorCompositionImpl {

    /**
     * 
     */
    public ColorCompositionImpl2() {
        super(Sprites.brownBackground);

        this.getTilesList().add(Sprites.blueTile);
        this.getTilesList().add(Sprites.yellowTile);
        this.getTilesList().add(Sprites.greenTile);
        this.getTilesList().add(Sprites.pinkTile);
        this.getTilesList().add(Sprites.beigeTile);
        this.getTilesList().add(Sprites.purpleTile);
        this.getTilesList().add(Sprites.greyTile);
    }
}
