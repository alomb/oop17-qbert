package qbert.model.characters;

import qbert.model.states.CharacterState;
import qbert.model.states.DownwardCharStandingState;
import qbert.model.states.MoveState;
import qbert.model.states.WaitTimerState;
import qbert.model.utilities.Position2D;
import qbert.view.CoilyGC;
import qbert.view.DownUpwardCharacterGC;

/**
 * The class shape a clever Qbert's enemy, a purple snake called Coily. Coily join the game in its egg form behaving as
 * a common {@link RedBall}, but when it reaches the bottom of the map it transforms in a snake and starts chasing Qbert.
 */
public class Coily extends CharacterImpl implements DownUpwardCharacter {

    private final CoilyGC graphics;
    private final int standingTime;
    private final Player qbert;

    private boolean adult;

    /**
     * @param startPos the first {@link Position2D} of the {@link Character} in the map
     * @param speed the {@link Character} movement speed
     * @param graphics the {@link Character}'s {@link CharacterGraphicComponent}
     * @param standingTime the time passed on standing state
     * @param qbert the {@link Qbert} reference
     */
    public Coily(final Position2D startPos, final float speed, final CoilyGC graphics, 
            final int standingTime, final Player qbert) {
        super(startPos, speed, graphics);
        this.graphics = graphics;
        this.standingTime = standingTime;
        this.qbert = qbert;
        this.setCurrentState(new MoveState.Spawn(this));
    }

    /**
     * The method to transform Coily from an egg to his adult form.
     */
    public void transform() {
        this.adult = true;
        this.graphics.transform();
    }

    @Override
    public final DownUpwardCharacterGC getDownUpwardGraphicComponent() {
        return this.graphics;
    }

    @Override
    public final CharacterState getStandingState() {
        if (!this.adult) {
            return new CoilyBallStandingState(this, standingTime);
        } else {
            return new CoilyAdultStandingState(this, standingTime, this.qbert);
        }
    }

    private class CoilyBallStandingState extends DownwardCharStandingState {

        private final Coily coily;

        /**
         * @param character the {@link Character} linked to this state
         * @param triggerTime the timer duration
         */
        CoilyBallStandingState(final Coily coily, final int triggerTime) {
            super(coily, triggerTime);
            this.coily = coily;
            this.getCharacter().getGraphicComponent().setStandingAnimation();
        }

        @Override
        public final boolean canAdvance() {
            if (this.getCharacter().getCurrentPosition().getY() == 0) {
                this.coily.transform();
                this.getCharacter().setCurrentState(this.getCharacter().getStandingState());
                return false;
            }
            return true;
        }

    }

    private class CoilyAdultStandingState extends WaitTimerState {

        private final Player qbert;
        private final Coily coily;

        /**
         * @param character the {@link Character} linked to this state
         * @param triggerTime the timer duration
         * @param qbert the {@link Qbert} reference
         */
        CoilyAdultStandingState(final Coily character, final int triggerTime, final Player qbert) {
            super(character, triggerTime);
            this.qbert = qbert;
            this.coily = character;
            this.getCharacter().setCurrentPosition(new Position2D(this.getCharacter().getNextPosition()));
            this.coily.getDownUpwardGraphicComponent().setStandingAnimation();
        }

        @Override
        public void conclude() {
            final Position2D targetPosition = new Position2D(this.qbert.getCurrentPosition());
            final Position2D myPosition = new Position2D(this.getCharacter().getCurrentPosition());

            if (targetPosition.equals(myPosition)) {
                return;
            }

            final int dx, dy;

            if (targetPosition.getX() > myPosition.getX()) {
                dx = 1;
            } else if (targetPosition.getX() < myPosition.getX()) {
                dx = -1;
            } else {
                dx = ((Math.random() > 0.5) ? 1 : -1);
            }

            if (targetPosition.getY() > myPosition.getY() || myPosition.getY() == 0) {
                dy = 1;
            } else if (targetPosition.getY() == myPosition.getY()) {
                dy = ((Math.random() > 0.5) ? 1 : -1);
            } else {
                dy = -1;
            }

            if (dx > 0 && dy > 0) {
                this.getCharacter().setCurrentState(new MoveState.UpRight(this.coily));
            } else if (dx < 0 && dy > 0) {
                this.getCharacter().setCurrentState(new MoveState.UpLeft(this.coily));
            } else if (dx > 0 && dy < 0) {
                this.getCharacter().setCurrentState(new MoveState.DownRight(this.coily));
            } else {
                this.getCharacter().setCurrentState(new MoveState.DownLeft(this.coily));
            }
        }
    }
}
