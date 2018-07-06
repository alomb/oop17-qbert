package qbert.model.characters;

import qbert.model.components.PointComponent;
import qbert.model.components.TimerComponent;
import qbert.model.states.CharacterState;
import qbert.model.states.CoilyAdultStandingState;
import qbert.model.states.CoilyBallStandingState;
import qbert.model.states.SpawnState;
import qbert.model.utilities.Position2D;
import qbert.view.characters.CoilyGC;
import qbert.view.characters.DownUpwardCharacterGC;

/**
 * Implementation of {@link Coily}.
 */
public class CoilyImpl extends CharacterImpl implements Coily {

    private final CoilyGC graphics;
    private final int standingTime;
    private final Player qbert;

    private boolean adult;

    /**
     * @param startPos the first {@link Position2D} of the {@link DownUpwardCharacte} in the map
     * @param speed the {@link DownUpwardCharacter} movement speed
     * @param graphics the {@link DownUpwardCharacte}'s {@link CoilyGC}
     * @param standingTime the time passed on standing state
     * @param qbert the {@link Player} reference
     */
    public CoilyImpl(final Position2D startPos, final float speed, final CoilyGC graphics, 
            final int standingTime, final Player qbert) {
        super(startPos, speed, graphics);
        this.graphics = graphics;
        this.standingTime = standingTime;
        this.qbert = qbert;
        this.setCurrentState(new SpawnState(this));
    }

    @Override
    public final void transform() {
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

    @Override
    public final void collide(final Player qbert, final PointComponent points, final TimerComponent timer) {
        qbert.setDead(true);
    }
}
