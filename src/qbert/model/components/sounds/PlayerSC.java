package qbert.model.components.sounds;

/**
 * This interface models the set of sounds related to the {@link Player}.
 */
public interface PlayerSC extends CharacterSC {

    /**
     * This method adds the sound effect associated with the player's hop on the disk to the queue.
     */
    void setOnDiskSound();

    /**
     * This method adds the sound effect associated with the player's death to the queue.
     */
    void setDeathSound();
}
