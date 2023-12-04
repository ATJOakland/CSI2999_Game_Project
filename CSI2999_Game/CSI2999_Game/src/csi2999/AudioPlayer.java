package csi2999;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
public class AudioPlayer {
	 private Clip audioClip;

	// used chatGPT, https://www.youtube.com/watch?v=wJO_cq5XeSA, https://docs.oracle.com/javase/tutorial/sound/accessing.html
	// audio is from https://www.fesliyanstudios.com/royalty-free-music/downloads-c/8-bit-music/6
    // Method to play and loop the audio file
    public void playSound(String resourcePath) {
        try {
            // Open an audio input stream from the file
        	URL audioResource = getClass().getResource(resourcePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioResource);

            // Get a sound clip resource
            Clip audioClip = AudioSystem.getClip();

            // Open audio clip and load samples from the audio input stream
            audioClip.open(audioStream);

            // Loop the clip indefinitely
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            audioClip.start();

            // Keep the program running until the user stops the application
            while (true) {
                Thread.sleep(1000);
            }

        } catch (UnsupportedAudioFileException e) {
            System.out.println("The specified audio file is not supported.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error playing the audio file.");
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.out.println("Audio line for playing back is unavailable.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Playback interrupted.");
            e.printStackTrace();
        }
    }
    public void stopMusic() {
        if (audioClip != null && audioClip.isRunning()) {
            audioClip.stop();
            audioClip.close();
        }
    }
}
