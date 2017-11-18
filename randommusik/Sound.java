package randommusik;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * <h1>Sound</h1>
 * <p>
 * Responsible for creating the thread and playing the passed filename
 * </p>
 * @author Alexander McLaughlin
 * @version 1.0.0
 * @since 11/10/2017
 * 
 */
public class Sound 
{
    /**
     * Uses a thread to play the given sound of the file
     * passed through the function created below
     * @param fileName 
     */
    public static synchronized void play(String fileName) 
    {             
        try 
        {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fileName));
            clip.open(inputStream);
            clip.start(); 
        } 
        catch (Exception e) 
        {
            System.out.println("play sound error: " + e.getMessage() + " for " + fileName);
        }
    }
}