package qbert.view;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import qbert.controller.Controller;
import qbert.controller.GameStatus;
import qbert.model.utilities.Dimensions;
import qbert.view.scenes.Scene;
import qbert.view.scenes.SceneGame;
import qbert.view.scenes.SceneGameOver;
import qbert.view.scenes.SceneIntro;
import qbert.view.scenes.SceneMenu;
import qbert.view.scenes.SceneRanking;

/**
 * The implementation of {@link View}.
 */
public class ViewImpl implements View {

    private final JFrame frame;
    private final JPanel fixedPanel;
    private Scene scene;
    private final Map<GameStatus, Scene> scenes;

    private final int w;
    private final int h;

    /**
     * Initialize the frame and the window.
     */
    public ViewImpl() {
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        Dimensions.setWindowHeight(Math.round(new Float(d.height)));
        h = Dimensions.getWindowHeight();
        Dimensions.setWindowWidth(Math.round(new Float(d.width)));
        w = Dimensions.getWindowWidth();

        this.frame = new JFrame("Qbert");
        fixedPanel = new JPanel(new CardLayout());
        frame.getContentPane().add(this.fixedPanel);
        frame.setSize(w, h);
        frame.setMinimumSize(new Dimension(w, h));
        frame.setResizable(false);

        this.scenes = new HashMap<>();
    }

    @Override
    public final void initialize(final Controller controller) {
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent ev) {
                controller.terminate();
            }
            public void windowClosed(final WindowEvent ev) {
                controller.terminate();
            }
        });

        this.addScene(new SceneIntro(w, h, controller), GameStatus.INTRODUCTION);
        this.addScene(new SceneGame(w, h, controller), GameStatus.GAMEPLAY);
        this.addScene(new SceneMenu(w, h, controller), GameStatus.MENU);
        this.addScene(new SceneRanking(w, h, controller), GameStatus.RANKING);
        this.addScene(new SceneGameOver(w, h, controller), GameStatus.GAMEOVER);

        if (!this.scenes.keySet().equals(GameStatus.getAll())) {
            final String errorMessage = "Program aborted. Not all the game status have been initialized.";
            Logger.getGlobal().log(Level.SEVERE, errorMessage);
            controller.forceQuit(errorMessage);
        } else {
            frame.pack();
            frame.setVisible(true);
        }
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
    public final void addScene(final Scene scene, final GameStatus status) {
        this.fixedPanel.add((Component) scene, status.name());
        this.scenes.put(status, scene);
    }

    @Override
    public final void setScene(final GameStatus status) {
        ((CardLayout) this.fixedPanel.getLayout()).show(this.fixedPanel, status.name());
        this.scene = this.scenes.get(status);
        this.scene.focus();
    }

    @Override
    public final Scene getScene() {
        return this.scene;
    }

    @Override
    public final void closeWindow() {
        this.frame.setVisible(false);
        this.frame.dispose();
    }

    @Override
    public final void showErrorMessageBox(final String message) {
        JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);
        this.closeWindow();
    }

    @Override
    public final synchronized void play(final Queue<Clip> clipToPlay) {
        new Thread(new Runnable() {
            public void run() {
                clipToPlay.poll().start();
            }
        }).start();
    }
}
