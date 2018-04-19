package qbert.model.characters;

import qbert.model.states.CharacterState;
import qbert.model.states.DownwardCharStandingState;
import qbert.model.states.MoveState;
import qbert.model.states.WaitTimerState;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.model.utilities.Sprites;
import qbert.view.CharacterGraphicComponent;
import qbert.view.DownwardUpwardCGC;

/**
 * The class shape a clever Qbert's enemy, a purple snake called Coily. Coily join the game in its egg form behaving as
 * a common {@link RedBall}, but when it reaches the bottom of the map it transforms in a snake and starts chasing Qbert.
 */
public class Coily extends CharacterImpl {

    private final int standingTime;
    private Character qbert;

    private boolean adult;

    /**
     * @param startPos the first {@link Position2D} of the {@link Character} in the map
     * @param speed the {@link Character} movement speed
     * @param graphics the {@link Character}'s {@link CharacterGraphicComponent}
     * @param standingTime the time passed on standing state
     * @param qbert the {@link Qbert} reference
     */
    public Coily(final Position2D startPos, final float speed, final CharacterGraphicComponent graphics, 
            final int standingTime, final Character qbert) {
        super(startPos, speed, graphics);
        this.standingTime = standingTime;
        this.qbert = qbert;
        this.setCurrentState(new MoveState.Spawn(this));
    }

    /**
     * The method to transform Coily from an egg to his adult form.
     */
    public void transform() {
        this.adult = true;
        this.setGraphicComponent(new DownwardUpwardCGC(Sprites.RedBallStanding, Sprites.RedBallMoving, 
                Sprites.RedBallStanding, Sprites.RedBallMoving, new Position2D(this.getGraphicComponent().getPosition())));
    }

    @Override
    public final CharacterState getStandingState() {
        if (!this.adult) {
            return new CoilyBallStandingState(this, standingTime);
        } else {
            return new CoilyAdultStandingState(this, standingTime, qbert);
        }
    }

    private class CoilyBallStandingState extends DownwardCharStandingState {

        /**
         * @param character the {@link Character} linked to this state
         * @param triggerTime the timer duration
         */
        CoilyBallStandingState(final Character character, final int triggerTime) {
            super(character, triggerTime);
            this.getCharacter().getGraphicComponent().setStandingAnimation();
        }

        @Override
        public final boolean canAdvance() {
            /*Si può sostituire con il numero di passi che deve compiere dipendente dalla dimensione della mappa*/
            if (this.getCharacter().getCurrentPosition().getY() == 0) {
                ((Coily) this.getCharacter()).transform();
                this.getCharacter().setCurrentState(this.getCharacter().getStandingState());
                return false;
            }
            return true;
        }

    }

    private class CoilyAdultStandingState extends WaitTimerState {

        private final Character qbert;

        /**
         * @param character the {@link Character} linked to this state
         * @param triggerTime the timer duration
         * @param qbert the {@link Qbert} reference
         */
        CoilyAdultStandingState(final Character character, final int triggerTime, final Character qbert) {
            super(character, triggerTime);
            this.qbert = qbert;
            this.getCharacter().getGraphicComponent().setStandingAnimation();
        }

        @Override
        public void conclude() {
            System.out.println("Coily B: " + this.getCharacter().getCurrentPosition());
            final Position2D targetPosition = qbert.getCurrentPosition();
            final Position2D myPosition = this.getCharacter().getCurrentPosition();

            final int dx, dy;

            if (targetPosition.equals(myPosition)) {
                return;
            }

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
                this.getCharacter().setCurrentState(new MoveState.UpRight(this.getCharacter()));
            } else if (dx < 0 && dy > 0) {
                this.getCharacter().setCurrentState(new MoveState.UpLeft(this.getCharacter()));
            } else if (dx > 0 && dy < 0) {
                this.getCharacter().setCurrentState(new MoveState.DownRight(this.getCharacter()));
            } else {
                this.getCharacter().setCurrentState(new MoveState.DownLeft(this.getCharacter()));
            }

            this.getCharacter().getCurrentPosition().setY(myPosition.getY() + dy);
            this.getCharacter().getCurrentPosition().setX(myPosition.getX() + dx);
        }
    }
}
