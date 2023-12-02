package csi2999;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleScreen extends JFrame {
	private JPanel mainPanel;
	private JLabel player;
	private JLabel enemy;
	private JButton attackButton;
	private JButton defendButton;
	private JButton fireButton;
	private JButton iceButton;
	private JButton thunderButton;
	private JButton itemButton;
	private JButton escapeButton;
	private backGroundCombat battle, Orc, Pirate, Goblin;
	private JPanel buttonPanel;
	private JPanel invtButtonPanel;
	private ImageIcon playerSprite;
	private ImageIcon enemySprite;
	private JPanel damagePanel;
	private String monsterName;
	private JLabel playerRollLabel;
    private JLabel enemyRollLabel;
    private JLabel playerDamageLabel;
    private JLabel enemyDamageLabel;
    private String emyRoll, emyDmg; 
	private boolean playerTurn = false;
	private boolean cpuTurn = false;
	private int dmg = 0;

	public BattleScreen(int level) {
		setTitle("Get ready to fight");
		setSize(800, 650);

		battle = new backGroundCombat(level);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(Color.BLACK);
		
		//creating center Panel
		damagePanel = new JPanel();
	    damagePanel.setLayout(new BoxLayout(damagePanel, BoxLayout.Y_AXIS)); // Setting layout
	    damagePanel.setBackground(Color.yellow);
	    
	    // Initialize the labels
	    String plyRoll, plyDmg ;
	    plyRoll = "Player Roll: ";
	    this.emyRoll = "Enemy Roll: ";
	    plyDmg = "Player Damage to: ";
	    this.emyDmg = "Enemy Damage to: ";
        playerRollLabel = new JLabel(plyRoll);
        enemyRollLabel = new JLabel(this.emyRoll);
        playerDamageLabel = new JLabel(plyDmg);
        enemyDamageLabel = new JLabel(this.emyDmg);

       // Set font 
        Font labelFont = new Font("Serif", Font.BOLD, 19);
        playerRollLabel.setFont(labelFont);
        enemyRollLabel.setFont(labelFont);
        playerDamageLabel.setFont(labelFont);
        enemyDamageLabel.setFont(labelFont);
        
		String resourcePath = "player.png";
		playerSprite = new ImageIcon(BattleScreen.class.getResource(resourcePath));

		if (playerSprite == null) {
			System.err.println("Resource not found: " + resourcePath);
		}

		// file path for sprite need here
		player = new JLabel("Player");// once we have a sprite rename this playerSprite
		player.setIcon(playerSprite);
		player.setForeground(Color.red);
		player.setPreferredSize(new Dimension(300, 210));
                
        monsterName = battle.getMonsterName();
        

        enemySprite = new ImageIcon(BattleScreen.class.getResource(monsterName + ".png"));// file path for sprite need here
		enemy = new JLabel(monsterName);// once we have a sprite rename this enemySprite
        enemy.setIcon(enemySprite);
		enemy.setForeground(Color.red);
		enemy.setPreferredSize(new Dimension(300, 220));

		damagePanel = new JPanel();
		damagePanel.setLayout(new BoxLayout(damagePanel, BoxLayout.Y_AXIS));
		damagePanel.setBackground(Color.yellow);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setBackground(Color.black);

		invtButtonPanel = new JPanel();
		invtButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		invtButtonPanel.setBackground(Color.black);

		attackButton = new JButton("Attack");
		attackButton.setPreferredSize(new Dimension(100, 50));
		attackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				int dmg = battle.getPlayerAttack();
				playerTurn(dmg);
				enemyTurn();
				// update Label
				
				player.setText("Health " + battle.getPlyHlth() + "/" + battle.maxHealthPly());
				enemy.setText("Enemy:" + battle.getEnemyHealth() + "/" + battle.getMaxEmyHealth());
				playerRollLabel.setText(plyRoll + Integer.toString(battle.getDiceRoll()));
				playerDamageLabel.setText(plyDmg + Integer.toString(dmg));
				
				
			}
		});
		defendButton = new JButton("Defend");
		defendButton.setPreferredSize(new Dimension(100, 50));
		defendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				battle.setDefend();
				playerTurn = true;
				cpuTurn = false;
				enemyTurn(); 
			}
		});
		iceButton = new JButton("ICE");
		iceButton.setPreferredSize(new Dimension(100, 50));
		iceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				
				int dmg = battle.applySpecialDamage("Ice");
				playerTurn(dmg);
				enemyTurn();
				// update Label
				JOptionPane.showMessageDialog(null, "Ice Damge" + dmg);
				player.setText("Health " + battle.getPlyHlth() + "/" + battle.maxHealthPly());
				enemy.setText("Enemy:" + battle.getEnemyHealth() + "/" + battle.getMaxEmyHealth());
				playerRollLabel.setText(plyRoll + Integer.toString(battle.getSpecDiceRoll()));
				playerDamageLabel.setText(plyDmg + Integer.toString(dmg));
				
				
			}
		});
		fireButton = new JButton("Fire");
		fireButton.setPreferredSize(new Dimension(100, 50));
		fireButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				
				int dmg = battle.applySpecialDamage("Fire");
				playerTurn(dmg);
				enemyTurn();
				// update Label
				JOptionPane.showMessageDialog(null, "Fire Damge" + dmg);
				player.setText("Health " + battle.getPlyHlth() + "/" + battle.maxHealthPly());
				enemy.setText("Enemy:" + battle.getEnemyHealth() + "/" + battle.getMaxEmyHealth());
				playerRollLabel.setText(plyRoll + Integer.toString(battle.getSpecDiceRoll()));
				playerDamageLabel.setText(plyDmg + Integer.toString(dmg));
				
				
			}
		});
		thunderButton = new JButton("Thunder");
		thunderButton.setPreferredSize(new Dimension(100, 50));
		thunderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				
				int dmg = battle.applySpecialDamage("Thunder");
				playerTurn(dmg);
				enemyTurn();
				// update Label
				JOptionPane.showMessageDialog(null, "Thunder Damge" + dmg);
				player.setText("Health " + battle.getPlyHlth() + "/" + battle.maxHealthPly());
				enemy.setText("Enemy:" + battle.getEnemyHealth() + "/" + battle.getMaxEmyHealth());
				playerRollLabel.setText(plyRoll + Integer.toString(battle.getSpecDiceRoll()));
				playerDamageLabel.setText(plyDmg + Integer.toString(dmg));
				
				
			}
		});
		itemButton = new JButton("Items");
		itemButton.setPreferredSize(new Dimension(100, 50));
		itemButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				String[] options = {"Potion", "Hi-Potion", "Elixer"};

                // Show the popup and get the selected option
                int selectedOption = JOptionPane.showOptionDialog(
                        null,                  // Parent component (null for default)
                        "Select an option:",   // Message to display
                        "Item Options",        // Dialog title
                        JOptionPane.DEFAULT_OPTION,  // Option type
                        JOptionPane.PLAIN_MESSAGE,  // Message type
                        null,                  // Icon (null for default)
                        options,               // Array of options
                        options[0]);           // Default selected option

                // Handle the selected option
                if (selectedOption >= 0) {
                    if(selectedOption == 0) { // Potion
                        battle.setHeal(50);
                        JOptionPane.showMessageDialog(null,"Amount healed: " + battle.setHeal(50));
                        player.setText("Health " + battle.getPlyHlth() + "/" + battle.maxHealthPly());
                    } else if(selectedOption == 1) { // Hi-Potion
                        // Handle Hi-Potion
                    	JOptionPane.showMessageDialog(null, battle.setHeal(100));
                    	player.setText("Health " + battle.getPlyHlth() + "/" + battle.maxHealthPly());// Example healing amount for Hi-Potion
                    } else if(selectedOption == 2) { // Elixer
                        // Handle Elixer
                    	JOptionPane.showMessageDialog(null, battle.setHeal(500));
                    	player.setText("Health " + battle.getPlyHlth() + "/" + battle.maxHealthPly());// Example healing amount for Elixer
                    }
                }
            }
        });
		escapeButton = new JButton("Escape");
		escapeButton.setPreferredSize(new Dimension(100, 50));
		escapeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				int roll;
				roll = battle.getDiceRoll();
				if(roll % 2 == 1) {
					battleOver(true);
				}
                            
			}
		});


		buttonPanel.add(attackButton);
		buttonPanel.add(defendButton);
		buttonPanel.add(iceButton);
		buttonPanel.add(fireButton);
		buttonPanel.add(thunderButton);
		buttonPanel.add(itemButton);
		buttonPanel.add(escapeButton);

        
		// Add labels to the panel
        damagePanel.add(playerRollLabel);
        damagePanel.add(enemyRollLabel);
        damagePanel.add(playerDamageLabel);
        damagePanel.add(enemyDamageLabel);


		mainPanel.add(damagePanel, BorderLayout.CENTER);
		mainPanel.add(player, BorderLayout.WEST);
		mainPanel.add(enemy, BorderLayout.EAST);
		// mainPanel.add(attackButton, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		add(mainPanel);
	}

	public boolean battleOver(boolean escape) {
		if (battle.deadOrAlive(battle.getEnemyHealth()) == false) {
			//setVisible(false);
			//battle.battleEnd();
			//JOptionPane.showMessageDialog(null, "Enemy Defeated!");
			setVisible(false);
	        battle.battleEnd();
	        updateEnemySprite(); // Update the enemy sprite for the new monster
	        JOptionPane.showMessageDialog(null, "Enemy Defeated!");
			return true;
		}else if(escape == true) {
			setVisible(false);
			battle.battleEnd();
			JOptionPane.showMessageDialog(null, "Escaped");
			return true;
		}
		else
			return false;
	}

	public void playerTurn(int dmg) {
		// int dmg = battle.getPlayerAttack();
		if (playerTurn == false) {
			JOptionPane.showMessageDialog(null, "You Damange the enemy: " + dmg); 
			battle.setEnemyHealth(dmg);
			playerTurn = true;
			cpuTurn = false;
			battleOver(false);
		} else {
			JOptionPane.showMessageDialog(null, "Please wait for cpu turn!");
		}
	}

	public void enemyTurn() {
		int emyDmg;
		
		emyDmg = battle.getEnenmyAtk();
		enemyRollLabel.setText(this.emyRoll + Integer.toString(battle.getEmyDiceRoll()));
		if (cpuTurn == false) {
                        try {
                            Thread.sleep(1000);
                          } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                          }
			battle.setPlyhealth(emyDmg);
			JOptionPane.showMessageDialog(null, "Enemy Damges you" + emyDmg); 
			enemyDamageLabel.setText(this.emyDmg + Integer.toString(emyDmg));
			battleOver(false);
			playerTurn = false;
			cpuTurn = true;
		} else {
			JOptionPane.showMessageDialog(null, "Please wait for cpu turn!");
		}
	}
	public void updateEnemySprite() {
	    monsterName = battle.getMonsterName();
	    enemySprite = new ImageIcon(BattleScreen.class.getResource(monsterName + ".png"));
	    enemy.setIcon(enemySprite);
	    enemy.setText(monsterName);
	}
	
}

