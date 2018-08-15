package qbert.model.characters;

import qbert.model.components.graphics.DownUpwardCharacterGC;
import qbert.model.components.sounds.CharacterSC;

/**
 * Interface that extends {@link Character} used to manage characters
 * which moves also upward and needs {@link DownUpwardCharacterGC}.
 */
public interface DownUpwardCharacter extends Character {

    /**
     * @return the {@link DownUpwardCharacterGC} of this {@link DownUpwardCharacter}
     */
    DownUpwardCharacterGC getDownUpwardGraphicComponent();

    /**
     * @return the {@link CharacterSC} of this {@link DownUpwardCharacter}
     */
    CharacterSC getCharcaterSoundComponent();

}
