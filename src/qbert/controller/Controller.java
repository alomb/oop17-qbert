package qbert.controller;

import java.util.List;

import qbert.input.Command;
import qbert.model.GUILogic;
import qbert.model.LevelSettings;
import qbert.view.Renderable;

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

    /**
     * @param newGameStatus the next {@link GameStatus}
     */
    void changeScene(GameStatus newGameStatus);

    /**
     * @return from the current model a list of GUI to be rendered
     */
    List<GUILogic> getGUI();

    /**
     * @return from the current model a list of images to be rendered
     */
    List<Renderable> getRenderables();
}
