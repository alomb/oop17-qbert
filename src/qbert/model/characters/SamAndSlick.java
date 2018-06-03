package qbert.model.characters;

import qbert.model.Level;
import qbert.model.components.MapComponent;
import qbert.model.components.PointComponent;
import qbert.model.components.TimerComponent;
import qbert.model.states.DeathState;
import qbert.model.utilities.Position2D;
import qbert.view.characters.CharacterGC;

/**
 * This class models two enemies Sam and Slick who jumps downward until they fall
 * out of the map, reversing colors of touched tiles. Those characters can be killed by Qbert.
 */
public class SamAndSlick extends DownwardCharacter {

    /**
     * @param startPos the first {@link Position2D} of the {@link Character} in the map
     * @param speed the {@link Character} movement speed
     * @param graphics the {@link Character}'s {@link CharacterGC}
     * @param standingTime the time passed on standing state
     */
    public SamAndSlick(final Position2D startPos, final float speed, final CharacterGC graphics, final Integer standingTime) {
        super(startPos, speed, graphics, standingTime);
    }

    @Override
    public final void land(final MapComponent map, final PointComponent points) {
        map.resetColor(this.getNextPosition());
    }

    @Override
    public final void collide(final Player qbert, final PointComponent points, final TimerComponent timer) {
        points.score(points.KILL_SAM_SLICK_SCORE);
        this.setCurrentState(new DeathState(this));
    }
}
