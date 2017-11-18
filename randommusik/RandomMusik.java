package randommusik;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.sound.midi.*;

/**
 * <h1>RandomMusik</h1>
 * <p>
 * RandomMusik was created to randomize, within reason, a set of musical notes. It generates them based on ceratain patterns
 * in music, mainly, that each note has a set of notes with which it has harmony and which it does not. Rather than classifying
 * notes based on what sounds nice with it and what doesn't, I split it into four groups. The groups are categorized according
 * to how harmonious on a scale of 0-3 they are with a particular note. From there they stem off into many different
 * statistics that lead to a final product of controlled, yet randomized music.
 * 
 * Based in UNIX and untested in LINUX based environments, in future adaptations this will be updated to meet standards of 
 * Windows, Linux, and Mac
 * </p>
 * @author Alexander McLaughlin
 * @version 1.0.0
 * @since 11/10/2017
 */
public class RandomMusik {
    
    /**
     * Makes use of the Musical Randomization
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException, IOException
    {
        //Presentation!
        String py1 = System.getProperty("user.dir")+"\\src\\randommusik\\";
        String py2 = System.getProperty("user.dir")+"\\src\\randommusik\\";
        ProcessBuilder pb1 = new ProcessBuilder("python", "MidiReader.py");
        pb1.directory(new File(py1));
        pb1.redirectError();
        
        //Sheet Musik (1)
        MusicalContainer m = new MusicalContainer(0);
        //m.compose(16);
        //Sheet Musik (2)
        //MusicalContainer k = new MusicalContainer(9);
        //k.compose(16);
        //Musik Analyzer
        
        ProcessBuilder pb2 = new ProcessBuilder("python", "MidiChanger.py");
        pb2.directory(new File(py2));
        pb2.redirectError();
        //Process p1 = Runtime.getRuntime().exec("python");
        
        String filename = System.getProperty("user.dir")+"\\src\\randommusik\\Notes.in";
        m.getSimilar(MusicalContainer.getStringFromFile(filename));
        
        //Process p2 = Runtime.getRuntime().exec("python"+py2);
        /* ALL BEFORE
        if(trial == -1 || trial == 1)
        {
            //Produces a random song and plays it
            MusicalContainer m = new MusicalContainer(9);
            m.compose(20);
            m.playComposition();

            m.printKeyProb();
        
            //Ouput:
            //Tone Shifts: will show indicate a positive or negative tonal shift
            //at certain points in the composition
            //Composition: simply displays the notes generated randomly
            //Performance: plays the music note for note and displays them on the
            //screen
            //
            //            +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
            //Tone Shifts:|   |   |   |   | + |   |   |   | - |   |   |   | - |   |   |
            //            +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
            //Composition:| A | C | D | f | E | A | B | B | B | b | c | D | E | G | C |
            //            +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
            //Performance:| A | C | D | f |...|   |   |   |   |   |   |   |   |   |   |
            //            +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
            //
            //Probablities: Tells what the odds were of selecting either a 
            //
            // no1.   no2.       no3.   no4.
            // very   slightly   less   not
            // in terms of the quality of harmony between this note and the next
            // at the very end of the program after execution

            //This tiny snippet shows the amount of trial and error necessary to get
            //the first 5 notes of ode to joy
            //this uses a modified overloaded compose function to generate 
            //the identified series of notes
        }
        
        ///Trial 2
        if(trial == -1 || trial == 2)
        {
            char[] song = new char[]{'E','E','F','G'};
            //Can be any song the user desires
            //For this particular song, it typically takes between 1 and 1,400,000 generations
            //depends highly on tonalShift

            //This application can be useful for an artist generating a song that
            //they want to be similar to another song that they know the notes to already
            //technically that's piracy, but live and let live

            MusicalContainer n = new MusicalContainer(song[0], 0);
            ArrayList ode = n.toList(song);

            System.out.println("Song to analyze:" + ode);
            System.out.println("System takes "+n.compose(ode)+" trials to generate this song using a tonal shift of "+n.tonalSet);

            //Prove mathematically this should be 8>>>
            //Will determine what the smallest tonalShift should be for a song like this
            n.bestFit(ode);

            //Update this to check the new statistical best tonalShift for the given song
            System.out.println("System takes "+n.compose(ode)+" trials to generate this song using a tonal shift of "+n.tonalSet);
        }
        
        //Trial 3
        if(trial == -1 || trial == 3)
        {
            //It is very possible to create chords in this system, generating them
            //randomly will be a task for v 1.1.0, mixing chords with singular notes
            //will be worked more thoroughly on in v 1.2.0

            //Create a string which the system will play, I picked a very harmonious
            //chord and one of the most common in early pieces:
            //A chord of length 3 in C Major
            PlayNotes.play("CEG");
            TimeUnit.MILLISECONDS.sleep(750);
            
            //Will then produce a few more chords which sound fairly nice
            //From length 2 to 4 most in the key of C
            PlayNotes.play("Df");
            TimeUnit.MILLISECONDS.sleep(750);
            PlayNotes.play("CG");
            TimeUnit.MILLISECONDS.sleep(750);
            PlayNotes.play("CFA");
            TimeUnit.MILLISECONDS.sleep(750);
            PlayNotes.play("DfA");
            TimeUnit.MILLISECONDS.sleep(750);
            PlayNotes.play("DGB");
            TimeUnit.MILLISECONDS.sleep(750);
            PlayNotes.play("CGEb");
            TimeUnit.MILLISECONDS.sleep(750);
            PlayNotes.play("CEGB");
            TimeUnit.MILLISECONDS.sleep(750);
        }*/
        
    }
}
