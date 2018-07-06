package qbert.model.states;

/**
 * The interface for a {@link Character} generic state. 
 */
public interface CharacterState {

    /**
     * This function update the state of the relative {@link CharacterState}.
     * @param dt the time passed since the last game cycle multiplied by the {@link Character} speed
     */
    void update(float dt);
}
