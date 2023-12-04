/* 

SCRIPT REFERENCE FROM THIS TUTORIAL:

https://www.youtube.com/watch?v=om59cwR7psI&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=1

Google and ChatGPT also used for reference of some aspects like checking if game
is running

*/

package csi2999;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

import characters.Player;
import tiles.TileManager;

public class GamePanel extends JPanel implements Runnable{
	// Creates window
	public final int defaultTileSize = 16; // ALL tiles are 16x16 size. This is the standard tile size for most games
	final int scale = 3; //For scaling up the tiles
	public final int tileSize = defaultTileSize * scale; // Scales up or down the tile size.
	
	// setting a count and creating objects for the battle system.
	private int battleCnt = 0;
	private final int maxBattle = 1000;
	private boolean isBattleActive = false;
	private RandomTimer battleTimer = new RandomTimer(5000, 9000);
	////////////public
	private final boolean flag = false;
	public BattleScreen battle = new BattleScreen(1, flag);

	//Putting tiles on screen
	public final int maxScreenColumnTiles = 16;
	public final int maxScreenRowTiles = 12;
	
	public final int screenWidth = tileSize * maxScreenColumnTiles;
	public final int screenHeight = tileSize * maxScreenRowTiles;
	
	// THE GAME'S CLOCK (for making the game 60FPS can continuously calling actions
	Thread gameThread;
	// FPS
	final int FPS = 60;
	// Initializes the User Input script
	UserInput userInput = new UserInput();
	// Gets a reference to the player in the characters package
	Player player = new Player(this, userInput);
	
	// Create the tile manager
	TileManager tileManager;
	///////////////////////////////////////////////////////////////
	public SaveLoad saveLoad = new SaveLoad(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //Sets screen size
        this.setBackground(Color.black); // Colors background
        this.setDoubleBuffered(true); // Use Jpanel's default double buffering
        this.addKeyListener(userInput); // Sets a keyListener to this method that takes from user Input
        this.setFocusable(true); // Allows for this class (GamePanel.java) to listen to they key inputs
    }
    
    // Starts the game clock (basically starts the game). Must be called by the main menu or any other way to load this level or any other levels.
    public void start(int levelNumber) {
        // Changes which map to load
        System.out.println("Level Number: " + levelNumber);
        
        // Initialize the tile manager
        tileManager = new TileManager(this, levelNumber);
 
        switch(levelNumber){
        	case 0:
        		System.out.println(levelNumber);
        		battle.dispose();
        		battle = new BattleScreen(1, flag);
        	    JOptionPane.showMessageDialog(null, "You have to find the evil commander and stop him!!!");
        		break;
        	case 1:
        		System.out.println(levelNumber);
        		battle.dispose();
        		battle = new BattleScreen(2, flag);
        		JOptionPane.showMessageDialog(null, "You've reached his lair, just a little further!");
        		break;
        	case 2:
        		System.out.println(levelNumber);
        		battle.dispose();
    			battle = new BattleScreen(3, flag);
    			JOptionPane.showMessageDialog(null, "The enemy commander is strong, but you grow stronger by the minute!!");
    			break;
        	case 3:
        		System.out.println(levelNumber);
        		battle.dispose();
    			battle = new BattleScreen(3, flag);
    			JOptionPane.showMessageDialog(null, "You dare challenge me? Let's see if you can reach me first.");
    			break;
        	case 4:
        		System.out.println(levelNumber);
        		battle.dispose();
    			battle = new BattleScreen(3, flag);
    			JOptionPane.showMessageDialog(null, "What?! How could I lose?!? NOOOOO!!!!");
    			break;

        }

        // Check if a game thread is already running
        if (gameThread == null || !gameThread.isAlive()) {
            // If not, create a new thread and start the game
            gameThread = new Thread(this);
            gameThread.start(); // Calls the run()
        }
        // If a thread is already running, do nothing (you can add else block for logging or handling the case)
    }

    
    /*
     * Runs the actual game, but start() should be called, not run().
     */
	@Override
	public void run() {
		// Variables for setting FPS and only updating on that FPS, and preventing infinite updates
		int nanoSecond = 1000000000;
		double drawInterval = nanoSecond/FPS; // 9 Zeroes. 1 nano-second. This is dividing 1Billion nanoseconds by 60 so that it limits the FPS.
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		// While the game is not stopped/exists
		while (gameThread != null) {
			// The clock for delta and the timer.
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			// When delta is greater or equal to 1, update everything and repaint the screen.
			if (delta >= 1) {
				update(); // Update all information that needs to be updated
				repaint(); // *NOTE* this calls "paintComponent()", it should be repaint() not paintComponent(). It's a default method for JFrame/JPanel.
				delta--;
				drawCount++;
			}
			
			// Reset the draw count and timer. And also print out the current FPS.
			if (timer >= nanoSecond) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
			if(battleCnt <= maxBattle) {
				if (battleTimer.isTimeUp() && !isBattleActive) {
					System.out.println("Turning on battle System");
					
	                isBattleActive = true;
	                battleTimer.pause();
	                battle.setVisible(true);
	                battleCnt ++;
	                
	            } else if (isBattleActive && battle.battleOver(false)) {
	                isBattleActive = false;
	                battle.setVisible(false);
	                //battle.resetEnemy();
	                battleTimer.resume();
	                battleTimer.reset();
	                
	            }
			}
			
			// Check if the player is in the vicinity of the winning tile
	        if (isInWinningTileVicinity(tileManager.winTileX, tileManager.winTileY)) {
	        	if (player.currentLevel < 3) {
			        System.out.println("Level Done. Moving on.");
			        JOptionPane.showMessageDialog(null, "You have to find the evil commander and stop him!!!");
		            player.currentLevel += 1;
		            player.resetPlayer();
			        start(player.currentLevel); // Recalls the game but makes it the next level
	        	}
	        	else if (player.currentLevel >= 3) { // When the player finishes then do this
	        		boolean bossBattle = true;
	        		battle.dispose();
	        		JOptionPane.showMessageDialog(null, "You have found the evil commander!!! \nThe world needs you to save it.\nYou have been healed\nBATTLE!!!");
	        		finalBattle();
	        		System.out.println("You beat the game!");
	                stopGame(); // Call method to stop the game thread
	        	}
	        	if(battle.isPlayerDied() == true) {
	        		 stopGame();
	        		 SwingUtilities.invokeLater(() -> {
	        		        new MainMenu();
	        		        battleCnt = maxBattle;
	   	        		 	setVisible(false);
	   	        		 	
	        		 });
	        		break;
	        		
	        	}
	        }
		}
	}
	
