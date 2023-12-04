package csi2999;

import characters.DefaultCharacter;

public class ColCheck {
	
	GamePanel panel;
	
	public ColCheck(GamePanel panel) {
		this.panel = panel;
	}
	
	public void checkTileOne(DefaultCharacter character) {
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
			tileNum1 = panel.tileM.mapTileNum[charLeftCol][charTopRow];
			tileNum2 = panel.tileM.mapTileNum[charRightCol][charTopRow];
			if(panel.tileM.tile[tileNum1].collision == true || panel.tileM.tile[tileNum2].collision == true) {
				character.colOn = true;
			}
			break;
		case "down":
			charBotRow = (charBottomWorldY + character.characterSpeed)/panel.tileSize;
			tileNum1 = panel.tileM.mapTileNum[charLeftCol][charBotRow];
			tileNum2 = panel.tileM.mapTileNum[charRightCol][charBotRow];
			if(panel.tileM.tile[tileNum1].collision == true || panel.tileM.tile[tileNum2].collision == true) {
				character.colOn = true;
			}
			break;
		case "left":
			charLeftCol = (charLeftWorldX - character.characterSpeed)/panel.tileSize;
			tileNum1 = panel.tileM.mapTileNum[charLeftCol][charTopRow];
			tileNum2 = panel.tileM.mapTileNum[charLeftCol][charBotRow];
			if(panel.tileM.tile[tileNum1].collision == true || panel.tileM.tile[tileNum2].collision == true) {
				character.colOn = true;
			}else if(character.posX == 1) {
				character.colOn = true;
			}
			break;
		case "right":
			charRightCol = (charRightWorldX + character.characterSpeed)/panel.tileSize;
			tileNum1 = panel.tileM.mapTileNum[charRightCol][charTopRow];
			tileNum2 = panel.tileM.mapTileNum[charRightCol][charBotRow];
			if(panel.tileM.tile[tileNum1].collision == true || panel.tileM.tile[tileNum2].collision == true) {
				character.colOn = true;
			}
			break;
		}
	}

	public void checkTileTwo(DefaultCharacter character) {
		int charLeftWorldX = character.posX + character.solidArea.x;
		int charRightWorldX = character.posX + character.solidArea.x + character.solidArea.width;
		int charTopWorldY = character.posY + character.solidArea.y;
		int charBottomWorldY = character.posY + character.solidArea.y + character.solidArea.height;
		
		int charLeftCol = charLeftWorldX/panel2.tileSize;
		int charRightCol = charRightWorldX/panel2.tileSize;
		int charTopRow = charTopWorldY/panel2.tileSize;
		int charBotRow = charBottomWorldY/panel2.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(character.direction) {
		case "up":
			charTopRow = (charTopWorldY - character.characterSpeed)/panel2.tileSize;
			tileNum1 = panel2.tileM.mapTileNum[charLeftCol][charTopRow];
			tileNum2 = panel2.tileM.mapTileNum[charRightCol][charTopRow];
			if(panel2.tileM.tile[tileNum1].collision == true || panel2.tileM.tile[tileNum2].collision == true) {
				character.colOn = true;
			}
			break;
		case "down":
			charBotRow = (charBottomWorldY + character.characterSpeed)/panel2.tileSize;
			tileNum1 = panel2.tileM.mapTileNum[charLeftCol][charBotRow];
			tileNum2 = panel2.tileM.mapTileNum[charRightCol][charBotRow];
			if(panel2.tileM.tile[tileNum1].collision == true || panel2.tileM.tile[tileNum2].collision == true) {
				character.colOn = true;
			}
			break;
		case "left":
			charLeftCol = (charLeftWorldX - character.characterSpeed)/panel2.tileSize;
			tileNum1 = panel2.tileM.mapTileNum[charLeftCol][charTopRow];
			tileNum2 = panel2.tileM.mapTileNum[charLeftCol][charBotRow];
			if(panel2.tileM.tile[tileNum1].collision == true || panel2.tileM.tile[tileNum2].collision == true) {
				character.colOn = true;
			}else if(character.posX == 1) {
				character.colOn = true;
			}
			break;
		case "right":
			charRightCol = (charRightWorldX + character.characterSpeed)/panel2.tileSize;
			tileNum1 = panel2.tileM.mapTileNum[charRightCol][charTopRow];
			tileNum2 = panel2.tileM.mapTileNum[charRightCol][charBotRow];
			if(panel2.tileM.tile[tileNum1].collision == true || panel2.tileM.tile[tileNum2].collision == true) {
				character.colOn = true;
			}
			break;
		}
	}

	public void checkTileThree(DefaultCharacter character) {
		int charLeftWorldX = character.posX + character.solidArea.x;
		int charRightWorldX = character.posX + character.solidArea.x + character.solidArea.width;
		int charTopWorldY = character.posY + character.solidArea.y;
		int charBottomWorldY = character.posY + character.solidArea.y + character.solidArea.height;
		
		int charLeftCol = charLeftWorldX/panel3.tileSize;
		int charRightCol = charRightWorldX/panel3.tileSize;
		int charTopRow = charTopWorldY/panel3.tileSize;
		int charBotRow = charBottomWorldY/panel3.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(character.direction) {
		case "up":
			charTopRow = (charTopWorldY - character.characterSpeed)/panel3.tileSize;
			tileNum1 = panel3.tileM.mapTileNum[charLeftCol][charTopRow];
			tileNum2 = panel3.tileM.mapTileNum[charRightCol][charTopRow];
			if(panel3.tileM.tile[tileNum1].collision == true || panel3.tileM.tile[tileNum2].collision == true) {
				character.colOn = true;
			}
			break;
		case "down":
			charBotRow = (charBottomWorldY + character.characterSpeed)/panel3.tileSize;
			tileNum1 = panel3.tileM.mapTileNum[charLeftCol][charBotRow];
			tileNum2 = panel3.tileM.mapTileNum[charRightCol][charBotRow];
			if(panel3.tileM.tile[tileNum1].collision == true || panel3.tileM.tile[tileNum2].collision == true) {
				character.colOn = true;
			}
			break;
		case "left":
			charLeftCol = (charLeftWorldX - character.characterSpeed)/panel3.tileSize;
			tileNum1 = panel3.tileM.mapTileNum[charLeftCol][charTopRow];
			tileNum2 = panel3.tileM.mapTileNum[charLeftCol][charBotRow];
			if(panel3.tileM.tile[tileNum1].collision == true || panel3.tileM.tile[tileNum2].collision == true) {
				character.colOn = true;
			}else if(character.posX == 1) {
				character.colOn = true;
			}
			break;
		case "right":
			charRightCol = (charRightWorldX + character.characterSpeed)/panel3.tileSize;
			tileNum1 = panel3.tileM.mapTileNum[charRightCol][charTopRow];
			tileNum2 = panel3.tileM.mapTileNum[charRightCol][charBotRow];
			if(panel3.tileM.tile[tileNum1].collision == true || panel3.tileM.tile[tileNum2].collision == true) {
				character.colOn = true;
			}
			break;
		}
	}

	public void checkTileFinal(DefaultCharacter character) {
		int charLeftWorldX = character.posX + character.solidArea.x;
		int charRightWorldX = character.posX + character.solidArea.x + character.solidArea.width;
		int charTopWorldY = character.posY + character.solidArea.y;
		int charBottomWorldY = character.posY + character.solidArea.y + character.solidArea.height;
		
		int charLeftCol = charLeftWorldX/panel4.tileSize;
		int charRightCol = charRightWorldX/panel4.tileSize;
		int charTopRow = charTopWorldY/panel4.tileSize;
		int charBotRow = charBottomWorldY/panel4.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(character.direction) {
		case "up":
			charTopRow = (charTopWorldY - character.characterSpeed)/panel4.tileSize;
			tileNum1 = panel4.tileM.mapTileNum[charLeftCol][charTopRow];
			tileNum2 = panel4.tileM.mapTileNum[charRightCol][charTopRow];
			if(panel4.tileM.tile[tileNum1].collision == true || panel4.tileM.tile[tileNum2].collision == true) {
				character.colOn = true;
			}
			break;
		case "down":
			charBotRow = (charBottomWorldY + character.characterSpeed)/panel4.tileSize;
			tileNum1 = panel4.tileM.mapTileNum[charLeftCol][charBotRow];
			tileNum2 = panel4.tileM.mapTileNum[charRightCol][charBotRow];
			if(panel4.tileM.tile[tileNum1].collision == true || panel4.tileM.tile[tileNum2].collision == true) {
				character.colOn = true;
			}
			break;
		case "left":
			charLeftCol = (charLeftWorldX - character.characterSpeed)/panel4.tileSize;
			tileNum1 = panel4.tileM.mapTileNum[charLeftCol][charTopRow];
			tileNum2 = panel4.tileM.mapTileNum[charLeftCol][charBotRow];
			if(panel4.tileM.tile[tileNum1].collision == true || panel4.tileM.tile[tileNum2].collision == true) {
				character.colOn = true;
			}else if(character.posX == 1) {
				character.colOn = true;
			}
			break;
		case "right":
			charRightCol = (charRightWorldX + character.characterSpeed)/panel4.tileSize;
			tileNum1 = panel4.tileM.mapTileNum[charRightCol][charTopRow];
			tileNum2 = panel4.tileM.mapTileNum[charRightCol][charBotRow];
			if(panel4.tileM.tile[tileNum1].collision == true || panel4.tileM.tile[tileNum2].collision == true) {
				character.colOn = true;
			}
			break;
		}
	}			
}
