package qbert.view;

import qbert.model.utilities.Sprites;

/**
 * A specific implementation of {@link ColorComposition}.
 */
public class ColorCompositionImpl4 extends ColorCompositionImpl {

    /**
     * 
     */
    public ColorCompositionImpl4() {
        super(Sprites.greyBackground);

        this.getTilesList().add(Sprites.blueTile);
        this.getTilesList().add(Sprites.yellowTile);
        this.getTilesList().add(Sprites.greenTile);
        this.getTilesList().add(Sprites.pinkTile);
        this.getTilesList().add(Sprites.beigeTile);
        this.getTilesList().add(Sprites.purpleTile);
    }
}
