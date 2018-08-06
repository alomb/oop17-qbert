package qbert.model.characters;


import qbert.model.components.PointComponent;
import qbert.model.components.TimerComponent;
import qbert.model.utilities.Position2D;
import qbert.view.characters.RightwardCharacterGC;

/**
 * An enemy who falls from the right and move rightward until it falls on the other side
 * of the field.
 */
public class Wrongway extends RightwardCharacter {

    /**
     * @param startPos the first {@link Position2D} of the {@link Character} in the map
     * @param speed the {@link Character} movement speed
     * @param graphics the {@link Character}'s {@link RightwardCharacterGC}
     * @param standingTime the time passed on standing state
     */
    public Wrongway(final Position2D startPos, final Float speed, final RightwardCharacterGC graphics, final Integer standingTime) {
        super(startPos, speed, graphics, standingTime);
        this.setCurrentState(this.getStandingState());
    }

    @Override
    public final void collide(final Player qbert, final PointComponent points, final TimerComponent timer) {
        qbert.setDead(true);
    }

}
