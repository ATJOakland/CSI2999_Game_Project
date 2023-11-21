package csi2999;

public class chacterClass {
	private int health;
	private int strength;
	private int totalHP;
	
	public chacterClass() {
		this.health = 100;
		this.strength = 4;
		this.totalHP = 101;
	}
	
	
	private void readFile() {
		
	}
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getTotalHP() {
		return totalHP;
	}

	public void setTotalHP(int totalHP) {
		this.totalHP = totalHP;
	}
	
	
}
