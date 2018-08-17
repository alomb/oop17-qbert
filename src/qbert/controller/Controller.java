package qbert.controller;

import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.sound.sampled.Clip;

import qbert.controller.input.Command;
import qbert.model.LevelSettings;
import qbert.model.models.GUILogic;
import qbert.model.components.graphics.Renderable;

/**
 * The application controller, responsible of communication between {@link View} and current
 * {@link Model} and initialization of {@link GameEngine}. It also provides a lot of I/O operations.
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
    void setScore(Integer value);

    /**
     * @return value of score point at finish game
     */
    Integer getScore();

    /**
     * @return from the current ranking
     */
    Map<String, Integer> getRank();
    /**
     * @param string 
     * 
     */
    void addRank(String string);

    /**
     * Close the window in the {@link View} and terminate the {@link GameEngine}
     * closing the application.
     */
    void terminate();

    /**
     * Method used to abort the application during its setup phase.
     */
    void abort();

    /**
     * @param soundEffect the {@link SoundEffectFile} value representing the .wav file to be loaded
     * @return the {@link Clip} of the sound effect to be played
     */
    Clip uploadClip(SoundEffectFile soundEffect);

    /**
     * @param queue the {@link Queue} to be emptied
     */
    void emptyClipQueue(Queue<Clip> queue);

}
