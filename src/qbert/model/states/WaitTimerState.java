package qbert.model.states;

import qbert.model.characters.Character;

/**
 * An implementation of the {@link CharacterState} interface used to wait the duration of a timer.
 */
public abstract class WaitTimerState extends CharacterStateImpl {

    private final int triggerTime;
    private int elapsedTime;

    /**
     * @param character the {@link Character} associated with this {@link CharacterState}
     * @param triggerTime the timer duration time
     */
    public WaitTimerState(final Character character, final int triggerTime) {
        super(character);
        this.triggerTime = triggerTime;
    }

    /**
     * @return the timer duration
     */
    public int getTriggerTime() {
        return this.triggerTime;
    }

    @Override 
    public final void update(final float dt) {
        if (this.triggerTime <= this.elapsedTime) {
            this.conclude();
            this.elapsedTime = 0;
        } else {
            this.elapsedTime += dt;
        }
    }

    /**
     * Undefined method used to accomplish the operations after the timer expirations. 
     */
    public abstract void conclude();
}
