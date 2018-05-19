package qbert.model.characters;

/**
 * The interface for a clever Qbert's enemy, a purple snake called Coily. 
 * Coily join the game in its egg form behaving as a common {@link RedBall}, 
 * but when it reaches the bottom of the map it transforms in a snake and starts chasing Qbert.
 * A contact with Qbert is lethal for the player.
 */
public interface Coily extends DownUpwardCharacter {

    /**
     * The method to transform Coily from an egg to his adult form.
     */
    void transform();

}
