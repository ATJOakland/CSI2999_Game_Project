package csi2999;
// line 58 in gamepanel.java
/* public SaveLoad saveLoad = new SaveLoad(this); /////////////
*/

//line 12 in user-input
////public boolean isPressingTab;

//line 41 in user-input
/* if (keyCode == KeyEvent.VK_TAB) {
			isPressingTab = true;
			JOptionPane.showMessageDialog(null, "Game has been saved");
		}
		//line 68 set to false
 */
//line 56 in player
/* else if(userInput.isPressingTab == true) {
			gamePanel.saveLoad.save();
		}
*/
//line 75 in main menu
/*
 //////
                gp.saveLoad.load();
                gp.start(player.currentLevel); /////////////
*/

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;



public class SaveLoad {
	GamePanel gp;
	//BattleScreen battle;
	
	public SaveLoad(GamePanel gp) {
		this.gp = gp;
	}
	
	public void save() {
		
		try {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
		
		PlayerStats ps = new PlayerStats();
		
		//ps.health = gp.player.currentHealth; //bug 1
		ps.level = gp.player.currentLevel; //bug 2
		
		//write the playerStats object
		oos.writeObject(ps);
		
		}
		catch(Exception e) {
			System.out.println("Save Exception!");
		}
	}
	
	public void load() {
		try {
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			
			//read the playerStats object
			PlayerStats ps = (PlayerStats)ois.readObject();
			
			//gp.player.currentHealth = ps.health; //bug 3
			gp.player.currentLevel = ps.level ; //bug 4
			
			
		}
		catch(Exception e) {
			System.out.println("Load Exception!");
		}
		
	}

}