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
        


}

   private void setPlayerAttack(){
       this.damage = twentySideDice.getDiceRoll() + playerAttack;
     
   }
   public int getPlayerAttack(){
       setPlayerAttack();
       return damage;
   }
   public void setEnemyHealth(int damage){
       this.enemyHealth = enemyHealth - playerAttack;
      
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
   public void setPlyhealth(){
       this.playerHealth = playerHealth - enemyDamage;
   }
   public int getPlyHlth(){
       return playerHealth;
   }
   public boolean deadOrAlive(int health) {
	   if( health <= 0) {
		   return false;
	   }else return true;
   }
   
}
