/* 

SCRIPT REFERENCE BY THIS TUTORIAL:

https://www.youtube.com/watch?v=VE7ezYCTPe4&list=PL8CAB66181A502179&index=1

Google and ChatGPT also used for reference of some aspects like checking if game
is running

*/

package csi2999;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension; // You need to import Dimension
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;

public class LevelOne extends Canvas {
    // JFrame declaration
    private JFrame frame;
    
    //For future use for something (the tutroial dude said it might be needed)
    private static final long serialVersionUID = 1L;
    
    //Checks if running or not
    public boolean running = false;
    //Checks current update count
    public int updateCount = 0;
    
    // Declares window sizes to match the main menu's window size
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int SCALE = 1;
    
    // BufferImage variable for the pixels
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
            BufferedImage.TYPE_INT_RGB);
    
    //Pixels cariable to be created in the render method
    private int[] pixels = ((DataBufferInt)
            image.getRaster().getDataBuffer()).getData();

    public LevelOne() {
        // Create the JFrame and JPanel
        frame = new JFrame("Level One");
        JPanel panel = new JPanel();
        
        // Dclares max min and perferred sizes
        frame.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        frame.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        frame.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        // Sets JFrame closing option
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sets JFrame layout
        frame.setLayout(new BorderLayout());

        // Centers the layout
        frame.add(panel, BorderLayout.CENTER);
        
        // Adds the canvas to the panel
        panel.add(this); // Add the LevelOne canvas to the panel

        // Sets frame to at or above size
        frame.pack();

        // Disables window resizing
        frame.setResizable(false);

        // Centers the frame on the screen
        frame.setLocationRelativeTo(null);

        // Makes the frame visible
        frame.setVisible(true);
        
        // Add a window listener to detect when the frame is closed or not
        // aka if the game is running
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Handle frame closure here
                running = false;
                System.out.println("GAME CLOSED.");
                System.exit(0); // Exit the application when frame closed
            }
        });
    }
    
    //Starts the game
    public void start(){
        //Declare the game to be running
        running = true;
        
        /* 
        Start a new thread for the game loop (for multi-threading I guess???)
        This is here because of an issue where in the while loop in gameLoop()
        is active, it prevents the user from pressing the 'X' in the corner
        to just close the application. So this is very important for the levels.
        */
        Thread gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                gameLoop();
            }
        });
        gameThread.start();
    }
    
    //Loop for updating and rendering the game
    private void gameLoop() {
        //FPS Variables. Will limit the while calls to 60FPS so that
        //It isn't called super fast forever (and breaking the game)
        long lastTime = System.nanoTime(); //nanotTime for accuracy
        double nanoSecondsPerTick = 1000000000D/60D; //1 billion nanoseconds
        //The above nanoSecondsPerTick is divided by 60 to get how many updates
        //are in those 60 nanoseconds
        
        int updates = 0; //How many updates (ticks) have been done
        int frames = 0; //How many frames per second.
        
        long lastTimer = System.currentTimeMillis();
        double delta = 0; //How many unprocessed nanoseconds since 60.
        
        System.out.println("GAME RUNNING.");
        while(running) //Update the game while it runs
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoSecondsPerTick;
            lastTime = now;
            boolean shouldRender = true; //will only render when update if false
            
            while(delta >= 1){
                updates++;
                update();
                delta -= 1;
                
                shouldRender = true; //Set should render to tue only if updating
            }
            
            //Prevents ridiculous FPS if shouldRender = true (it does)
            //Comment out for higher framerate (but it gets really high)
            try{
                Thread.sleep(2);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            
            //If Should render is true (above)
            if(shouldRender){
                frames++;
                render();
            }
            
            //If current time - last time is >= 1000 miliseconds (1 second)
            if(System.currentTimeMillis() - lastTimer >= 1000)
            {
                lastTimer += 1000;
                System.out.println(frames + "FPS, " + updates + " Updates.");
                //Reset the frames and updates
                frames = 0;
                updates = 0;
            }
        }
    }
    
    public void update()
    {
        updateCount++;
        
        for(int i = 0; i < pixels.length; i++)
        {
            pixels[i] = i * updateCount;
        }
    }
    
    //This method renders the visuals on screen
    public void render() {
        // BufferStrategy is an object for organizing the data on the canvas
        BufferStrategy bs = getBufferStrategy();
        // If it doesn't exist then create one
        if (bs == null) {
            createBufferStrategy(3);//Triple buffering (no img tearing)
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        
        g.dispose(); // Gets rid of all unseen/unused graphics
        bs.show(); // Show the contents of the buffer (aka the graphics)
    }
}