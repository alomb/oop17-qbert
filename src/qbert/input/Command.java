package qbert.input;

import qbert.model.Game;

/**
 * A command object invokes methods of the receiver (the object affected by the execution of certain commands)
 * and/or do other operations when an invoker (e.g. the keyboard) demands it by calling the execute method.
 * It provides a simple way of delegating some operations to other classes.
 */
public interface Command {

    /**
     * @param game the reference to the current game session
     */
    void execute(Game game);
}
