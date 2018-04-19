package qbert.input;

import qbert.model.Game;
import qbert.model.characters.Character;
import qbert.model.states.MoveState;
import qbert.model.states.QbertStandingState;

/**
 * The command used to manage the down-arrow key.
 */
public class MoveDown implements Command {

    @Override
    public void execute(final Game game) {
        final Character qbert = game.getLevel().getQBert();
        if (qbert.getCurrentState() instanceof QbertStandingState) {
            qbert.setCurrentState(new MoveState.DownLeft(qbert));
        }
    }
}
