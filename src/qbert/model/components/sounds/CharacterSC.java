package qbert.model.components.sounds;

/**
 * This interface models the set of sounds related to a specific character.
 */
public interface CharacterSC extends SoundComponent {

    /**
     * This method adds the sound effect associated with a character's hop to the queue.
     */
    void setHopSound();

    /**
     * This method adds the sound effect associated with a character's fall beyond the map to the queue.
     */
    void setFallSound();

    /**
     * This method adds the sound effect associated with a character's attack to the queue.
     */
    void setKillSound();

}
