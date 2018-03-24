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

    /**
     * This function is used to end the current state, e.g., replacing with other one 
     * and updating the {@link CharacterState} reference.
     */
    void conclude();

    /**
     * @return the relative hashcode
     */
    int hashCode();

    /**
     * @param obj the state to compare
     * @return true if the two states are equals
     */
    boolean equals(Object obj);
}
