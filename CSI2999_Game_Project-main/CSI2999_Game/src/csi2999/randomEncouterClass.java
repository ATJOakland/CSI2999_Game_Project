package csi2999;

import java.security.SecureRandom;
public class randomEncouterClass {
	// type of monster
	private String[] monsterClass = { "Pirate", "Orc", "Goblin"};
	private SecureRandom rand = new SecureRandom();
	private SecureRandom rand2 = new SecureRandom();
	
	public String generateMonster() {
		// create object and random monster
		int monster = rand.nextInt(monsterClass.length);
		return monsterClass[monster];
	}
	
	public boolean setBattle() {
		return rand2.nextInt(0,1000) == 2;
		
	}
}
