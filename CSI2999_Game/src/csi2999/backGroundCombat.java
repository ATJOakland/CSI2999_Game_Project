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
    
    // creating my dice object
    private randomNumberClass twentySideDice = new randomNumberClass();
    
    public void backGroundCombat(){
        // left empty since this is the outline for combat. 
        this.playerHealth = 200;
        this.playerAttack = 10;
        this.playerDefense = 10;
        this.enemyHealth = 50;
        this.enemyAttack = 2;
        this.enemyDefense = 2;


}
 /*   public void setTurn(){
        
        // this will be replaced by user selection
        Scanner input = new Scanner(System.in);
        boolean alive = true;
        do{
            // this will be replaced by on screen selection but rough outline
            System.out.println("Please enter if you would like to attack or defend?");
            userInput = input.nextLine();
            playerTurn();
            alive = checkHealth();
            if (alive == false){continue;}
            enemyTurn();  
            alive = checkHealth();
        }while(alive == true);
        
        
    }
        
    private boolean checkHealth(){
        //while ((playerHealth > 0) || (enemyHealth > 0)){
        
        if (playerHealth < 1){
                // game over screen
            System.out.println("Game Over");
            return false;
                }
        else if (enemyHealth < 1)    {
                // return back to level
            System.out.println("You win");
            return false;
                }
        else{
            return true;
        }
    }
    
    private void playerTurn(){
        int[] player = {playerAttack, playerFireDmg, playerFireDmg, playerFireDmg,
            playerFireDmg, playerDefense};
            
        int damage = 0;
        if(userInput.toLowerCase().startsWith("a")){
            damage = twentySideDice.getDiceRoll() + playerAttack;           
        }
        else if (userInput.toLowerCase().startsWith("f")){
            damage = twentySideDice.getDiceRoll() + playerFireDmg;
        }
        else if (userInput.toLowerCase().startsWith("i")){
            // ice damage
            damage = twentySideDice.getDiceRoll() + playerFireDmg;
        }
        else if (userInput.toLowerCase().startsWith("t")){
            // thunder damage
            damage = twentySideDice.getDiceRoll() + playerFireDmg;
        }
        else if (userInput.toLowerCase().startsWith("p")){
            // item usage
            int potion = 10;
            playerHealth = playerHealth + 20;
        }
        else if (userInput.toLowerCase().startsWith("d")){
            // item usage         
            playerDefense = playerDefense + 5;
        }
        enemyHealth = enemyHealth - damage;
        if (enemyHealth < 0){enemyHealth = 0;}
        System.out.println("Damge:" + damage + " Enemy Health Remaining: " + enemyHealth);
    }
    private void enemyTurn(){
        int damage = 0;
            damage = twentySideDice.getDiceRoll() + enemyAttack;    
            playerHealth = playerHealth - damage;
            if (playerHealth < 0){playerHealth = 0;}
            System.out.println("Damge:" + damage + " Player Health Remaining: " + playerHealth);
        }*/
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
   
   
}
