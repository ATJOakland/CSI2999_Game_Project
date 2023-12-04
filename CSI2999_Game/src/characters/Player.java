package character;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import swingPackage.LevelTwo;
import swingPackage.UserInput;

public class Player extends DefaultCharacter {
	// References to the first level and user input
	GamePanel gamePanel;
	UserInput userInput;
	
	public Player (GamePanel gamePanel, UserInput userInput) {
		this.GamePanel = GamePanel;
		this.userInput = userInput;
		
		solidArea = new Rectangle(4,14,12,6);
		
		SetDefaultValues(); //Sets the defaults
		getPlayerImage();
	}
	
	public void SetDefaultValues() {
		// These are defined in the DefaultCharacter class
		posX = 100;
		posY = 100;
		characterSpeed = 3;
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
	
	public void update() {
		// Checks the player's movement
		/* Y increases going down.
		 * X increases to the right.
		 * So subtracting from Y makes the player go down. Increasing X makes them go right.
		 * Increasing Y makes them go up. Decreasing X makes them go left.
		 * */
		if(userInput.isPressingUp == true || userInput.isPressingDown == true || 
				userInput.isPressingLeft == true || userInput.isPressingRight == true) {
			
			if(userInput.isPressingUp == true) {			
				direction = "up";
			}
			else if(userInput.isPressingDown == true) {			
				direction = "down";
			}
			else if(userInput.isPressingRight == true) {			
				direction = "right";
			}
			else if(userInput.isPressingLeft == true) {			
				direction = "left";
			}
		
			colOn = false;
			gamePanel.c.checkTile(this);
			//if no collision, move character
			if(colOn == false) {
				switch(direction) {
				case "up":
					posY -= characterSpeed;
					break;
				case "down":
					posY += characterSpeed;
					break;
				case "left":
					posX -= characterSpeed;
					break;
				case "right":
					posX += characterSpeed;
					break;
				}
			}
			//display character images while moving/idle
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum == 0) {
					spriteNum = 1;
				}else if(spriteNum == 1) {
					spriteNum = 2;
				}else if(spriteNum == 2) {
					spriteNum = 1;
				}										
					spriteCounter = 0;
				}					
		}else spriteNum = 0;	
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		//draw images base on direction
		switch(direction) {
		case "up":
			if(spriteNum == 0) {
				image = up;
			}
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			
			break;
		case "down":
			if(spriteNum == 0) {
				image = down;
			}
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 0) {
				image = left;
			}
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 0) {
				image = right;
			}
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, posX, posY, levelTwo.tileSize + 8, levelTwo.tileSize + 8, null);
	}
}
