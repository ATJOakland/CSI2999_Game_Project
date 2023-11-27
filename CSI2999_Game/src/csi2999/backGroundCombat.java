package csi2999;

public class backGroundCombat {
	// this is will be called in based on enemies
	private int enemyHealth;
	private int enemyAttack;
	private int enemyDefense;
	// this is be called in from char sheet
	private int playerHealth;
	private int playerAttack;
	private int playerFireDmg;
	private int playerDefense;
	private int diceRoll, emyDiceRoll;
	private int damage;
	private int enemyDamage;
	private int iceDmg;
	private int fireDmg;
	private int wtrDmg;
	
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
		this.enemyHealth = 50;//enemy.getEnemyHealth();
		this.enemyAttack = 2;//enemy.getEnemyAttack();
		this.enemyDefense = 2;//enemy.getEnemyDefense();
		this.iceDmg = player.getIceDmg();
		this.fireDmg = player.getPlayerFireDmg();
		this.wtrDmg = player.getWtrDmg();
		this.diceRoll= 0;
		this.emyDiceRoll = 0;

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

	public int setIceDmg(int dmg, int bonus) {
		int iceDmg;
		iceDmg = this.iceDmg + dmg + bonus;
		return iceDmg;
	}

	public int setFireDmg(int dmg, int bonus) {
		int fireDmg;
		fireDmg = this.fireDmg + dmg + bonus;
		return fireDmg;
	}

	public int setWtrDmg(int dmg, int bonus) {
		int wtrDmg;
		wtrDmg = this.wtrDmg + dmg + bonus;
		return wtrDmg;
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
	public void SetEnemyHealthBack() {
		this.enemyHealth = 50;
	}
	public void resetCombat() {
        this.monsterName = monster.generateMonster();
        enemy = new EnemyClass(1, this.monsterName);
        player = new characterClass(1);
        this.playerHealth = player.getPlayerHealth();
        this.playerAttack = player.getPlayerAttack();
        this.playerDefense = player.getPlayerDefense();
        this.enemyHealth = 50;
        this.enemyAttack = 2;
        this.enemyDefense = 2;
        this.iceDmg = player.getIceDmg();
        this.fireDmg = player.getPlayerFireDmg();
        this.wtrDmg = player.getWtrDmg();
    }

}