	// Makes the winning tile detection bigger
	private boolean isInWinningTileVicinity(int winTileX, int winTileY) {
        // Define a range around the winning tile's coordinates
        int vicinityRange = 30; // ** THIS NUMBER HAS TO BE 30 **

        // Check if the player's position is within the vicinity range of the winning tile
        return player.posX >= winTileX - vicinityRange && player.posX <= winTileX + vicinityRange &&
        		player.posY >= winTileY - vicinityRange && player.posY <= winTileY + vicinityRange;
    }
	
	private void stopGame() {
	    // Set gameThread to null to exit the game loop
	    gameThread = null;
	    
	    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
	    topFrame.dispose();

	}
	
	
	/*
	 * update() should be used for all things that are being updated. So like when the player
	 * presses a key or an enemy moves or something.
	 */
	public void update() {
		// Checks the player's movement
		// If NOT in a battle then update the player
		if(isBattleActive == false) {
			player.update(); // Player's movement is handled in the Player class in characters
		}
		else if (isBattleActive == true) { // If Battle activates then stop all movement from the player until it's over
			if (userInput.isPressingDown || userInput.isPressingUp || userInput.isPressingLeft || userInput.isPressingRight == true) {
				userInput.isPressingDown = false;
				userInput.isPressingUp = false;
				userInput.isPressingLeft = false;
				userInput.isPressingRight = false;
			}
			
			player.stopMovement();
		}
	}
	
	/*
	 * JPanel's default method for paining the screen
	 * All things being drawn to the screen should be in here.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g; // Changing to 2D graphics and for using 2D graphic functions
		
		tileManager.draw(g2); // Calls the draw method IN the tile manager. !!! THIS MUST BE DRAWN BEFORE THE PLAYER/NPCS ARE DRAWN !!!
		player.draw(g2); // Player is drawn in the Player class in the characters package. Needs g2 to be able to draw 2D things.
		
		g2.dispose(); // Get rid of unused graphics
	}
	public boolean finalBattle() {
		BattleScreen bossBattle = new BattleScreen(4, true);
		bossBattle.setBossS();
		bossBattle.setVisible(true);
		while (bossBattle.battleOver(false) == false) {
			return true;
		} 
		battle.setVisible(false);
		JOptionPane.showMessageDialog(null, "What?! How could I lose?!? NOOOOO!!!!\n you saved the world");
		return false;
		}
		
	}
	

