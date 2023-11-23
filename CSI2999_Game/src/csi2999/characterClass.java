package csi2999;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class characterClass {
	private int playerMaxHealth;
	private int playerAttack;
	private int playerFireDmg;
	private int playerDefense;
	private int iceDmg;
	private int level;
	private int currentHealth;
	private int wtrDmg;
	private final String filePath;

	public characterClass(int level) {
		filePath = "Health.txt";
		this.level = level;
		this.playerMaxHealth = 200 + (level - 1) * 50; // Base health plus extra per level
		this.playerAttack = 5 + (level - 1) * 3; // Default value, can be modified later
		this.playerFireDmg = 6 + (level - 1) * 2; // Default value, can be modified later
		this.playerDefense = 3 + (level - 1) * 4; // Default value, can be modified later
		this.iceDmg = 3 + (level - 1) * 3; // Default value, can be modified later
		this.currentHealth = readCurrentHealthFromFile(filePath);
		this.wtrDmg = 4 + (level - 1) * 3; //
	}

	public int getWtrDmg() {
		return wtrDmg;
	}

	public void setWtrDmg(int wtrDmg) {
		this.wtrDmg = wtrDmg;
	}

	public int getPlayerHealth() {
		return playerMaxHealth;
	}

	public void setPlayerHealth(int health) {
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

	public int readCurrentHealthFromFile(String filePath) {
		int readHealth = this.playerMaxHealth; // Default value

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line = reader.readLine();
			if (line != null) {
				readHealth = Integer.parseInt(line.trim());
			} else {
				System.out.println("File is empty. Defaulting current health.");
			}
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Invalid number format in file: " + e.getMessage());
		}

		return readHealth; // Return the read or default health value
	}
	

	@Override
	public String toString() {
		return "characterClass{" + "playerHealth=" + playerMaxHealth + ", playerAttack=" + playerAttack
				+ ", playerFireDmg=" + playerFireDmg + ", playerDefense=" + playerDefense + ", iceDmg=" + iceDmg
				+ ", level=" + level + '}';
	}
}
