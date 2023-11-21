package csi2999;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInput implements KeyListener{

	public boolean isPressingUp;
	public boolean isPressingDown;
	public boolean isPressingLeft;
	public boolean isPressingRight;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// THIS WILL NOT BE USED. BUT THE METHOD IS NEEDED FOR THE KEY LISTENER TO WORK.
	}
	
	// If Key is pressed/held
	@Override
	public void keyPressed(KeyEvent e) {
		// Gets the number of the key pressed
		int keyCode = e.getKeyCode();
		
		// If the number of the key pressed equals "W" or the Up Arrown then do something (aka, go up).
		if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
			isPressingUp = true;
		}
		
		if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
			isPressingDown = true;
		}
		
		if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
			isPressingLeft = true;
		}
		
		if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
			isPressingRight = true;
		}
	}
	
	// If key is not pressed/released
	@Override
	public void keyReleased(KeyEvent e) {
		// Gets the number of the key pressed
		int keyCode = e.getKeyCode();
		
		// If the number of the key pressed equals "W" then do something (aka, go up).
		if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
			isPressingUp = false;
		}
		
		if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
			isPressingDown = false;
		}
		
		if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
			isPressingLeft = false;
		}
		
		if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
			isPressingRight = false;
		}
	}
}
