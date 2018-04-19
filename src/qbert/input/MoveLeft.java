package qbert.input;

import qbert.model.Game;
import qbert.model.characters.Character;
import qbert.model.states.MoveState;
import qbert.model.states.QbertStandingState;

/**
 * The command used to manage the left-arrow key.
 */
public class MoveLeft implements Command {

    @Override
    public void execute(final Game game) {
        final Character qbert = game.getLevel().getQBert();
        if (qbert.getCurrentState() instanceof QbertStandingState) {
            qbert.setCurrentState(new MoveState.UpLeft(qbert));
        }
    }
}
