package qbert.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import qbert.model.Level;
import qbert.model.mapping.Mapper;
import qbert.model.utilities.Position2D;

public class Scene {    
    private final JFrame frame;
    private final ScenePanel panel;
    private final Level level;

    public Scene(final Level level, final Mapper mapper, final int w, final int h) {
        this.frame = new JFrame("Qbert Test");
        this.frame.setSize(w, h);
        this.frame.setMinimumSize(new Dimension(w, h));
        this.frame.setResizable(false);
        this.panel = new ScenePanel(level, mapper, w, h);
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
        Mapper mapper;
        Font custom;
        public ScenePanel(final Level level, final Mapper mapper, final int w, final int h) {
            
            //Temporary location of font loader for testing purposes

            // This font is < 35Kb.
            try {
                String fName = "C:\\Users\\Alessandro\\eclipse-workspace\\qbert\\res\\ARCADE_N.ttf";
                File fontFile = new File(fName);
                this.custom = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(18.0f);
                GraphicsEnvironment ge = 
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(this.custom);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            
            this.mapper = mapper;
            setSize(w, h);
            this.setBackground(Color.black);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            requestFocusInWindow(); 
        }

        public void render() {
            this.repaint();
        }

        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);

            //TODO: Migliorare rendering
            g.drawImage(level.getBackground(), (int) mapper.getMapPos().getX(), (int)  mapper.getMapPos().getY(), this);

            level.getTiles().stream().forEach(e -> {
                GraphicComponent c = e.getGraphicComponent();
                Position2D pos = mapper.getPhysical(c.getPosition());
                g.drawImage(c.getSprite(), (int) pos.getX(), (int) pos.getY(), this);
            });

            level.getEntities().stream().forEach(e -> {
                GraphicComponent c = e.getGraphicComponent();
                g.drawImage(c.getSprite(), 100, 100, this);
            });

            //Tests for text rendering
            g.setColor(new Color(255, 255, 255));
            g.setFont(this.custom);
            g.drawString("Qbert",40,40);
            g.drawString("Level: " + ((level.getRound() + 2) / 3), 40, 70);
            g.drawString("Round: " + ((level.getRound() + 2) % 3 + 1), 40, 110);
            g.drawString("Points: " + level.getPoints(), 40, 150);
        }
    }
}
