package tile;

import swingPackage.SwingClass;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class TileManager {
	
	SwingClass sp;
	Tile[] tile;
	int mapTileNum[][];
	
	public TileManager(SwingClass sp) {
		
		this.sp = sp;
		tile = new Tile[10];
		mapTileNum = new int[sp.maxScreenCol][sp.maxScreenRow];
		getTileImage();
		loadMap();
	}
	
	public void getTileImage() {
		
		try {
			 File file = new File("res/tiles/grass.png");
	            FileInputStream fis = new FileInputStream(file);
	            tile[0] = new Tile();
	            tile[0].image = ImageIO.read(fis);

	            file = new File("res/tiles/wall.png");
	            fis = new FileInputStream(file);
	            tile[1] = new Tile();
	            tile[1].image = ImageIO.read(fis);

	            file = new File("res/tiles/water.png");
	            fis = new FileInputStream(file);
	            tile[2] = new Tile();
	            tile[2].image = ImageIO.read(fis);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadMap() {
		try {
			InputStream is = getClass().getResourceAsStream("/maps/map1.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < sp.maxScreenCol && row < sp.maxScreenRow) {
				
				String line = br.readLine();
				
				while(col < sp.maxScreenCol) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				
				if(col == sp.maxScreenCol) {
					col = 0;
					row++;
				}
			}
			br.close();
				
		}catch(Exception e){
			
		}
	}
	
	public void draw(Graphics2D g2) {
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < sp.maxScreenCol && row < sp.maxScreenRow) {
			int tileNum = mapTileNum[col][row];
			g2.drawImage(tile[tileNum].image,x,y,sp.tileSize,sp.tileSize,null);
			col++;
			x += sp.tileSize;
			if(col == sp.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y+= sp.tileSize;
			}
		}
		//g2.drawImage(tile[0].image,0,0,sp.tileSize,sp.tileSize,null);
		//g2.drawImage(tile[1].image,48,0,sp.tileSize,sp.tileSize,null);
		//g2.drawImage(tile[2].image,96,0,sp.tileSize,sp.tileSize,null);
	}

}
