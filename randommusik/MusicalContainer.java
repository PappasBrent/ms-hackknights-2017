package randommusik;

import static java.awt.JobAttributes.DestinationType.FILE;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * <h1>MusicalContainer</h1>
 * <p>
 * The MusicalContainer class holds all the notes and runs functions
 * for generating random patterns of those notes.
 * </p>
 * 
 * @author Alexander McLaughlin
 * @version 1.0.0
 * @since 11/10/2017
 * 
 */
public class MusicalContainer
{ 
   /**
    * A MusicalNode which each singular note is contained in
    * @see MusicalNode
    */
   public MusicalNode note;
   
   /** 
    * This tiny little int is where the magic of generation comes from, 
    * depending on the tonal set, on a scale of 0-9, the piece will either be a 0
    * which denotes a very very light song with little to no dissonant sounds, to a 
    * 9 which sounds very dark and harsh
    */
   public int tonalSet;

   /**
    * Contains the probability of each harmony being chosen depending on the harmonicArray of the note
    */
   public int[] keyProb = new int[4];

   /**
    * Sets defaults for all fields
    * 
    */
   MusicalContainer()
   {
       note = new MusicalNode();
       // Without an input value for the tonal set, the program defaults to 4
       tonalSet=4;
       updateKeyProb();
   }
   
   /**
    * A constructor which takes a parameter to override the default tonalSet.
    * 
    * @param tonalSet Overrides the default tonalSet
    */
   MusicalContainer(int tonalSet)
   {
       note = new MusicalNode();
       //Checks the bounds of the input
       this.tonalSet = (tonalSet<0 || tonalSet>9)? 4:tonalSet;
       updateKeyProb();
   }
   
   /**
    * Initializes the first note to whatever the passed parameter note is.
    * 
    * @param note overrides the default note C
    */
   MusicalContainer(char note)
   {
       this.note = new MusicalNode(note);
       //Checks the bounds of the input
       this.tonalSet = 4;
       updateKeyProb();
   }

   /**
    * Will override the default values for both the tonalSet and the starting note
    * 
    * @param note overrides the default note C
    * @param tonalSet overrides the default tonalSet
    */
   MusicalContainer(char note, int tonalSet)
   {   
       this.note = new MusicalNode(note);
       //Checks the bounds of the input
       this.tonalSet = (tonalSet<0 || tonalSet>9)? 4:tonalSet;
       updateKeyProb();
   }

   /**
    * Harmonize increases the value of tonalSet making the music more varied
    * 
    */
   public void harmonize()
   {
       if(this.tonalSet>=9)
           return;

       //Increases the tonalSet
       this.tonalSet++;
       updateKeyProb();
   }
   
   /**
    * set the tonalSet to the desired number, checks if it is within bounds [0,9]
    * @param harmony a harmonic number passed into the array
    */
   public void harmonize(int harmony)
   {
       if(harmony<0 || harmony>9)
           return;
       
       this.tonalSet=harmony;
       updateKeyProb();
   }
   
   /**
    * Deharmonize decreases the value of tonalSet making the music less varied
    * 
    */
   public void deharmonize()
   {
        if(this.tonalSet<=0)
            return;

        //Decreases the tonalSet
        this.tonalSet--;
        updateKeyProb();
   }

   /**
    * Updates the probabilities for each tone to be selected during composition
    * 
    */
   public void updateKeyProb()
   {
        this.keyProb[0]=((9-tonalSet)*11);
        this.keyProb[1]=(int)((100-keyProb[0])*(1.0-(tonalSet/10.0)));
        this.keyProb[2]=(int)((100-keyProb[0])*((tonalSet/10.0)));
        this.keyProb[3]=100-(keyProb[0]+keyProb[1]+keyProb[2]);
   }
   
   /**
    * Prints the probabilities of each tone being selected during composition
    * 
    */
   public void printKeyProb()
   {
       System.out.print("End Probabilities of Selecting Most Harmonic vs Lesser Harmonic: ");
       System.out.println(keyProb[0]+"% "+keyProb[1]+"% "+keyProb[2]+"% "+keyProb[3]+"%");
       System.out.println();
   }

   /**
    * Will overload the compose method with a default value of 10
    * Throws an Interrupted exception due to PlayNotes
    * 
    * @throws InterruptedException 
    * @throws IOException
    */
   public void compose() throws InterruptedException, IOException
   {
       //Gives a default length of 10 notes
       this.compose(10);
   }
   
   /**
    * Takes a number of random notes to generate and randomly creates them
    * 
    * @param length the length of the composition in number of notes
    * @throws InterruptedException 
    * @throws IOException
    */
   public void compose(int length) throws InterruptedException, IOException
   {
       MusicalNode temp;
       int change;
       int tone;
       temp = this.note;
       
       System.out.print("Tone Shifts: ");
       
       for(int i=0; i<length; i++)
       {
           tone = this.getKey();
           
           temp.next = new MusicalNode(this.getRandomHarmonic(tone));
           temp=temp.next;
           
           // 10% chance of a tonal shift nearly unrecognizable to user
           change = (int)(Math.random()*100);
           if(change<10)
           {
               //50-50 chance of upward tonal shift or downward tonal shift
               change = (int)(Math.random()*100);
               if(change<50)
               {
                   System.out.print("+ ");
                   this.harmonize();
               }
               else
               {

                   this.deharmonize();
               }
           }
       }
       System.out.println();

       //this.printComposition();
       this.printToFile(length);
   }
   
