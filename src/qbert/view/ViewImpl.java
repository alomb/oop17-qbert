package qbert.view;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import qbert.controller.Controller;
import qbert.controller.GameStatus;
import qbert.model.utilities.Dimensions;
import qbert.view.scenes.Scene;
import qbert.view.scenes.SceneGame;
import qbert.view.scenes.SceneIntro;
import qbert.view.scenes.SceneMenu;

/**
 * The implementation of {@link View}.
 */
public class ViewImpl implements View {

    private final JPanel fixedPanel;
    private Scene scene;
    private final Map<String, Scene> scenes;

    /**
     * @param controller the game controller.
     */
    public ViewImpl(final Controller controller) {
        final int w = Dimensions.getWindowWidth(), h = Dimensions.getWindowHeight();
        final JFrame frame = new JFrame("Qbert");
        fixedPanel = new JPanel(new CardLayout());
        frame.getContentPane().add(this.fixedPanel);
        frame.setSize(w, h);
        frame.setMinimumSize(new Dimension(w, h));
        frame.setResizable(false);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent ev) {
                System.exit(-1);
            }
            public void windowClosed(final WindowEvent ev) {
                System.exit(-1);
            }
        });

        frame.pack();
        frame.setVisible(true);

        this.scenes = new HashMap<>();

        this.addScene(new SceneIntro(w, h, controller), GameStatus.INTRODUCTION.name());
        this.addScene(new SceneMenu(w, h, controller), GameStatus.MENU.name());
        this.addScene(new SceneGame(w, h, controller), GameStatus.GAMEPLAY.name());

    }

    @Override
    public final void render() {
        if (this.scene != null) {
            try {
                SwingUtilities.invokeAndWait(() -> {
                    this.scene.render();
                });
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public final void addScene(final Scene scene, final String sceneName) {
        this.fixedPanel.add((Component) scene, sceneName);
        this.scenes.put(sceneName, scene);
    }

    @Override
    public final void setScene(final String sceneName) {
        ((CardLayout) this.fixedPanel.getLayout()).show(this.fixedPanel, sceneName);
        this.scene = this.scenes.get(sceneName);
        this.scene.focus();
    }

    @Override
    public final Scene getScene() {
        return this.scene;
    }
}
