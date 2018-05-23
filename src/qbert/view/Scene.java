package qbert.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import qbert.controller.GameEngine;
import qbert.input.MoveDown;
import qbert.input.MoveLeft;
import qbert.input.MoveRight;
import qbert.input.MoveUp;
import qbert.model.Level;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.model.utilities.Sprites;

public class Scene {
    private final JFrame frame;
    private final ScenePanel panel;
    private final Level level;
    private final GameEngine controller;

    public Scene(final Level level, final int w, final int h, final GameEngine controller) {
        this.frame = new JFrame("Qbert Test");
        this.frame.setSize(w, h);
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
        this.controller = controller;
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

    public class ScenePanel extends JPanel implements KeyListener {
        // Temp Variables
        private Font custom; 
        private BufferedImage lifeSprite;

        public ScenePanel(final Level level, final int w, final int h) {
            this.setSize(w, h);
            this.setBackground(Color.black);
            this.addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            requestFocusInWindow(); 

            // Temporary Font
            try {
                URL url = getClass().getResource("/arcade_n.ttf");
                File fontFile = new File(url.getPath());
                this.custom = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont((float) Dimensions.windowHeight / 50);
                GraphicsEnvironment ge = 
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(this.custom);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Temporary Sprite Loading
            this.lifeSprite = Sprites.life;
        }

        public void render() {
            this.repaint();
        }

        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);

            // Level components rendering
            g.drawImage(level.getBackground(), Dimensions.backgroundX, Dimensions.backgroundY, this);
            
            /**
             *      level.getGameObjects().stream().sorted((a, b) -> a.getCurrentPosition().getY() - b.getCurrentPosition().getY() ).forEach(c -> {
             *          g.drawImage(c.getGraphicComponent().getSprite(), c.getGraphicComponent().getPosition().getY(), c.getGraphicComponent().getPosition().getX(), this);
             *      });
             */
            level.getMap().getTileList().stream().forEach(e -> {
                /*GraphicComponent c = e.getGraphicComponent();
                Position2D pos = level.getMap().getPhysical(c.getPosition());
                g.drawImage(c.getSprite(), pos.getX(), pos.getY(), this);*/

                GraphicComponent c = e.getGraphicComponent();
                g.drawImage(c.getSprite(), c.getPosition().getX(), c.getPosition().getY(), this);
            });
            
            level.getMap().getDiskList().stream().forEach(e -> {
                GraphicComponent c = e.getDiskGraphicComponent();
                g.drawImage(c.getSprite(), c.getPosition().getX(), c.getPosition().getY(), this);
            });

            level.getEntities().stream().forEach(e -> {
                GraphicComponent c = e.getGraphicComponent();
                g.drawImage(c.getSprite(), c.getPosition().getX(), c.getPosition().getY(), this);
            });

            g.drawImage(level.getQBert().getGraphicComponent().getSprite(), level.getQBert().getGraphicComponent().getPosition().getX(), level.getQBert().getGraphicComponent().getPosition().getY(), this);

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
            
            int unit = Dimensions.windowWidth / 48;
            g.drawString("DEBUG:", unit * 2, unit * 20);
            g.drawString("L - Gain Life", unit * 2, unit * 20 + unit);
            g.drawString("D - Loose Life", unit * 2, unit * 20 + unit * 2);
            g.drawString("I - God Mode (" + (level.immortality ? "on" : "off") + ")", unit * 2, unit * 20 + unit * 3);
            g.drawString("P - Pause Game (" + (level.update ? "running" : "paused") + ")", unit * 2, unit * 20 + unit * 4);
            g.drawString("E - Pause Entities (" + (level.updateEntities ? "running" : "paused") + ")", unit * 2, unit * 20 + unit * 5);
            g.drawString("Q - Quit", unit * 2, unit * 20 + unit * 6);
        }

        @Override
        public void keyTyped(final KeyEvent e) {

        }

        @Override
        public void keyPressed(final KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_KP_UP) {
                Scene.this.controller.notifyCommand(new MoveUp());
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_KP_DOWN) {
                Scene.this.controller.notifyCommand(new MoveDown());
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_KP_RIGHT) {
                Scene.this.controller.notifyCommand(new MoveRight());
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_KP_LEFT) {
                Scene.this.controller.notifyCommand(new MoveLeft());
            } else { //Debug keys
                if (e.getKeyCode() == KeyEvent.VK_L) {
                    level.gainLife();
                } else if (e.getKeyCode() == KeyEvent.VK_D) {
                    level.death();;
                } else if (e.getKeyCode() == KeyEvent.VK_I) {
                    level.toggleImmortality();
                } else if (e.getKeyCode() == KeyEvent.VK_P) {
                    level.toggleTime();
                } else if (e.getKeyCode() == KeyEvent.VK_E) {
                    level.toggleEntities();
                } else if (e.getKeyCode() == KeyEvent.VK_Q) {
                    System.exit(0);
                }
            }
        }

        @Override
        public void keyReleased(final KeyEvent e) {

        }
    }
}
