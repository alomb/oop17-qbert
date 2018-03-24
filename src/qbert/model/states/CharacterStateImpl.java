package qbert.model.states;

import qbert.model.Character;

/**
 * A general implementation of the interface {@link CharacterState}.
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

    @Override
    public abstract void conclude();

    /**
     * @return the {@link Character} associated with this {@link CharacterState}
     */
    public Character getCharacter() {
        return this.character;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((character == null) ? 0 : character.hashCode());
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        }
        final CharacterStateImpl other = (CharacterStateImpl) obj;
        if (character == null) {
            if (other.character != null) {
                return false;
            }
        } else if (!character.equals(other.character)) {
            return false;
        }
        return true;
    }
}
