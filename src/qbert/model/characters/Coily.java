package qbert.model.characters;

import qbert.controller.Sounds;
import qbert.model.characters.states.CharacterState;
import qbert.model.characters.states.CoilyAdultStandingState;
import qbert.model.characters.states.CoilyBallStandingState;
import qbert.model.characters.states.FallState;
import qbert.model.characters.states.SpawnState;
import qbert.model.components.MapComponent;
import qbert.model.components.PointComponent;
import qbert.model.components.TimerComponent;
import qbert.model.utilities.Position2D;
import qbert.view.characters.CoilyGC;
import qbert.view.characters.DownUpwardCharacterGC;

/**
 * Implementation of {@link Snake}.
 */
public class Coily extends CharacterImpl implements Snake {

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
    public Coily(final Position2D startPos, final float speed, final CoilyGC graphics, 
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
    public final void setCurrentState(final CharacterState state) {
        super.setCurrentState(state);
        if (state instanceof FallState) {
            Sounds.playSound("CoilyGoesOverTheEdge.wav");
        }
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
        if (this.adult) {
            Sounds.playSound("CoilyBites.wav");
        } else {
            Sounds.playSound("QbertDeath1.wav");
        }
    }

    @Override
    public final void land(final MapComponent map, final PointComponent points) {
        if (this.adult) {
            Sounds.playSound("CoilyHops.wav");
        }
    }
}
