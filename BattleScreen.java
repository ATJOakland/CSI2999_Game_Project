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
    private backGroundCombat battle = new backGroundCombat();
    private JPanel buttonPanel;
    private JPanel invtButtonPanel;
    private ImageIcon playerSprite;
    private ImageIcon enemySprite;
    private JLabel damageBox; 
    private JPanel damagePanel;
    private JLabel damageButton;
    
    private boolean playerTurn = false;
    private boolean cpuTurn = false;
    
    private randomEncouterClass monster = new randomEncouterClass();
    private int dmg = 0;
    public BattleScreen(){
        setTitle("Get ready to fight");
        setSize(800,600);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);
        
        
        
        playerSprite = new ImageIcon("player.png");// file path for sprite need here
        player = new JLabel("Player");//once we have a sprite rename this playerSprite
        player.setForeground(Color.red);
        player.setPreferredSize(new Dimension(300,200));
        
        enemySprite = new ImageIcon("player.png");// file path for sprite need here
        enemy = new JLabel(monster.generateMonster());//once we have a sprite rename this enemySprite
        enemy.setForeground(Color.red);
        enemy.setPreferredSize(new Dimension(300,200));
        
        damageBox = new JLabel("");
        damageBox.setForeground(Color.BLUE);
        damageBox.setPreferredSize(new Dimension(100,75));
        damageBox.setBackground(Color.YELLOW);
        
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
        attackButton.setPreferredSize(new Dimension(100,50));
        attackButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent a){
                int dmg = battle.getPlayerAttack();
                playerTurn(dmg);
                enemyTurn();
                //update Label
                player.setText("Health " + battle.getPlyHlth());
                enemy.setText("Enemy Health" + battle.getEnemyHealth());
                damageBox.setText(Integer.toString(dmg));
                
            }
        });
        defendButton = new JButton("Defend");
        defendButton.setPreferredSize(new Dimension(100,50));
        defendButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent a){
                
                
                
            }
        });
        iceButton = new JButton("ICE");
        iceButton.setPreferredSize(new Dimension(100,50));
        iceButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent a){
                
            }
        });
        fireButton = new JButton("Fire");
        fireButton.setPreferredSize(new Dimension(100,50));
        fireButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent a){
                
                
            }
        });
        thunderButton = new JButton("Thunder");
        thunderButton.setPreferredSize(new Dimension(100,50));
        thunderButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent a){
                
                
            }
        });
        itemButton = new JButton("Items");
        itemButton.setPreferredSize(new Dimension(100,50));
        itemButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent a){
               JOptionPane.showMessageDialog(null, "Potion \n High Potion");
                
            }
        });
        escapeButton = new JButton("Escape");
        escapeButton.setPreferredSize(new Dimension(100,50));
        escapeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent a){
                
                
            }
        });
        
        JLabel damageLabel = new JLabel("Damage");
        damageLabel.setPreferredSize(new Dimension(200,50));
        damageLabel.setFont(new Font("Serif", Font.BOLD, 24));
        
        
        buttonPanel.add(attackButton);
        buttonPanel.add(defendButton);
        buttonPanel.add(iceButton);
        buttonPanel.add(fireButton);
        buttonPanel.add(itemButton);
        buttonPanel.add(escapeButton);
        
        damagePanel.add(damageLabel);
        damagePanel.add(damageBox);
        
        mainPanel.add(damagePanel, BorderLayout.CENTER);
        mainPanel.add(player, BorderLayout.WEST);
        mainPanel.add(enemy, BorderLayout.EAST);
       // mainPanel.add(attackButton, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    public boolean battleOver() {
    	if(battle.deadOrAlive(battle.getEnemyHealth()) == false) {
        	setVisible(false);
        	return true;
        } else return false;
    }
    public void playerTurn(int dmg) {
    	//int dmg = battle.getPlayerAttack();
        if(playerTurn == false) {
        	battle.setEnemyHealth(dmg);
        	playerTurn = true;
        	cpuTurn = false;
        	battleOver();}
        else {
        	System.out.println("Please wait for cpu turn!");
        }
    }
    
    public void enemyTurn() {
    	int dmg = battle.getEnenmyAtk();
    	if(cpuTurn == false) {
        	battle.setPlyhealth(dmg);
        	playerTurn = false;
        	cpuTurn = true;
        	battleOver();}
        else {
        	System.out.println("Please wait for cpu turn!");
        }
    }
}
 
