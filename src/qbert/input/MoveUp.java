package qbert.input;

import qbert.model.Character;
import qbert.model.Game;
import qbert.model.Qbert;
import qbert.model.states.MoveState;

/**
 * The command used to manage the up-arrow key.
 */
public class MoveUp implements Command {

    @Override
    public void execute(final Game game) {
        final Character qbert = game.getLevel().getQBert();
        if (qbert.getCurrentState() instanceof Qbert.QbertStandingState) {
            qbert.setCurrentState(new MoveState.UpRight(qbert));
        }
    }
}
