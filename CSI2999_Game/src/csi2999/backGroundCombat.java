package csi2999;

public class backGroundCombat {
	// this is will be called in based on enemies
	private int enemyHealth;
	private int enemyAttack;
	private int enemyDefense;
	// this is be called in from char sheet
	private int playerHealth;
	private int playerAttack;
	private int specDiceRoll;
	private int playerDefense;
	private int diceRoll, emyDiceRoll;
	private int damage;
	private int enemyDamage;
	private int iceDmg;
	private int fireDmg;
	private int thunderDmg;
	private int maxEmyHealth;
	private randomEncouterClass monster = new randomEncouterClass();
	private characterClass player;
	private EnemyClass enemy;
	String monsterName;

	// creating my dice object
	private randomNumberClass twentySideDice = new randomNumberClass();
	public backGroundCombat(int level) {

		// left empty since this is the outline for combat.
		this.monsterName = monster.generateMonster();
		enemy = new EnemyClass(level, this.monsterName);
		player = new characterClass(level);
		this.playerHealth = player.getCurrentHealth();
		this.playerAttack = player.getPlayerAttack();
		this.playerDefense = player.getPlayerDefense();
		this.enemyHealth = enemy.getEnemyHealth();
		this.enemyAttack = enemy.getEnemyAttack();
		this.enemyDefense = enemy.getEnemyDefense();
		this.iceDmg = player.getIceDmg();
		this.fireDmg = player.getPlayerFireDmg();
		this.thunderDmg = player.getthunderDmg();
		this.diceRoll= 0;
		this.emyDiceRoll = 0;
		this.maxEmyHealth = enemy.getMaxHealth();
		this.specDiceRoll = 0;
	}
	public int getMaxEmyHealth() {
		return maxEmyHealth;
	}
	public int getEmyDiceRoll() {
		return this.emyDiceRoll;
	}
	public void setEmyDiceRoll() {
		this.emyDiceRoll = twentySideDice.getDiceRoll();
	}
	private void rollDice() {
		this.diceRoll = twentySideDice.getDiceRoll();
	}
	public int getDiceRoll() {
		return this.diceRoll;
	}
	
	public int getSpecDiceRoll() {
		return specDiceRoll;
	}
	public void setSpecDiceRoll() {
		this.specDiceRoll = twentySideDice.getDiceRoll();;
	}
	public int maxHealthPly() {
		return player.getPlayerHealth();
	}
	private void setPlayerAttack() {
		rollDice();
		this.damage = this.diceRoll + this.playerAttack - this.enemyDefense;

	}

	public int getPlayerAttack() {
		setPlayerAttack();
		return damage;
	}

	public void setEnemyHealth(int damage) {
		this.enemyHealth = enemyHealth - this.damage;

	}

	public int getEnemyHealth() {
		return enemyHealth;
	}

	public void setAtkEnemy() {
		setEmyDiceRoll();
		this.enemyDamage = this.emyDiceRoll + this.enemyAttack - this.playerDefense;
	}

	public int getEnenmyAtk() {
		setAtkEnemy();
		return enemyDamage;
	}

	public void setPlyhealth(int damage) {
		this.playerHealth = playerHealth - damage;
		player.saveToCsvFile(this.playerHealth);
	}

	public int getPlyHlth() {
		return playerHealth;
	}

	public int setHeal(int amount) {
		int healthBeforeHealing = this.playerHealth;
	    this.playerHealth += amount;

	    if (this.playerHealth > this.maxHealthPly()) {
	        this.playerHealth = this.maxHealthPly();
	    }

	    player.saveToCsvFile(this.playerHealth);

	    // Calculate the actual amount of health restored
	    int actualHealedAmount = this.playerHealth - healthBeforeHealing;
	    return actualHealedAmount;
	}
	public int setDefend() {
		int defenseDamage;
		int defenseboost = 10;
		defenseDamage = getEnenmyAtk() - this.playerDefense - defenseboost;
		if (defenseDamage < 0) {
			defenseDamage = 0;
		}
		setPlyhealth(defenseDamage);
		return defenseDamage;
	}

	public boolean deadOrAlive(int health) {
		if (health <= 0) {
			
			return false;
		} else
			return true;
	}

	public String getMonsterName() {
		return this.monsterName;
	}
	public void generateMonster() {
	    // Reset player stats
	    this.playerHealth = player.getPlayerHealth();
	    this.playerAttack = player.getPlayerAttack();
	    this.playerDefense = player.getPlayerDefense();
	    this.iceDmg = player.getIceDmg();
	    this.fireDmg = player.getPlayerFireDmg();
	    this.thunderDmg = player.getthunderDmg();

	    // Reset enemy stats
	    this.enemyHealth = enemy.getEnemyHealth();
	    this.enemyAttack = enemy.getEnemyAttack();
	    this.enemyDefense = enemy.getEnemyDefense();
	    this.maxEmyHealth = enemy.getMaxHealth();

	    // Reset damage and dice rolls
	    this.damage = 0;
	    this.enemyDamage = 0;
	    this.diceRoll = 0;
	    this.emyDiceRoll = 0;
    }
	public void battleEnd() {
	    if (this.enemyHealth <= 0) {
	        
	        generateMonster(); // Generate a new monster for the next encounter
	    }
	}
	public int applySpecialDamage(String type) {
        int specialDamage = 0;
        int bonus = 0;//setWeakNess();
        setSpecDiceRoll();
        switch (type) {
            case "Ice":
                specialDamage = calculateIceDamage(bonus);
                break;
            case "Fire":
                specialDamage = calculateFireDamage(bonus);
                break;
            case "Thunder":
                specialDamage = calculateThunderDamage(bonus);
                break;
        }
        this.enemyHealth -= specialDamage;
        return specialDamage;
    }
	private int calculateIceDamage(int bonus) {
		return  this.iceDmg + this.specDiceRoll - 8 + bonus;

    }

    private int calculateFireDamage(int bonus) {
    
		return  this.fireDmg + this.specDiceRoll - 8 + bonus;
    
    }

    private int calculateThunderDamage(int bonus) {
   
		return this.thunderDmg + this.specDiceRoll - 8 + bonus;
    
    }
    
    /*private int setWeakNess() {
    	int dmg = 10;
    	int noDmg = 0;
    	switch(enemy.getWeakness()) {
	    	case 0: 
	    		return dmg;
	    	case 1: 
	    		return dmg;
	    	case 2:
	    		return dmg;
	    	default:
	    		return noDmg;*/
	    		
    	//}
    //}
}
