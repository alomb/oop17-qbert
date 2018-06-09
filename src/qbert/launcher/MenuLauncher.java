package qbert.launcher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.sun.glass.events.KeyEvent;

public class MenuLauncher {

    public static void main(String[] args) {
        new MenuLauncher();
    }


    @SuppressWarnings("unchecked")
    public MenuLauncher() {
        JFrame frame = new JFrame("Test");
        frame.add(new MenuPane());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //gets the default forward traversal keys (tab)
        Set forwardKeys = frame.getFocusTraversalKeys(
        KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS); 

        // your own set of forward traversal keys
        Set newForwardKeys = new HashSet(forwardKeys); 

        // add the RIGHT UP key 
        newForwardKeys.add(KeyStroke.getKeyStroke(
            KeyEvent.VK_UP, 0)); 

        //apply your  new set of keys
        frame.setFocusTraversalKeys(
        KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, 
            newForwardKeys); 

        //gets the default backward traversal keys (shift-tab)
        Set backwardKeys = frame.getFocusTraversalKeys(
        KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS); 

        // your own set of backward traversal keys
        Set newBackwardKeys = new HashSet(backwardKeys); 

        // add the LEFT DOWN key 
        newBackwardKeys.add(KeyStroke.getKeyStroke(
            KeyEvent.VK_DOWN, 0)); 

        //apply your  new set of keys
        frame.setFocusTraversalKeys(
        KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, 
            newBackwardKeys); 
        
    }
        

    public class MenuPane extends JPanel{

        public MenuPane(){
            setBorder(new EmptyBorder(10, 10, 10, 10));
            setLayout(new GridBagLayout());
            setBackground(Color.BLACK);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;
            
            add(new JLabel("<html><h1><strong><i>QBert</i></strong></h1><hr></html>"), gbc);

            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            
            UIManager.put("Button.focus", Color.red);
            JPanel buttons = new JPanel(new GridBagLayout());
            JButton jbStart = new JButton("Start");
            setButtonTheme(jbStart);
            jbStart.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent e) { 
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            QBertLauncher start = new QBertLauncher();       
                        }
                       });
                    t.start();
                  } 
                } );
            buttons.add(jbStart, gbc);

            buttons.add(setButtonTheme(new JButton("Show scores")), gbc);
            buttons.add(setButtonTheme(new JButton("Help")), gbc);
            buttons.add(setButtonTheme(new JButton("Exit")), gbc);

            gbc.weighty = 1;
            add(buttons, gbc);
            
        }
        
        
        private JButton setButtonTheme(JButton n) {
            File fontFile;
            Font font;
            Font sizedFont=new Font("Tahoma", Font.BOLD, 12);
            try {
                fontFile = new File("res/arcade_n.ttf");
                font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
                sizedFont = font.deriveFont(12f);
            } catch (FontFormatException | IOException e1) {
                e1.printStackTrace();
            }
            
            n.setBackground(new Color(0, 0, 0));
            n.setBorderPainted(false);
            n.setForeground(Color.WHITE);
            n.setFocusPainted(false);
            n.setFont(sizedFont);
            n.addFocusListener(new FocusListener() {

                @Override
                public void focusLost(FocusEvent e) {
                    n.setForeground(Color.white);
                }

                @Override
                public void focusGained(FocusEvent e) {
                    n.setForeground(Color.red);
                }
            });
            return n;
        }
    }

}


