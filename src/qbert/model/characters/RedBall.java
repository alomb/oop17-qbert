package qbert.model.characters;

import qbert.controller.Sounds;
import qbert.model.components.PointComponent;
import qbert.model.components.TimerComponent;
import qbert.model.utilities.Position2D;
import qbert.view.characters.CharacterGC;

/**
 * This class models an enemy who falls from the sky to the map and starts jumping
 * randomly downwards until it drops off the edge. A contact with Qbert is lethal for 
 * the player.
 * 
 * If it hits Qbert he will loose a life.
 */
public class RedBall extends DownwardCharacter {

    /**
     * @param startPos the first {@link Position2D} of the {@link Character} in the map
     * @param speed the {@link Character} movement speed
     * @param graphics the {@link Character}'s {@link CharacterGC}
     * @param standingTime the time passed on standing state
     */
    public RedBall(final Position2D startPos, final Float speed, final CharacterGC graphics, final Integer standingTime) {
        super(startPos, speed, graphics, standingTime);
    }

    @Override
    public final void collide(final Player qbert, final PointComponent points, final TimerComponent timer) {
        qbert.setDead(true);
        Sounds.playSound("%*#!*.wav");
    }
}
