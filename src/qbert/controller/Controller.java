package qbert.controller;

import java.util.List;
import java.util.Map;

import qbert.controller.input.Command;
import qbert.model.LevelSettings;
import qbert.model.models.GUILogic;
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
    
    /**
     * @param value of score point at finish game
     */
    void setScore(Integer i);
    
    /**
     * @return value of score point at finish game
     */
    Integer getScore();
    
    /**
     * @return from the current ranking
     */
    Map<String,Integer> getRank();
    
    /**
     * @return from the current ranking
     */
    void addRank(String s, Integer i);
    
}
