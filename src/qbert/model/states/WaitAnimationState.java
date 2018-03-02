package qbert.model.states;

import qbert.model.Character;

public abstract class WaitAnimationState implements CharacterState{

    private final Character character;

    public WaitAnimationState(final Character character) {
        this.character = character;
    }

    public Character getCharacter() {
        return this.character;
    }

    @Override
    public void update(final float dt) {
        if (!this.character.getGraphicComponent().getCurrentAnimation().hasFinished()) {
            this.character.getGraphicComponent().updateGraphics(dt * this.character.getSpeed());
        } else {
            this.conclude();
        }
    }

    @Override
    public abstract void conclude();
}
