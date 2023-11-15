package csi2999;

import java.util.Scanner ;


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
    private int damage;
    private int enemyDamage;
    private int iceDmg;
    private int fireDmg;
    private int wtrDmg;
    private String userInput;
    chacterClass player = new chacterClass();
    
    // creating my dice object
    private randomNumberClass twentySideDice = new randomNumberClass();
    
    public backGroundCombat(){
        // left empty since this is the outline for combat. 
        this.playerHealth = 200;
        this.playerAttack = 10;
        this.playerDefense = 10;
        this.enemyHealth = 50;
        this.enemyAttack = 2;
        this.enemyDefense = 2;
        this.iceDmg = 4;
        this.fireDmg = 5;
        this.wtrDmg = 6;
        


}

   private void setPlayerAttack(){
       this.damage = twentySideDice.getDiceRoll() + playerAttack;
     
   }
   public int getPlayerAttack(){
       setPlayerAttack();
       return damage;
   }
   public void setEnemyHealth(int damage){
       this.enemyHealth = enemyHealth - damage;
      
   }
   public int getEnemyHealth(){
       return enemyHealth;
   }
   
   public void setAtkEnemy(){
       this.enemyDamage = twentySideDice.getDiceRoll() + enemyAttack;
   }
   public int getEnenmyAtk(){
       setAtkEnemy();
       return enemyDamage;
   }
   public void setPlyhealth(int damage){
       this.playerHealth = playerHealth - damage;
   }
   public int getPlyHlth(){
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
	   if(defenseDamage < 0) {
		   defenseDamage = 0;
	   }
	   setPlyhealth(defenseDamage);
	   return defenseDamage;
   }
   public boolean deadOrAlive(int health) {
	   if( health <= 0) {
		   return false;
	   }else return true;
   }
   
}
