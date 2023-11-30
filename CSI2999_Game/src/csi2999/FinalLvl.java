package csi2999;
	import javax.swing.SwingUtilities;
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.Graphics;
	import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


	import characters.Player;
	import tiles.TileManager;

	public class FinalLvl extends JPanel implements Runnable{
		// Creates window
		public final int defaultTileSize = 16; // ALL tiles are 16x16 size. This is the standard tile size for most games
		final int scale = 3; //For scaling up the tiles
		public final int tileSize = defaultTileSize * scale; // Scales up or down the tile size. 48x48
		
		//Putting tiles on screen
		public final int maxScreenColumn = 16;
		public final int maxScreenRow = 12;
		public final int screenWidth = tileSize * maxScreenColumn; // 768 Pixels
		public final int screenHeight = tileSize * maxScreenRow; // 576 Pixels 
		

		
		// THE GAME'S CLOCK (for making the game 60FPS can continuously calling actions
		Thread gameThread;
		// FPS
		final int FPS = 60;
		// Initializes the User Input script
		UserInput userInput = new UserInput();
		// Gets a reference to the player in the characters package
		public Player player = new Player(this, userInput);
		//Reference to NPC in characters package
		//Sound music = new Sound(); 
		//Sound se = new Sound(); 
		
		// Initialize the tile manager
		TileManager tileManager = new TileManager(this);
		

	    public FinalLvl() {
	        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //Sets screen size
	        this.setBackground(Color.black); // Colors background
	        this.setDoubleBuffered(true); // Use Jpanel's default double buffering
	        this.addKeyListener(userInput); // Sets a keyListener to this method that takes from user Input
	        this.setFocusable(true); // Allows for this class (LevelOne.java) to listen to they key inputs
	    }
	    
	    // Starts the game clock (basically starts the game). Must be called by the main menu or any other way to load this level or any other levels.
	    public void start() {
	    	gameThread = new Thread(this);
	    	gameThread.start(); // Calls the run()
	    }

	    
	    /*
	     * Runs the actual game, but start() should be called, not run().
	     */
		@Override
		public void run() {
			// Variables for setting FPS and only updating on that FPS, and preventing infinite updates
			double drawInterval = 1000000000/FPS; // 9 Zeroes. 1 nano-second. This is dividing 1Billion nanoseconds by 60 so that it limits the FPS.
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
				if (timer >= 1000000000) {
					System.out.println("FPS: " + drawCount);
					drawCount = 0;
					timer = 0;
				}
			}
		}
		
		
		/*
		 * update() should be used for all things that are being updated. So like when the player
		 * presses a key or an enemy moves or something.
		 */
		public void update() {
				//Checks the player's movement
				player.update(); // Player's movement is handled in the Player class in characters  
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
		
		public class ReadEnding {
		    public static void main(String[] args) {
		        // Finds the ending lines of Level 1
		        String filePath = "C:/Users/typhoon/Desktop/FinalLvlTxt.txt";

		        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
		            String line;

		            // Reads every line until the end of the file
		            while ((line = br.readLine()) != null) {
		                System.out.println(line);
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
	
	}