package qbert.input;

import qbert.model.Model;

/**
 * The command used to manage the up-arrow key.
 */
public class MoveUp implements Command {

    @Override
    public final void execute(final Model model) {
        model.moveUp();
    }
}
