package csi2999;
// line 55 in levelone.java
/* SaveLoad saveLoad = new SaveLoad(this); /////////////
*/

//line 12 in user-input
////public boolean isPressingTab;

//line 41 in user-input
/* if (keyCode == KeyEvent.VK_TAB) {
			isPressingTab = true;
		}
		//line 68 set to false
 */
//line 56 in player
/* else if(userInput.isPressingTab == true) {
			levelOne.saveLoad.save();
			JOptionPane.showMessageDialog(null, "Game has been saved");
		}
*/
//line 75 in main menu
/*
 //////
                LevelOne levelOne;
                levelOne.saveLoad.load();
*/

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;



public class SaveLoad {
	LevelOne levelOne;
	
	public SaveLoad(LevelOne levelOne) {
		this.levelOne = levelOne;
	}
	public void save() {
		
		try {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
		
		PlayerStats ps = new PlayerStats();
		
		ps.health = levelOne.player.currentHealth; //bug 1
		ps.level = levelOne.player.level; //bug 2
		
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
			
			levelOne.player.getCurrentHealth = ps.health; //bug 3
			levelOne.player.level = ps.level ; //bug 4
		}
		catch(Exception e) {
			System.out.println("Load Exception!");
		}
		
	}
}