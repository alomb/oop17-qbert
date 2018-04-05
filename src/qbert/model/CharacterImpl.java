package qbert.model;

import qbert.model.states.CharacterState;
import qbert.model.utilities.Position2D;
import qbert.view.CharacterGraphicComponent;

public abstract class CharacterImpl implements Character {

    private Position2D currentPos;
    private Position2D nextPos;

    private float characterSpeed;
    private CharacterGraphicComponent graphics;

    private CharacterState currentState;

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

    @Override
    public CharacterState getCurrentState() {
        return this.currentState;
    }

    @Override
    public void setCurrentState(final CharacterState state) {
        this.currentState = state;
    }

    @Override
    public abstract CharacterState getStandingState();

    @Override
    public void update(final float dt) {
        this.currentState.update(dt);
    }
/*
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(characterSpeed);
        result = prime * result + ((currentPos == null) ? 0 : currentPos.hashCode());
        result = prime * result + ((currentState == null) ? 0 : currentState.hashCode());
        result = prime * result + ((nextPos == null) ? 0 : nextPos.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CharacterImpl other = (CharacterImpl) obj;
        if (Float.floatToIntBits(characterSpeed) != Float.floatToIntBits(other.characterSpeed)) {
            return false;
        }
        if (currentPos == null) {
            if (other.currentPos != null) {
                return false;
            }
        } else if (!currentPos.equals(other.currentPos)) {
            return false;
        }
        if (currentState == null) {
            if (other.currentState != null) {
                return false;
            }
        } else if (!currentState.equals(other.currentState)) {
            return false;
        }
        if (nextPos == null) {
            if (other.nextPos != null) {
                return false;
            }
        } else if (!nextPos.equals(other.nextPos)) {
            return false;
        }
        return true;
    }
*/
}
