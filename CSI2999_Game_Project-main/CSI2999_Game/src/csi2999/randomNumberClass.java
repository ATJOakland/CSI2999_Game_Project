package csi2999;

import java.security.SecureRandom;

public class randomNumberClass {
     private SecureRandom diceRoll = new SecureRandom();
     int upLimit = 20;
     int lowLimit = 0;
     private int rolled;
             
     public void randomNumberClass(){
         //diceRoll = new SecureRandom();
         int rolled = 0;
     }
     
     public void setDiceRoll() {
          rolled = diceRoll.nextInt(lowLimit, upLimit + 1);      
    }
     
    public int getDiceRoll() {
        setDiceRoll();
        return rolled;
    }
     
     
}
