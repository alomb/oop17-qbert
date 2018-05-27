package qbert.launcher;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.sun.glass.events.KeyEvent;

public class MenuLauncher {

    public static void main(String[] args) {
        new MenuLauncher();
    }

    public MenuLauncher() {
        JFrame frame = new JFrame("Test");
        frame.add(new MenuPane());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
          Set forwardKeys = frame.getFocusTraversalKeys(
          KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS); 
    
          Set newForwardKeys = new HashSet(forwardKeys); 
    
          // add the UP ARROW key 
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
    
          // add the LEFT ARROW key 
          newBackwardKeys.add(KeyStroke.getKeyStroke(
              KeyEvent.VK_LEFT, 0)); 
    
          //apply your  new set of keys
          frame.setFocusTraversalKeys(
          KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, 
              newBackwardKeys); 
    }
        

    public class MenuPane extends JPanel {

        public MenuPane() {
            setBorder(new EmptyBorder(10, 10, 10, 10));
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;

            add(new JLabel("<html><h1><strong><i>QBert</i></strong></h1><hr></html>"), gbc);

            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JPanel buttons = new JPanel(new GridBagLayout());
            JButton jbstart = new JButton("Start");
            jbstart.addActionListener(new ActionListener() { 
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
            buttons.add(jbstart, gbc);
            buttons.add(new JButton("Show scores"), gbc);
            buttons.add(new JButton("Help"), gbc);
            buttons.add(new JButton("Exit"), gbc);

            gbc.weighty = 1;
            add(buttons, gbc);
        }

    }

}


