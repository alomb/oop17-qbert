package qbert.model.characters.states;

import qbert.model.characters.Snake;

/**
 * The {@link CharacterState} used by {@link Snake} in its egg form when it 
 * behaves similar to any other {@link DownwardCharStandingState}.
 */
public class CoilyBallStandingState extends DownwardCharStandingState {

    private final Snake coily;

    /**
     * @param coily the {@link Snake} linked to this state
     * @param triggerTime the timer duration
     */
    public CoilyBallStandingState(final Snake coily, final int triggerTime) {
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
