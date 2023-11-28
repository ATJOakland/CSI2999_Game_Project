package characters;

import java.awt.Color;
import java.awt.Graphics2D;

import csi2999.LevelOne;
import csi2999.UserInput;

public class Player extends DefaultCharacter {
	// References to the first level and user input
	LevelOne levelOne;
	UserInput userInput;
	
	public Player (LevelOne levelOne, UserInput userInput) {
		this.levelOne = levelOne;
		this.userInput = userInput;
		
		SetDefaultValues(); //Sets the defaults
	}
	
	public void SetDefaultValues() {
		// These are defined in the DefaultCharacter class
		posX = 100;
		posY = 60;
		characterSpeed = 4;
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
		}
		else if(userInput.isPressingDown == true) {
			posY += characterSpeed;
		}
		else if(userInput.isPressingRight == true) {
			posX += characterSpeed;
		}
		else if(userInput.isPressingLeft == true) {
			posX -= characterSpeed;
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.red);
		
		g2.fillRect(posX, posY, levelOne.defaultTileSize, levelOne.defaultTileSize); // Test player character. defaultTileSize because that's the size of ALL tiles in the game.
	}
}
