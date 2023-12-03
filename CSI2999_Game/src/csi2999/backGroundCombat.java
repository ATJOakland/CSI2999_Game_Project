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
	private int elemMinus = 3;
	// creating my dice object
	private randomNumberClass twentySideDice = new randomNumberClass();
	public backGroundCombat(int level, boolean boss) {

		// Check if it's a boss battle
        if (boss) {
            // Set boss stats
            this.monsterName = "Undead Commander";
            this.enemyHealth = 375;
            this.enemyAttack = 9;
            this.enemyDefense = 7;
            this.maxEmyHealth = 375;
            // Create an EnemyClass instance for the boss
            enemy = new EnemyClass(level, this.monsterName);
        } else {
            // Generate a regular monster
            this.monsterName = monster.generateMonster();
            // Create an EnemyClass instance for the regular monster
            enemy = new EnemyClass(level, this.monsterName);
            // Set stats for the regular monster
            this.enemyHealth = enemy.getEnemyHealth();
            this.enemyAttack = enemy.getEnemyAttack();
            this.enemyDefense = enemy.getEnemyDefense();
            this.maxEmyHealth = enemy.getMaxHealth();
        }

        // Initialize the player
        player = new characterClass(level);
        // Set player stats
        this.playerHealth = player.getCurrentHealth();
        this.playerAttack = player.getPlayerAttack();
        this.playerDefense = player.getPlayerDefense();
        this.iceDmg = player.getIceDmg();
        this.fireDmg = player.getPlayerFireDmg();
        this.thunderDmg = player.getthunderDmg();

        // Initialize other class members
        this.diceRoll = 0;
        this.emyDiceRoll = 0;
        this.specDiceRoll = 0;

        // Perform any other initialization needed for the level
        initializeLevel(level);

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
		if(enemyHealth - damage < 0) {
			this.enemyHealth = 0;
		 }else {this.enemyHealth = enemyHealth - damage;}

	}

	public int getEnemyHealth() {
		return enemyHealth;
	}

	public void setAtkEnemy() {
		setEmyDiceRoll();
		int dmg;
		dmg = this.emyDiceRoll + this.enemyAttack - this.playerDefense;;
		if(dmg < 0) {
			this.enemyDamage = 0;
			}else {
				this.enemyDamage = this.emyDiceRoll + this.enemyAttack - this.playerDefense;
		}
	}

	public int getEnenmyAtk() {
		setAtkEnemy();
		return enemyDamage;
	}

	public void setPlyhealth(int damage) {
		if(playerHealth - damage < 0) {
			this.playerHealth = 0;
		}else {this.playerHealth = playerHealth - damage;}
		
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
        int bonus = setWeakNess();
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
        //this.enemyHealth -= specialDamage;
        return specialDamage;
    }
	
	private int calculateIceDamage(int bonus) {
		return  this.iceDmg + this.specDiceRoll - elemMinus + bonus;

    }

    private int calculateFireDamage(int bonus) {
    
		return  this.fireDmg + this.specDiceRoll - elemMinus + bonus;
    
    }

    private int calculateThunderDamage(int bonus) {
   
		return this.thunderDmg + this.specDiceRoll - elemMinus + bonus;
    
    }
    
    public void initializeLevel(int level) {
        this.monsterName = monster.generateMonster();
        enemy = new EnemyClass(level, this.monsterName);
        player = new characterClass(level); //
        updateStats();
    }
    private void updateStats() {
        this.playerHealth = player.getCurrentHealth();
        this.playerAttack = player.getPlayerAttack();
        this.playerDefense = player.getPlayerDefense();
        this.enemyHealth = enemy.getEnemyHealth();
        this.enemyAttack = enemy.getEnemyAttack();
        this.enemyDefense = enemy.getEnemyDefense();
        this.iceDmg = player.getIceDmg();
        this.fireDmg = player.getPlayerFireDmg();
        this.thunderDmg = player.getthunderDmg();
        this.maxEmyHealth = enemy.getMaxHealth();

        // Reset damage and dice rolls
        this.damage = 0;
        this.enemyDamage = 0;
        this.diceRoll = 0;
        this.emyDiceRoll = 0;
        this.specDiceRoll = 0;
    }
    private int setWeakNess() {
    	int dmg = 10;
    	int noDmg = 0;
    	switch(enemy.getWeakness()) {
	    	case 1: 
	    		return dmg;
	    	case 2: 
	    		return dmg;
	    	case 3:
	    		return dmg;
	    	default:
	    		return noDmg;
	    		
    	}
    }
    public void setBossStats() {
    	int health, attack, defense;
    	health = 375;
    	attack = 9;
    	defense = 7;
    	this.monsterName = "Undead Commander";
    	this.enemyHealth = health;
    	this.enemyAttack = attack;
    	this.enemyDefense = defense;
    	this.maxEmyHealth = health;
    	enemy.setEnemyAttack(attack);
    	enemy.setEnemyHealth(health);
    	enemy.setEnemyDefense(defense);
    	//this.weakness =2;
    }
}
