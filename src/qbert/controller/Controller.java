package qbert.controller;

import qbert.input.Command;
import qbert.model.LevelSettings;

/**
 * The game controller.
 */
public interface Controller {

    /**
     * Setup the game engine.
     */
    void setupGameEngine();

    /**
     * @param level the current level number
     * @param round the current round number
     * @return the level features
     */
    LevelSettings getLevelSettings(int level, int round);

    /**
     * @param command a class encapsulating code to execute
     */
    void notifyCommand(Command command);
}
