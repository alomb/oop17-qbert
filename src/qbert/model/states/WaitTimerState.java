package qbert.model.states;

import qbert.model.Character;

public abstract class WaitTimerState implements CharacterState {

    private final Character character;
    private final int triggerTime;
    private int elapsedTime;

    public WaitTimerState(final Character character, final int triggerTime) {
        this.character = character;
        this.triggerTime = triggerTime;
    }

    public Character getCharacter() {
        return this.character;
    }

    public int getTriggerTime() {
        return this.triggerTime;
    }

    @Override
    public void update(final float dt) {
        if (this.triggerTime <= this.elapsedTime) {
            this.conclude();
        } else {
            this.elapsedTime += dt;
        }
    }

    @Override
    public abstract void conclude();

}
