package qbert.launcher;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.sun.glass.events.KeyEvent;

public class MenuLauncher {

    public static void main(String[] args) {
        new MenuLauncher();
    }

    public MenuLauncher() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Test");
                frame.add(new MenuPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class MenuPane extends JPanel {

        public MenuPane() {
            setBorder(new EmptyBorder(10, 10, 10, 10));
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;

            add(new JLabel("<html><h1><strong><i>Qbert</i></strong></h1><hr></html>"), gbc);

            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            
            JButton jb = new JButton("Start");
            jb.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent e) { 
                    QBertLauncher start = new QBertLauncher();
                } 
            });

            JPanel buttons = new JPanel(new GridBagLayout());
            buttons.add(jb, gbc);
            buttons.add(new JButton("Show scores"), gbc);
            buttons.add(new JButton("Help"), gbc);
            buttons.add(new JButton("Exit"), gbc);

            gbc.weighty = 1;
            add(buttons, gbc);
        }

    }

}
