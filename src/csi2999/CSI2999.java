package csi2999;

import javax.swing.SwingUtilities;

public class CSI2999 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                new MainMenu();
                }
        });
    }
}