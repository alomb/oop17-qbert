package qbert.view;

/**
 * 
 */
public interface TileGC extends GraphicComponent {

    /**
     * @return
     */
    int getSpriteIndex();

    /**
     * 
     */
    void setNextSprite();

    /**
     * 
     */
    void setPreviousSprite();

    /**
     * @param index the new sprite's number
     */
    void setSprite(int index);
}
