package csi2999;

import characters.DefaultCharacter;


public class ColCheck {
	
	GamePanel panel;
	
	public ColCheck(GamePanel panel) {
		this.panel = panel;
	}
	
	public void checkTile(DefaultCharacter character) {
		int charLeftWorldX = character.posX + character.solidArea.x;
		int charRightWorldX = character.posX + character.solidArea.x + character.solidArea.width;
		int charTopWorldY = character.posY + character.solidArea.y;
		int charBottomWorldY = character.posY + character.solidArea.y + character.solidArea.height;
		
		int charLeftCol = charLeftWorldX/panel.tileSize;
		int charRightCol = charRightWorldX/panel.tileSize;
		int charTopRow = charTopWorldY/panel.tileSize;
		int charBotRow = charBottomWorldY/panel.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(character.direction) {
		case "up":
			charTopRow = (charTopWorldY - character.characterSpeed)/panel.tileSize;
			tileNum1 = panel.tileManager.mapTileNumber[charLeftCol][charTopRow];
			tileNum2 = panel.tileManager.mapTileNumber[charRightCol][charTopRow];
			if(panel.tileManager.tile[tileNum1].collision == true || panel.tileManager.tile[tileNum2].collision == true) {
				character.colOn = true;
			}else if(character.posY == 1) {
				character.colOn = true;
			}
			break;
		case "down":
			charBotRow = (charBottomWorldY + character.characterSpeed)/panel.tileSize;
			tileNum1 = panel.tileManager.mapTileNumber[charLeftCol][charBotRow];
			tileNum2 = panel.tileManager.mapTileNumber[charRightCol][charBotRow];
			if(panel.tileManager.tile[tileNum1].collision == true || panel.tileManager.tile[tileNum2].collision == true) {
				character.colOn = true;
			}else if(character.posY == 16) {
				character.colOn = true;
			}
			break;
		case "left":
			charLeftCol = (charLeftWorldX - character.characterSpeed)/panel.tileSize;
			tileNum1 = panel.tileManager.mapTileNumber[charLeftCol][charTopRow];
			tileNum2 = panel.tileManager.mapTileNumber[charLeftCol][charBotRow];
			if(panel.tileManager.tile[tileNum1].collision == true || panel.tileManager.tile[tileNum2].collision == true) {
				character.colOn = true;
			}else if(character.posX == 1) {
				character.colOn = true;
			}
			break;
		case "right":
			charRightCol = (charRightWorldX + character.characterSpeed)/panel.tileSize;
			tileNum1 = panel.tileManager.mapTileNumber[charRightCol][charTopRow];
			tileNum2 = panel.tileManager.mapTileNumber[charRightCol][charBotRow];
			if(panel.tileManager.tile[tileNum1].collision == true || panel.tileManager.tile[tileNum2].collision == true) {
				character.colOn = true;
			}else if(character.posX == 16) {
				character.colOn = true;
			}
			break;
		}
	}
}
