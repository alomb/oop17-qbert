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
     */
    void setNextSprite();

    /**
     * set the previous sprite and updates the index.
     */
    void setPreviousSprite();

    /**
     * Set the index of the current.
     * @param index the new sprite's number
     */
    void setSprite(int index);
}
