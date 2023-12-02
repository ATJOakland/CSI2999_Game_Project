package csi2999;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EnemyClass {
	private final String filePath = "csi2999/Enemy.txt";;

	private int enemyHealth;
	private int enemyDefense;
	private int enemyAttack;
	private int maxHealth;
	//private int weakness;
	private static final Map<String, int[]> enemyStatsCache = new HashMap<>();

	public EnemyClass(int level, String type) {
		// Load the stats from the fi
		int[] stats = getEnemyStats(type);
		this.enemyHealth = stats[1] + (level - 1) * 10;
        this.enemyAttack = stats[0] + (level - 1) * 2;
        this.enemyDefense = stats[2] + (level - 1) * 2;
        this.maxHealth = stats[1] + (level - 1) * 10;
       // this.weakness = stats[3];
       
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
	
	
	//public int getWeakness() {
	//	return weakness;
	//}
	public int getMaxHealth() {
		return maxHealth;
	}
	public int[] loadEnemyStats(String enemyType) {
	    int[] stats = new int[4]; // To hold health, attack, and defense

	    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(this.filePath);
	    if (inputStream == null) {
	        System.err.println("File not found: " + this.filePath);
	        return stats; // Return default stats or consider throwing an exception
	    }

	    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            // Assuming the file format is: Type,Health,Attack,Defense
	            String[] parts = line.split(",");
	            if (parts.length == 4 && parts[0].trim().equalsIgnoreCase(enemyType)) {
	                stats[0] = Integer.parseInt(parts[2].trim()); // Attack
	                stats[1] = Integer.parseInt(parts[1].trim()); // Health
	                stats[2] = Integer.parseInt(parts[3].trim()); // Defense
	                stats[3] = Integer.parseInt(parts[3].trim()); // Element weakness
	                break;
	            }
	        }
	    } catch (IOException | NumberFormatException e) {
	        e.printStackTrace();
	    }

	    return stats;
	}
	private int[] getEnemyStats(String enemyType) {
        // Check if stats are already loaded
        if (!enemyStatsCache.containsKey(enemyType)) {
            // Load stats and cache them
            int[] stats = loadEnemyStats(enemyType);
            enemyStatsCache.put(enemyType, stats);
        }
        return enemyStatsCache.get(enemyType);
    }

}
