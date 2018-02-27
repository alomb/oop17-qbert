package qbert.model;

import qbert.model.states.CharacterState;
import qbert.model.utilities.Position2D;
import qbert.view.CharacterGraphicComponent;

public abstract class CharacterImpl implements Character {

    private Position2D currentPos;
    private Position2D nextPos;
    private float characterSpeed;

    private CharacterGraphicComponent graphics;
    private CharacterState state;

    public CharacterImpl(final Position2D startPos, final float speed, final CharacterGraphicComponent graphics) {
        this.currentPos = startPos;
        this.characterSpeed = speed;
        this.graphics = graphics;
    }

    @Override
    public Position2D getCurrentPosition() {
        return this.currentPos;
    }

    @Override
    public void setCurrentPosition(final Position2D currentGridPos) {
        this.currentPos = currentGridPos;
    }

    @Override
    public Position2D getNextPosition() {
        return this.nextPos;
    }

    @Override
    public void setNextPosition(final Position2D nextGridPos) {
        this.nextPos = nextGridPos;
    }

    @Override
    public float getSpeed() {
        return this.characterSpeed;
    }

    @Override
    public void setSpeed(final float speed) {
        this.characterSpeed = speed;
    }

    @Override
    public CharacterGraphicComponent getGraphicComponent() {
        return this.graphics;
    }

    @Override
    public void setGraphicComponent(final CharacterGraphicComponent graphics) {
        this.graphics = graphics;
    }

    @Override
    public boolean isMoving() {
        return this.currentPos.equals(this.nextPos);
    }

    public abstract void update(final float dt);
}
