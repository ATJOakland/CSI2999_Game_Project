package characters;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class DefaultCharacter {
	// Default variables for ALL types of characters (player and enemies and even NPCs)
	public int posX, posY, tempPosX, tempPosY;
	public int characterSpeed;
	public boolean isInBattle = false; // Checks if in Battle or not.
	public BufferedImage up, down, left, right, up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction = "down";
	public Rectangle solidArea;
	public boolean colOn = false;
	public int spriteCounter = 0;
	public int spriteNum = 0;
}
