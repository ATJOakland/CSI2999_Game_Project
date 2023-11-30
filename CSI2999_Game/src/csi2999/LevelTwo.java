package csi2999;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import characters.Player;
import tiles.TileManager;


public class LevelTwo extends JPanel implements Runnable {
	// Creates window
	public final int defaultTileSize = 16; // ALL tiles are 16x16 size. This is the standard tile size for most games
	final int scale = 3; //For scaling up the tiles
	public final int tileSize = defaultTileSize * scale; // Scales up or down the tile size.
	
	// setting a count and creating objects for the battle system.
	private int battleCnt = 0;
	private final int maxBattle = 4;
	private boolean isBattleActive = false;
	private RandomTimer battleTimer = new RandomTimer(5000, 9000);
	private BattleScreen battle = new BattleScreen(1);
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
	
	// Initialize the tile manager
	TileManager tileManager = new TileManager(LevelTwo);

	public LevelTwo() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(userInput);
        this.setFocusable(true);

	}
	
    public void start() {
    	gameThread = new Thread(this);
    	gameThread.start(); // Calls the run()
    }
	
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
					System.out.println("turning on battle System");
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
		}
	}
	
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
	
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileManager.draw(g2);
		player.draw(g2);
		
		g2.dispose();
	}

}
