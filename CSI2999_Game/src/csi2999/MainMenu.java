package csi2999;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import characters.Player;
public class MainMenu extends JFrame {

    UserInput u;

    public MainMenu(){
        super("Start Screen");
        setSize(800,600);
        Color backgroundColor = Color.RED;

        // Creating a panel with GridBagLayout for better control over the layout
        JPanel buttonLayout = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0); // Add some spacing between buttons

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel screen = new JPanel(new BorderLayout());
        screen.setBackground(backgroundColor);

        JLabel title = new JLabel("Undead Commander Revenge", SwingConstants.CENTER);
        title.setFont(new Font("Times", Font.BOLD, 36));

        // start game a code logic
        JButton startButton = new JButton("Start Game");
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
                JOptionPane.showMessageDialog(null, "Game is loading....");
                /////////////////////////////////////////////////////////////////////
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
                //gamePanel.start(0); //currentLevel is in the Player.java by default
                //new player 
                Player player = new Player(gamePanel,u);
                ////////////////////////////////////////
                gamePanel.saveLoad.load();
                gamePanel.start(gamePanel.player.currentLevel);
                //currentLevel is in the Player.java by default
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

        // Add buttons to the layout
        buttonLayout.add(startButton, gbc);
        buttonLayout.add(loadButton, gbc);
        buttonLayout.add(credButton, gbc);
        buttonLayout.add(exitButton, gbc);

        // Adding the title and buttonLayout to the screen panel
        screen.add(title, BorderLayout.NORTH);

        // Wrapping buttonLayout in another panel to center it
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(buttonLayout);
        screen.add(centerPanel, BorderLayout.CENTER);

        add(screen);
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
}