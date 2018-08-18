package qbert.model.characters;

import qbert.model.components.PointComponent;
import qbert.model.components.TimerComponent;
import qbert.model.utilities.Position2D;
import qbert.model.components.graphics.LeftwardCharacterGC;

/**
 * An enemy who falls from the left and move leftward until it falls on the other side
 * of the field.
 */
public class Ugg extends LeftwardCharacter {

    /**
     * @param startPos the first {@link Position2D} of the {@link Character} in the map
     * @param speed the {@link Character} movement speed
     * @param graphics the {@link Character}'s {@link RightwardCharacterGC}
     * @param standingTime the time passed on standing state
     */
    public Ugg(final Position2D startPos, final Float speed, final LeftwardCharacterGC graphics, final Integer standingTime) {
        super(startPos, speed, graphics, standingTime);
    }

    @Override
    public final void collide(final Player qbert, final PointComponent points, final TimerComponent timer) {
        qbert.setDead(true);
        qbert.getPlayerSoundComponent().setDeathSound();
    }

}
