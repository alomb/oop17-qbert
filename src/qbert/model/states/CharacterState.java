package qbert.model.states;

/**
 * The interface for a {@link Character} generic state. 
 */
public interface CharacterState {

    /**
     * This function update the state of the relative {@link CharacterState}.
     * @param dt the time passed since the last game cycle
     */
    void update(float dt);

    /**
     * This function is used to end the current state, e.g., replacing with other one 
     * and updating the {@link CharacterState} reference.
     */
    void conclude();
}
