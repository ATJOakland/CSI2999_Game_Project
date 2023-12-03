package csi2999;

import javax.swing.SwingUtilities;

public class MainClass {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
        	public void run(){
        		try {
        			new MainMenu();
        		}
        		catch (Exception e){
        			e.printStackTrace(); 
                    System.out.println("An error occurred: " + e.getMessage() + "Error");
        		}
        	}
        	});
        System.out.println("Game Started");
    }
}
