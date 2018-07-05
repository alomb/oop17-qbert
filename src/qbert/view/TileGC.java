package qbert.view;

/**
 *  An extension of {@link GraphicComponent} for objects that have a set of indexed sprites.
 */
public interface TileGC extends GraphicComponent {

    /**
     * @return the current sprite's index
     */
    int getSpriteIndex();

    /**
     * set the next sprite and updates the index.
     * @return True if the the sprite changed to a forward color
     */
    boolean setNextSprite();

    /**
     * Set the index of the current.
     * @param index the new sprite's number
     */
    void setSprite(int index);

    /**
     * @return True if {@link Tile} is in its target Color
     */
    boolean isTargetColor();
}
