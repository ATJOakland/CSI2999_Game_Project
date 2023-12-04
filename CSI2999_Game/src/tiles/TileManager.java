package tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import csi2999.GamePanel;

public class TileManager {
	GamePanel gamePanel;
	public Tile[]  tile;
	
	public int mapTileNumber[][];

    public int winTileX ;
    public int winTileY;
	
	// For start() in the game panel. 0 loads map 1, 1 loads map 2, 2 loads 3, 3 loads final map.
    public TileManager(GamePanel gamePanel, int mapNumber){
	    System.out.println("Map Number in TileManager: " + mapNumber);
		this.gamePanel = gamePanel;
		
		tile = new Tile[10];
		
		mapTileNumber = new int[gamePanel.maxScreenColumnTiles][gamePanel.maxScreenRowTiles];
		
		// Map Dictionary
		Map<String, String> mapPaths = new HashMap<String, String>() {{
            put("levelOne", "/maps/levelOneMap.txt");
            put("levelTwo", "/maps/levelTwoMap.txt");
            put("levelThree", "/maps/levelThreeMap.txt");
            put("levelFour", "/maps/finallvlmap.txt");
        }};

        getTileImage();
        
        // Switch of which map to load
        switch (mapNumber) {
        	case 0:
                loadMap(mapPaths.get("levelOne"));
                break;
        	case 1:
                loadMap(mapPaths.get("levelTwo"));
                break;
        	case 2:
                loadMap(mapPaths.get("levelThree"));
                break;
        	case 3:
                loadMap(mapPaths.get("levelFour"));
                break;
            default:	// Load level 1 by default if something else chosen
                loadMap(mapPaths.get("levelOne"));
                break;
        }
	}
	
	// Loads in the tile images
	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(new FileInputStream(new File("CSI2999_Game/res/background_tiles/grass.png")));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(new FileInputStream(new File("CSI2999_Game/res/background_tiles/Grey Brick 16x16.png")));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(new FileInputStream(new File("CSI2999_Game/res/background_tiles/Water 16x16.png")));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(new FileInputStream(new File("CSI2999_Game/res/background_tiles/Dirt 16x16.png")));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(new FileInputStream(new File("CSI2999_Game/res/background_tiles/Red Brick 16x16.png")));
			tile[4].collision = false;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(new FileInputStream(new File("CSI2999_Game/res/background_tiles/wall.png")));
			tile[5].collision = false;
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
			
			while(column < gamePanel.maxScreenColumnTiles && row < gamePanel.maxScreenRowTiles) {
				
				String line = br.readLine();
				
				while(column < gamePanel.maxScreenColumnTiles) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[column]);
					
					mapTileNumber[column][row] = num;
					column++;
				}
				
				if(column == gamePanel.maxScreenColumnTiles) {
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
		while(column < gamePanel.maxScreenColumnTiles && row < gamePanel.maxScreenRowTiles) {
			int tileNumber = mapTileNumber[column][row]; // Gets the number in the row/column of the map
			
			g2.drawImage(tile[tileNumber].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
			column++;
			x += gamePanel.tileSize;
			
			// Check if the current tile is the winning tile (value 5)
	        if (tileNumber == 5) {
	            // Store the coordinates of the winning tile
	            winTileX = x;
	            winTileY = y;
	            // Now you have the position of the winning tile (5)
	        }
			
			if(column == gamePanel.maxScreenColumnTiles) {
				column = 0;
				x = 0;
				row++;
				y += gamePanel.tileSize;
			}
			
		}
	}
}