package csi2999;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;;

public class EnemyClass {
	private String filePath;

	private int enemyHealth;
	private int enemyDefense;
	private int enemyAttack;


	public EnemyClass(int level, String type) {
		// Load the stats from the file
		this.filePath = "csi2999/Enemy.txt";
        int[] stats = loadEnemyStats(type);
        
		this.enemyAttack = stats[0] + (level - 1) * 50;
		this.enemyDefense = stats[2] + (level - 1) * 2;
		this.enemyHealth = stats[1] + (level - 1) * 3;
	
	}

	public int getEnemyHealth() {
		return this.enemyHealth;
	}

	public int getEnemyDefense() {
		return this.enemyDefense;
	}

	public int getEnemyAttack() {
		return this.enemyAttack;
	}

	public int[] loadEnemyStats(String enemyType) {
        int[] stats = new int[3]; // To hold health, attack, and defense

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(this.filePath);
        if (inputStream == null) {
            System.err.println("File not found: " + this.filePath);
            return stats; // Return default stats or consider throwing an exception
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }


        return stats;
    }
	

}
