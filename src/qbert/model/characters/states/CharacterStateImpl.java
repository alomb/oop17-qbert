package qbert.model.characters.states;

import qbert.model.characters.Character;

/**
 * A generic implementation of the interface {@link CharacterState}, that provides minimal functionalities.
 */
public abstract class CharacterStateImpl implements CharacterState {

    private final Character character;

    /**
     * @param character {@link Character} associated with this {@link CharacterState} 
     */
    public CharacterStateImpl(final Character character) {
        this.character = character;
    }

    @Override
    public abstract void update(float dt);

    /**
     * @return the {@link Character} associated with this {@link CharacterState}
     */
    public Character getCharacter() {
        return this.character;
    }
}
