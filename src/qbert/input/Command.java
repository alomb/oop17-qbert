package qbert.input;

import qbert.model.Game;

/**
 * 
 *
 */
public interface Command {

    void execute(Game scene);
}