   /**
    * Takes an ArrayList, generates random pieces of music until it matches
    * the one passed as a parameter
    * 
    * @param song an ArrayList containing a melody
    * @see ArrayList
    * @return 
    * @throws InterruptedException 
    */
   public int compose(ArrayList song) throws InterruptedException
   {
       MusicalNode temp;
       int change;
       int tone;
       int count;
       int length=song.size();
       
       int originalTone = this.tonalSet;
       
       ArrayList current = new ArrayList();
       
        for(count=1; !equalArray(song, current) && count<10000000; count++)
        {
            current.clear();
            temp = this.note;
            this.tonalSet=originalTone;
            
            for(int i=0; i<length; i++)
            {
                tone = this.getKey();

                current.add(temp.note);
                
                temp.next = new MusicalNode(this.getRandomHarmonic(tone));
                
                temp=temp.next;

                // 10% chance of a tonal shift nearly unrecognizable to user
                change = (int)(Math.random()*100);
                if(change<10)
                {
                    //50-50 chance of upward tonal shift or downward tonal shift
                    change = (int)(Math.random()*100);
                    if(change<50)
                        this.harmonize();
                    else
                        this.deharmonize();
                }
            }
            //System.out.print("Trial #"+count+" ");
            //this.printComposition();
        }
        return count;
   }
   
   /**
    * Finds the best fit for tonal shift that is similar to a passed melody
    * @param song a melody which is passed in to be duplicated with multiple tonalSets
    * @throws InterruptedException
    * @see compose
    */
   public void bestFit(ArrayList song) throws InterruptedException
   {
       int iterations=0, total=0, i, minTone=0;
       int[] average = new int[10];
       
       for(int j=0; j<10; j++)
        {
            this.harmonize();
            total=0;
            iterations=0;
            
            for(i=0; i<250; i++)
            {
                iterations=this.compose(song);
                total+=iterations;
            }

            average[j]=total/i;
        }
        
        for(i=0; i<10; i++)
            if(average[minTone]>average[i])
                minTone=i;
        
        this.harmonize(minTone);
   }
   
   /**
    * Converts a passed character array of notes to an ArrayList
    * 
    * @param song a list of notes to be converted from an array to an ArrayList
    * @return an ArrayList populated with character notes
    */
   public ArrayList toList(char[] song)
   {
        ArrayList ode = new ArrayList(song.length);
        for(int i=0; i<song.length; i++)
            ode.add(song[i]);
        
        return ode;
   }
   
   /**
    * Checks if ArrayLists a and b are the same, return true or false
    * 
    * @param a ArrayList #1
    * @param b ArrayList #2
    * @return true if the same, false otherwise
    */
   public boolean equalArray(ArrayList a, ArrayList b)
   {
       boolean flag=true;
       
       if(a.size()!=b.size())
           return false;
       
       for(int i=0; i<a.size(); i++)
           if(a.get(i)!=b.get(i))
               flag=false;
       
       return flag;
   }
   
   /**
    * Will fetch, based on the statistics in the keyProb array, which tone to
    * 
    * @return integer of random key relevant to key probability
    */
   public int getKey()
   {
       int rand = (int)(Math.random()*100);
       
       if(rand<keyProb[0])
           return 0;
       
       rand = (int)(Math.random()*100);
       
       if(rand<keyProb[1])
           return 1;
       
       rand = (int)(Math.random()*100);
       
       if(rand<keyProb[2])
           return 2;
       
       
       return 3;
   }
   
   /**
    * Based on the tone passed as a parameter, will get from a list of harmonies
    * a suitable note 
    * 
    * @param tone the toneSet which determines the broadness of notes
    * @return a character representing a note
    */
   public char getRandomHarmonic(int tone)
   {
       char[] harmonies;
       int i, h=0;
       int rand;
       
       for(i=0; i<12; i++)
       {
           if(this.note.harmonic[1][i]==tone)
               h++;
       }
       
       harmonies = new char[h];
       
       for(i=0, h=0; i<12; i++)
       {
           if(this.note.harmonic[1][i]==tone)
           {
               harmonies[h]=(char)this.note.harmonic[0][i];
               h++;
           }
       }
       
       rand = (int)(Math.random()*h);
       
       return harmonies[rand];
   }

   /**
    * Prints out the composition of notes as characters
    * 
    * @throws InterruptedException 
    */
   public void printComposition() throws InterruptedException
   {
       MusicalNode temp;
       temp=this.note;
       System.out.print("Composition: ");
       //Set up to print sheet music
       
       while(temp.next!=null)
       {
           System.out.print(temp.note + " ");
           temp=temp.next;
       }
       System.out.println();
       //playComposition();
   }
   
   /**
    * Prints out the composition of notes as characters to a file
    * 
    * @throws IOException
    */
   public void printToFile(int length) throws IOException
   {
       String file = System.getProperty("user.dir")+"\\src\\randommusik\\notes.txt";
       FileWriter f = new FileWriter(file);
       BufferedWriter w = new BufferedWriter(f);
       
       String s="";
       
       MusicalNode temp;
       temp=this.note;
       s+=(length+"\n");
       //Set up to print sheet music
       
       while(temp.next!=null)
       {
           s+=(temp.note);
           temp=temp.next;
       }
       
       w.write(s);
       w.close();
       f.close();
   }
   
   /**
    * Will call on the PlayNotes class to generate a sound for each character
    * 
    * @throws InterruptedException 
    */
   public void playComposition() throws InterruptedException
   {
       MusicalNode temp;
       temp=this.note;
       System.out.print("Performance: ");
       
       while(temp.next!=null)
       {
           PlayNotes.play(temp.note);
           temp=temp.next;
           TimeUnit.MILLISECONDS.sleep(250);
       }
       TimeUnit.MILLISECONDS.sleep(500);
       System.out.println();
   }
}
