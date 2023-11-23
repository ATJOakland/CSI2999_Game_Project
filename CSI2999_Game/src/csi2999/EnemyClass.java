package csi2999;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EnemyClass {
	private String filePath;
	private List<Integer> integers;
	private int enemyHealth;
	private int enemyDefense;
	private int enemyAttack;
	private String type;

	public EnemyClass(int level, String type) {
		this.enemyAttack = enemyHealth + (level - 1) * 50;
		this.enemyDefense = enemyDefense + (level - 1) * 2;
		this.enemyHealth = enemyHealth + (level - 1) * 3;
		this.type = type;
		this.filePath = filePath;
		this.integers = new ArrayList<>();
	}

	public int getEnemyHealth() {
		return enemyHealth;
	}

	public void setEnemyHealth(int enemyHealth) {
		this.enemyHealth = enemyHealth;
	}

	public int getEnemyDefense() {
		return enemyDefense;
	}

	public void setEnemyDefense(int enemyDefense) {
		this.enemyDefense = enemyDefense;
	}

	public int getEnemyAttack() {
		return enemyAttack;
	}

	public void setEnemyAttack(int enemyAttack) {
		this.enemyAttack = enemyAttack;
	}

	public void readAndLoadIntegers() {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			boolean headerProcessed = false;

			while ((line = reader.readLine()) != null) {
				String[] variables = line.split(",");

				if (!headerProcessed) {
					headerProcessed = true;
				} else {
					for (String variable : variables) {
						if (variable.trim().startsWith(this.type)) {
							String integerPart = variable.trim().substring(4);
							int intValue = Integer.parseInt(integerPart);
							integers.add(intValue);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<Integer> getIntegers() {
		return integers;
	}

}
