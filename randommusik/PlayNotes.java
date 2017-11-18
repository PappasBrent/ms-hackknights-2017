package randommusik;

import java.util.concurrent.TimeUnit;

/**
 * <h1>PlayNotes</h1>
 * <p>
 * Generates a sound from a single character passed from RandomMusik representing
 * a musical note
 * </p>
 * @author Alexander McLaughlin
 * @version 1.0.0
 * @since 11/10/2017
 * 
 */
public class PlayNotes 
{
    /**
     * Simply Creates a new PlayNote instance, no fields to alter
     */
    PlayNotes()
    {
        //Will do nothing
    }
    
    /**
     * Will generate from a character m a filename that goes with the .wav file
     * to be played for that given note
     * 
     * @param m a character which will be translated into a note file
     * @throws InterruptedException 
     */
    static public void play(char m) throws InterruptedException
    {
        String file;
        System.out.print(m+" ");
        TimeUnit.MILLISECONDS.sleep(250);
        if(Character.isLowerCase(m))
        {
            file=System.getProperty("user.dir")+"\\src\\randommusik\\Notes\\"+Character.toUpperCase(m)+"1 Note.wav";
        }
        else
        {
            file=System.getProperty("user.dir")+"\\src\\randommusik\\Notes\\"+m+" Note.wav";
        }
        Sound.play(file);
    }
    
    /**
     * Will be used to play chords instead of a single note, passes a string into 
     * the function and parses it until the end
     * 
     * @param n a string which will be broken into characters and played accordingly
     * @throws InterruptedException 
     */
    static public void play(String n) throws InterruptedException
    {
        String file;
        char m;
        System.out.print(n+"\n");
        TimeUnit.MILLISECONDS.sleep(50);
        for(int i=0; i<n.length(); i++)
        {
            m=n.charAt(i);
            if(Character.isLowerCase(m))
            {
                file=System.getProperty("user.dir")+"\\src\\randommusik\\Notes\\"+Character.toUpperCase(m)+"1 Note.wav";
            }
            else
            {
                file=System.getProperty("user.dir")+"\\src\\randommusik\\Notes\\"+m+" Note.wav";
            }
            Sound.play(file);
        }
    }
    
}
