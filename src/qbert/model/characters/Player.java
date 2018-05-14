package qbert.model.characters;

import qbert.view.PlayerGC;

/**
 * Interface that extends {@link Character} used to manage {@link Qbert}, 
 * the character controlled by the player, and to provide a different graphic
 * component ({@link PlayerGC}).
 */
public interface Player extends DownUpwardCharacter {

    /**
     * @return the {@link PlayerGC} of this {@link Player}
     */
    PlayerGC getPlayerGraphicComponent();

}
