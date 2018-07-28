package qbert.input;

import qbert.model.Model;

/**
 * The command used to manage the enter key.
 */
public class Confirm implements Command {

    @Override
    public final void execute(final Model model) {
        model.confirm();
    }
}
