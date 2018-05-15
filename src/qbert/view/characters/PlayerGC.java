package qbert.view.characters;

/**
 * The {@link CharacterGC} used by {@link Player} that contains 
 * other different animations like the surfing disk.
 */
public interface PlayerGC extends DownUpwardCharacterGC {

    /**
     * Set the {@link Player} relative moving down-right animation.
     */
    void setOnDiskAnimation();

}
