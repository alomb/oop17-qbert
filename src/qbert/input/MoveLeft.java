package qbert.input;

import qbert.model.Model;
/**
 * The command used to manage the left-arrow key.
 */
public class MoveLeft implements Command {

    @Override
    public final void execute(final Model model) {
        model.moveLeft();
    }
}
