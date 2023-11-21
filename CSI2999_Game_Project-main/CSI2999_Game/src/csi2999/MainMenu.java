package csi2999;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.SwingUtilities;

public class MainMenu extends JFrame {
    public MainMenu(){
        super("Start Screen");
        
        // set my screen size
        setSize(800,600);
        
        //select color for the background
        Color backgroundColor = Color.BLUE; 
        
        // creating vertical stacked buttons
        JPanel buttonLayout = new JPanel();
        buttonLayout.setLayout(new BoxLayout(buttonLayout, BoxLayout.Y_AXIS));
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setting boreder layout box
        JPanel screen = new JPanel(new BorderLayout());
        
        screen.setBackground(backgroundColor);
       
        // create the title label
        JLabel title = new JLabel("CSI299", SwingConstants.CENTER);
        title.setFont(new Font("Times", Font.BOLD, 36));
        
        // create the start button
        JButton startButton = new JButton("Start Game" );
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Game is starting!");
                //BattleScreen battleScreen = new BattleScreen();
                //battleScreen.setVisible(true);
                
                setVisible(false);
                // Create instance of the Level One Class and start it
                LevelOne levelOne = new LevelOne();
                // Create the JFrame for Level one
                JFrame levelOneFrame = new JFrame("Level One");
                levelOneFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close the frame only
                //Add the Level One panel to JFrame
                levelOneFrame.add(levelOne);
                //Set size and visibility of the frame
                levelOneFrame.setSize(levelOne.getPreferredSize());
                levelOneFrame.pack(); // This will set the frame size based on LevelOne's preferred size
                levelOneFrame.setLocationRelativeTo(null);
                levelOneFrame.setVisible(true);
                // Starts the level
                levelOne.start();
            }
        });
        
        JButton loadButton = new JButton("Load Game");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Game is loading....");
                
            }
        });
        
        JButton exitButton = new JButton("Exit Game");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Game is Closing!");
                System.exit(0);
                
            }
        });
        
        JButton credButton = new JButton("Credits");
        credButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Michael Wilson"
                        + "\nNicholas Banks \nRobert Elle"
                        + "\nAndrae Taylor\nAiden Blitz");
                
            }
        });
        
        buttonLayout.add(startButton);
        buttonLayout.add(loadButton);
        buttonLayout.add(credButton);
        buttonLayout.add(exitButton);
        
        screen.add(title, BorderLayout.NORTH);
        screen.add(buttonLayout, BorderLayout.CENTER);
        
        add(screen);
        setLocationRelativeTo(null);
        setVisible(true);
       
    }
}