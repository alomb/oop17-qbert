package qbert.model;

import model.utilities.Position2D;

public class CharacterImpl implements Character {

    private Position2D currentPos;
    private Position2D nextPos;
    private float characterSpeed;

    @Override
    public Position2D getCurrentPosition() {
        // TODO Auto-generated method stub
        return this.currentPos;
    }

    @Override
    public void setCurrentPosition(final Position2D currentGridPos) {
        // TODO Auto-generated method stub
        this.currentPos = currentGridPos;
    }

    @Override
    public Position2D getNextPosition() {
        // TODO Auto-generated method stub
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
    public void update(final float dt) {
        
    }
}
