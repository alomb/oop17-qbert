package qbert.view.animations;

import qbert.model.utilities.Position2D;

public class MoveUpAnimation extends GenericAnimation {

    public MoveUpAnimation(Position2D startPos, Position2D targetPos) {
        super(startPos);
        this.setTargetPosition(targetPos);
    }

    @Override
    public void calculateNext() {
        if (!this.hasFinished()) {
            this.getCurrentPosition().setY(this.getCurrentPosition().getY() - this.getAnimationSpeed());

            if (this.getTargetPosition().getY() > this.getCurrentPosition().getY()) {
                this.getCurrentPosition().setY(this.getTargetPosition().getY());
            }
        }
    }

}
