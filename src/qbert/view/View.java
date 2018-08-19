package qbert.view;

import java.util.Queue;

import javax.sound.sampled.Clip;

import qbert.controller.Controller;
import qbert.controller.GameStatus;
import qbert.view.scenes.Scene;

/**
 * The responsible of rendering and scene managing.
 */
public interface View {

    /**
     * Method used to initialize the {@link View}.
     * @param controller the application controller
     */
    void initialize(Controller controller);

    /**
     * Render the current {@link Scene}.
     */
    void render();

    /**
     * @param scene a new {@link Scene}
     * @param status its associated {@link GameStatus}
     */
    void addScene(Scene scene, GameStatus status);

    /**
     * @param status the {@link GameStatus} associated to the {@link Scene} to be set
     */
    void setScene(GameStatus status);

    /**
     * @return get the current {@link Scene}
     */
    Scene getScene();

    /**
     * Close the window releasing all the native screen resources, 
     * its subcomponents and all of its owned children.
     */
    void closeWindow();

    /**
     * This method plays the sound effects associated with the clips contained in the queue.
     * @param clipToPlay the {@link Queue} containing the sound effects clips to be played
     */
    void play(Queue<Clip> clipToPlay);

    /**
     * Show an error message then close the window.
     * @param message the message to be displayed
     */
    void showErrorMessageBox(String message);


}
