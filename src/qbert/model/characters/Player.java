package qbert.model.characters;

import qbert.model.components.sounds.PlayerSC;
import qbert.model.components.graphics.PlayerGC;

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

    /**
     * Makes the player gain 1 life.
     */
    void gainLife();

    /**
     * Makes the player loose 1 life.
     */
    void looseLife();

    /**
     * @return Number of lives left
     */
    int getLivesNumber();

    /**
     * @return {@link PlayerSC} of this {@link Player}
     */
    PlayerSC getPlayerSoundComponent();
}
