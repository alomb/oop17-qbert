package qbert.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import qbert.model.Level;
import qbert.model.Sprites;
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
        // Temp Variables
        private Mapper mapper;
        private Font custom; 
        private BufferedImage background;
        private BufferedImage lifeSprite;

        public ScenePanel(final Level level, final Mapper mapper, final int w, final int h) {
            this.setSize(w, h);
            this.setBackground(Color.black);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            requestFocusInWindow(); 

            // Temporary Font
            try {
                URL url = getClass().getResource("/arcade_n.ttf");
                File fontFile = new File(url.getPath());
                this.custom = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(18.0f);
                GraphicsEnvironment ge = 
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(this.custom);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Temporary Mapper
            this.mapper = mapper;

            //Temporary Sprite Loading
            this.background = Sprites.blueBackground;
            this.lifeSprite = Sprites.life;


            //TODO: Remove
            // Qbert Movement Simulation
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    level.getTiles().stream().forEach(t -> {
                        GraphicComponent c = t.getGraphicComponent();
                        Position2D pos = mapper.getPhysical(c.getPosition());
                        if (e.getX() >= pos.getX() && e.getX() <= pos.getX() + c.getSpriteWidth()
                            && e.getY() >= pos.getY() && e.getY() <= pos.getY() + c.getSpriteHeight()) {
                            if (level.step(t)) {
                                level.score(50);
                            }
                        }
                    });
                    level.checkStatus();
                }
            });

            // Qbert Death Simulation
            JButton sim1 = new JButton("Simulate Qbert's Death");
            sim1.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    level.death();
                }
            });
            this.add(sim1);
        }

        public void render() {
            this.repaint();
        }

        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);

            // Level components rendering
            g.drawImage(this.background, (int) mapper.getMapPos().getX(), (int)  mapper.getMapPos().getY(), this);

            level.getTiles().stream().forEach(e -> {
                GraphicComponent c = e.getGraphicComponent();
                Position2D pos = mapper.getPhysical(c.getPosition());
                g.drawImage(c.getSprite(), (int) pos.getX(), (int) pos.getY(), this);
            });

            level.getEntities().stream().forEach(e -> {
                GraphicComponent c = e.getGraphicComponent();
                g.drawImage(c.getSprite(), (int) c.getPosition().getX(), (int) c.getPosition().getY(), this);
            });

            // Info rendering
            g.setColor(new Color(255, 255, 255));
            g.setFont(this.custom);
            g.drawString("Player:", 40, 40);
            g.drawString("" + level.getPoints(), 40, 80);

            g.drawString("Level:", 1200, 300);
            g.drawString("" + level.getLevel(), 1400, 300);

            g.drawString("Round:", 1200, 330);
            g.drawString("" + level.getRound(), 1400, 330);

            for (int i = 0, posY = 0; i < level.getLives(); i++, posY += this.lifeSprite.getHeight() + 10) {
                g.drawImage(this.lifeSprite, 60, 190 + posY, this);
            }

            g.drawString("Hi-Score:", 1000, 40);
            g.drawString("1500", 1200, 40);
        }
    }
}
