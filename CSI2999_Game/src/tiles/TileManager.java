package tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import csi2999.LevelOne;

public class TileManager {
	
	LevelOne levelOne;
	Tile[]  tile;
	
	int mapTileNumber[][];
	
	public TileManager(LevelOne levelOne){
		this.levelOne = levelOne;
		
		tile = new Tile[10];
		
		mapTileNumber = new int[levelOne.maxScreenColumnTiles][levelOne.maxScreenRowTiles];
		
		getTileImage();
		loadMap("/maps/testMap.txt");
	}
	
	// Loads in the tile images
	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/background_tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/background_tiles/Grey Brick 16x16.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/background_tiles/Water 16x16.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/background_tiles/Dirt 16x16.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String mapFileName) {
		try {
			InputStream is = getClass().getResourceAsStream(mapFileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int column = 0;
			int row = 0;
			
			while(column < levelOne.maxScreenColumnTiles && row < levelOne.maxScreenRowTiles) {
				
				String line = br.readLine();
				
				while(column < levelOne.maxScreenColumnTiles) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[column]);
					
					mapTileNumber[column][row] = num;
					column++;
				}
				
				if(column == levelOne.maxScreenColumnTiles) {
					column = 0;
					row++;
				}
			}
			br.close();
				
		}catch(Exception e){
			
		}
	}
	
	public void draw(Graphics2D g2) {
		/*
		 *Variables for the columns, row, and x&y coordinates for where to draw a tile.
		 *Reminder that all tiles, npc, and the player is 16x16 pixels, so
		 *"x += * is moving by the tile size. The "tileSize" is the defaultTileSize (16) times the scale (currently 3).
		 *If you make the scale a bigger number then the tiles will be bigger, and smaller then smaller.
		*/
		int column = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		// Loops through the entire screen and places a tile.
		while(column < levelOne.maxScreenColumnTiles && row < levelOne.maxScreenRowTiles) {
			int tileNumber = mapTileNumber[column][row]; // Gets the number in the row/column of the map
			
			g2.drawImage(tile[tileNumber].image, x, y, levelOne.tileSize, levelOne.tileSize, null);
			column++;
			x += levelOne.tileSize;
			
			if(column == levelOne.maxScreenColumnTiles) {
				column = 0;
				x = 0;
				row++;
				y += levelOne.tileSize;
			}
			
		}
	}
}