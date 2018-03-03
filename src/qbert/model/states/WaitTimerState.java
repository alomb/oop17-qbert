package qbert.model.states;

import qbert.model.Character;

/**
 * An implementation of the {@link CharacterState} interface used to wait the duration of a timer.
 */
public abstract class WaitTimerState implements CharacterState {

    private final Character character;
    private final int triggerTime;
    private int elapsedTime;

    /**
     * @param character the {@link Character} associated with this {@link CharacterState}
     * @param triggerTime the timer duration time
     */
    public WaitTimerState(final Character character, final int triggerTime) {
        this.character = character;
        this.triggerTime = triggerTime;
    }

    /**
     * @return the {@link Character} associated with this {@link CharacterState}
     */
    public Character getCharacter() {
        return this.character;
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

    @Override
    public abstract void conclude();

}
