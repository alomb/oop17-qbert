package qbert.view;

import qbert.view.scenes.Scene;

/**
 * The responsible of rendering and scene managing.
 */
public interface View {

    /**
     * Render the current {@link Scene}.
     */
    void render();

    /**
     * @param scene a new scene
     * @param sceneName its name
     */
    void addScene(Scene scene, String sceneName);

    /**
     * @param sceneName the name of the {@link Scene} to be set
     */
    void setScene(String sceneName);

    /**
     * @return get the current {@link Scene}
     */
    Scene getScene();

}
