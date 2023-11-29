package swingPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import tile.TileManager;
import javax.swing.JPanel;
import character.Player;

public class LevelTwo extends JPanel implements Runnable {
	public final int maxScreenCol = 48;
	public final int maxScreenRow = 48;
	public final int originalTileSize = 16;
	final int scale = 1;
	public final int tileSize = originalTileSize * scale;
	final int screenWidth = tileSize * maxScreenCol;
	final int screenHeight = tileSize * maxScreenRow;
	
	int fps = 60;
	UserInput u = new UserInput();
	Player player = new Player(this, u);
	TileManager tileM = new TileManager(this);
	public ColCheck c = new ColCheck(this);
	Thread swingThread;
	public LevelTwo() {
		
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(u);
		this.setFocusable(true);

	}
	
	public void startSwingThread() {
		//start game thread
		swingThread = new Thread(this);
		swingThread.start();
	}
	
	public void run() {
		//run the game thread
		double drawInterval = 1000000000/fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		
		while(swingThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000 ) {
				System.out.println("FPS: "+drawCount);
				drawCount = 0;
				timer = 0;
			}
		}				
	}
	
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		player.draw(g2);
		g2.dispose();
	}

}
