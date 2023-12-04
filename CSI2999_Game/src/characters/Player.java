package characters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import csi2999.GamePanel;
import csi2999.UserInput;
import tiles.TileManager;

public class Player extends DefaultCharacter {
	// References to the first level and user input
	GamePanel gamePanel;
	UserInput userInput;
	
	public int currentLevel = 0; //////
	//public currentHealth = 200;
	
	public Player (GamePanel gamePanel, UserInput userInput) {
		this.gamePanel = gamePanel;
		this.userInput = userInput;

		solidArea = new Rectangle(4,14,12,6);
		
		SetDefaultValues(); //Sets the defaults
	}

	public void SetDefaultValues() {
		// These are defined in the DefaultCharacter class
		posX = 100;
		posY = 60;
		
		tempPosX = posX;
		tempPosY = posY;
		
		characterSpeed = 2;
	}

	public void getPlayerImage() {
		//read character image files
		try {
			up = ImageIO.read(new FileInputStream(new File("res/player/guy_idle_up.png")));
			down = ImageIO.read(new FileInputStream(new File("res/player/guy_down_idle.png")));
			left = ImageIO.read(new FileInputStream(new File("res/player/guy_idle_left.png")));
			right = ImageIO.read(new FileInputStream(new File("res/player/guy_idle_right.png")));
			up1 = ImageIO.read(new FileInputStream(new File("res/player/guy_up_1.png")));
			up2 = ImageIO.read(new FileInputStream(new File("res/player/guy_up_2.png")));
			down1 = ImageIO.read(new FileInputStream(new File("res/player/guy_down_1.png")));
			down2 = ImageIO.read(new FileInputStream(new File("res/player/guy_down_2.png")));
			left1 = ImageIO.read(new FileInputStream(new File("res/player/guy_left_1.png")));
			left2 = ImageIO.read(new FileInputStream(new File("res/player/guy_left_2.png")));
			right1 = ImageIO.read(new FileInputStream(new File("res/player/guy_right_1.png")));
			right2 = ImageIO.read(new FileInputStream(new File("res/player/guy_right_2.png")));
		} catch (IOException e) {
			//exception if files can't be read
			e.printStackTrace();
		}
	}
	
	public void resetPlayer() {
	    SetDefaultValues();
	    characterSpeed = 2;
	}
	
	public void update() {
		// Checks the player's movement
		/* Y increases going down.
		 * X increases to the right.
		 * So subtracting from Y makes the player go down. Increasing X makes them go right.
		 * Increasing Y makes them go up. Decreasing X makes them go left.
		 * */
		if(userInput.isPressingUp == true) {
			posY -= characterSpeed;
			tempPosY = posY;
		}
		else if(userInput.isPressingDown == true) {
			posY += characterSpeed;
			tempPosY = posY;
		}
		else if(userInput.isPressingRight == true) {
			posX += characterSpeed;
			tempPosX = posX;
		}
		else if(userInput.isPressingLeft == true) {
			posX -= characterSpeed;
			tempPosX = posX;
		}
		else if(userInput.isPressingShift == true) {
			gamePanel.saveLoad.save();
		}
	}
	
	public void stopMovement() {
		// Checks the player's movement
		/* Stops all movement completely.
		 * */
		posX = tempPosX;
		posY = tempPosY;
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.red);
		
		g2.fillRect(posX, posY, gamePanel.defaultTileSize, gamePanel.defaultTileSize); // Test player character. defaultTileSize because that's the size of ALL tiles in the game.
	}
}
