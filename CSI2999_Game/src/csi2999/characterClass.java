package csi2999;

import java.io.*;


public class characterClass {
	private int playerMaxHealth;
	private int playerAttack;
	private int playerFireDmg;
	private int playerDefense;
	private int iceDmg;
	private int level;
	private int currentHealth;
	
	private int thunderDmg;
	private final String filePath;

	public characterClass(int level) {
		this.filePath = "Health.dat";
		this.level = level;
		this.playerMaxHealth = 200 + (level - 1) * 50; // Base health plus extra per level
		this.playerAttack = 5 + (level - 1) * 3; // Default value, can be modified later
		this.playerFireDmg = 6 + (level - 1) * 2; // Default value, can be modified later
		this.playerDefense = 3 + (level - 1) * 4; // Default value, can be modified later
		this.iceDmg = 3 + (level - 1) * 3; // Default value, can be modified later
		this.currentHealth = readCurrentHealthFromFile();
		this.thunderDmg = 4 + (level - 1) * 3;
	}

	public int getthunderDmg() {
		return thunderDmg;
	}

	public void setthunderDmg(int thunderDmg) {
		this.thunderDmg = thunderDmg;
	}

	public int getPlayerHealth() {
		return playerMaxHealth;
	}

	public void setPlayerMaxHealth(int health) {
		this.playerMaxHealth = health;
	}

	public int getPlayerAttack() {
		return playerAttack;
	}

	public void setPlayerAttack(int playerAttack) {
		this.playerAttack = playerAttack;
	}

	public int getPlayerFireDmg() {
		return playerFireDmg;
	}

	public void setPlayerFireDmg(int playerFireDmg) {
		this.playerFireDmg = playerFireDmg;
	}

	public int getPlayerDefense() {
		return playerDefense;
	}

	public void setPlayerDefense(int playerDefense) {
		this.playerDefense = playerDefense;
	}

	public int getIceDmg() {
		return iceDmg;
	}

	public void setIceDmg(int iceDmg) {
		this.iceDmg = iceDmg;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	public int heal (int healAmount) {
		int difference;
		difference = this.playerMaxHealth - this.currentHealth  ;
		if(difference < healAmount ) {
			this.currentHealth = this.currentHealth + difference;
			if(this.currentHealth > this.playerMaxHealth) {
				this.currentHealth = this.playerMaxHealth;
			}
			return difference;
		}
		this.currentHealth = this.currentHealth + healAmount;
		return healAmount;
	}
	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	

	public int readCurrentHealthFromFile() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(this.filePath)))) {
            // Load health data
            return (int) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Load Exception! " + e.getMessage());
            return this.playerMaxHealth; // Default to max health if loading fails
        }
 
	}
	public void saveToCsvFile(int data) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(this.filePath)))) {
            // Save current health
            oos.writeObject(this.currentHealth);
        } catch (IOException e) {
            System.out.println("Save Exception! " + e.getMessage());
        }
    }
	   
	@Override
	public String toString() {
		return "characterClass{" + "playerHealth=" + playerMaxHealth + ", playerAttack=" + playerAttack
				+ ", playerFireDmg=" + playerFireDmg + ", playerDefense=" + playerDefense + ", iceDmg=" + iceDmg
				+ ", level=" + level + '}';
	}
}
