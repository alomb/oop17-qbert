package qbert.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import qbert.model.Level;

public class Scene {    
    private final JFrame frame;
    private final ScenePanel panel;
    private final Level level;

    public Scene(final Level level, final int w, final int h) {
        this.frame = new JFrame("Qbert Test");
        this.frame.setSize(w,h);
        this.frame.setMinimumSize(new Dimension(w, h));
        this.frame.setResizable(false);
        this.panel = new ScenePanel(level, w, h);
        this.frame.getContentPane().add(this.panel);
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
        this.level = level;
    }

    public void render() {
        try {
            SwingUtilities.invokeAndWait(() -> {
                this.panel.render();
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public class ScenePanel extends JPanel {
        public ScenePanel(final Level level, final int w, final int h) {
            setSize(w, h);
            this.setBackground(Color.black);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            requestFocusInWindow(); 
        }

        public void render() {
            this.paintComponent(this.getGraphics());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            //TODO: Migliorare rendering
            g.drawImage(level.getBackground(), 40, 40, this);

            level.getEntities().stream().forEach(e -> {
                GraphicComponent c = e.getGraphicComponent();
                g.drawImage(c.getSprite(), 100, 100, this);
            });

            level.getTiles().stream().forEach(e -> {
                GraphicComponent c = e.getGraphicComponent();
                g.drawImage(c.getSprite(), (int) c.getPosition().getX(), (int) c.getPosition().getY(), this);
            });
        }
    }
}
