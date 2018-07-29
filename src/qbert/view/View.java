package qbert.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import qbert.controller.Controller;
import qbert.model.Game;
import qbert.model.Introduction;
import qbert.model.utilities.Dimensions;

public class View {
    private final JFrame frame;
    private final JPanel fixedPanel;
    private SceneImpl scene;
    private Controller controller;
    private Game game;

    private Map<String, SceneImpl> scenes;

    public View(final Controller controller, final Game game, final Introduction introduction) {
        int w = Dimensions.getWindowWidth(), h = Dimensions.getWindowHeight();
        this.frame = new JFrame("Qbert");
        this.fixedPanel = new JPanel(new CardLayout());
        this.frame.getContentPane().add(this.fixedPanel);
        this.frame.setSize(w, h);
        this.frame.setMinimumSize(new Dimension(w, h));
        this.frame.setResizable(false);

        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent ev) {
                System.exit(-1);
            }
            public void windowClosed(final WindowEvent ev) {
                System.exit(-1);
            }
        });
        this.frame.pack();
        this.frame.setVisible(true);
        this.controller = controller;

        this.scenes = new HashMap<>();

        this.addScene(new SceneIntro(introduction, Dimensions.getWindowWidth(), Dimensions.getWindowHeight(), controller), "SceneIntro");
        this.addScene(new SceneGame(game, Dimensions.getWindowWidth(), Dimensions.getWindowHeight(), controller), "SceneGame");
    }

    public void render() {
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

    public void addScene(final SceneImpl scene, final String sceneName) {
        this.fixedPanel.add(scene, sceneName);
        this.scenes.put(sceneName, scene);
    }

    public void setScene(final String sceneName) {
        ((CardLayout) this.fixedPanel.getLayout()).show(this.fixedPanel, sceneName);
        this.scene = this.scenes.get(sceneName);
        this.scene.focus();
    }

    public SceneImpl getScene() {
        return this.scene;
    }
}
