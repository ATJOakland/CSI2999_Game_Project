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

import characters.Player;

public class MainMenu extends JFrame {
	Player player;
	
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
        // setting border layout box
        JPanel screen = new JPanel(new BorderLayout());
        
        screen.setBackground(backgroundColor);
       
        // create the title label
        JLabel title = new JLabel("CSI2999", SwingConstants.CENTER);
        title.setFont(new Font("Times", Font.BOLD, 36));
        
        // create the start button
        JButton startButton = new JButton("Start Game" );
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Game is starting!");
                //BattleScreen battleScreen = new BattleScreen();
                //battleScreen.setVisible(true);
                //set resetHealth back to 200;
                characterClass resetHealth = new characterClass(0);
                resetHealth.setCurrentHealth(200);
                //setVisible(false);
                // Create instance of the Game Panel Class and start it
                GamePanel gamePanel = new GamePanel();
                // Create the JFrame for Game Panel
                JFrame gamePanelFrame = new JFrame("Game");
                gamePanelFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close the frame only
                //Add the Game panel to JFrame
                gamePanelFrame.add(gamePanel);
                //Set size and visibility of the frame
                gamePanelFrame.setSize(gamePanel.getPreferredSize());
                gamePanelFrame.pack(); // This will set the frame size based on LevelOne's preferred size
                gamePanelFrame.setLocationRelativeTo(null);
                gamePanelFrame.setVisible(true);
                // Starts the level (first level, first)
                gamePanel.start(0); //currentLevel is in the Player.java by default
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