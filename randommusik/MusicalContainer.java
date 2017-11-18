package randommusik;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.*;

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
    * Will open a file and convert the input to a string
    * 
    * @param filename
    * @return
    * @throws IOException 
    */
   public static String getStringFromFile(String filename) throws IOException
   {
       String s;
       Scanner sc = new Scanner(new File(filename));
       
       s=sc.next();
       
       return s;
   }
   
   /**
    * Takes a song posed as an string of note characters and from that will find the average
    * tonal shift of that song and generate a new song based on that tonal shift
    * 
    * @param str A string that will be analyzed and semi-replicated
    * @throws InterruptedException
    * @throws IOException
    */
   public void getSimilar(String str) throws IOException, InterruptedException
   {
       //overload previously with filename
       int size;
       if(str.length()%2==0)
           size=str.length()/2;
       else
           size=str.length()/2+1;
       int[] generationHarmony = new int[size];
       ArrayList finArray = new ArrayList();
       char[][] breakDown = new char[size][2];
       
       for(int i=0, j=0; j<str.length(); j++)
       {
           if(j!=0 && j%2==0)
               i++;
           breakDown[i][j%2]=str.charAt(j);
           //System.out.println(i +", "+j%2);
       }
       
       for(int i=0; i<size; i++)
       {
           if(str.length()%2!=0 && i==size-1)
               generationHarmony[i] = getHarmonic(breakDown[i][0], breakDown[0][1]);
           else
               generationHarmony[i] = getHarmonic(breakDown[i][0], breakDown[i][1]);
       }
       
       for(int i=0; i<generationHarmony.length; i++)
       {
           if(i==0)
               finArray.add(getRandomHarmonic(generationHarmony[0]));
           else
               finArray.add(getRandomHarmonic(generationHarmony[i-1]));
               
           finArray.add(getRandomHarmonic(generationHarmony[i]));
       }
       
       //this.playComposition(finArray);
       this.printToFile(System.getProperty("user.dir")+"\\src\\randommusik\\Notes.out", finArray);
   }
   
   /**
    * Will check the harmonic array of each node for the harmony of each
    * 
    * @param c1 The first note character to compare
    * @param c2 The second note character to compare
    * @return integer value signifying the harmony of the two values
    */
   public int getHarmonic(char c1, char c2)
   {
       MusicalNode m1 = new MusicalNode(c1);
       MusicalNode m2 = new MusicalNode(c2);
       
       return MusicalNode.checkHarmony(m1, m2);
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
    * Prints out the composition of notes as characters to a file, and plays them after
    * 
    * @param length indicates the length of the song being composed
    * 
    * @throws IOException
    * @throws InterruptedException
    */
   public void printToFile(int length) throws IOException, InterruptedException
   {
       String file1 = System.getProperty("user.dir")+"\\src\\randommusik\\hackknights\\hackknights\\paintStaffs.html";
       String file2 = System.getProperty("user.dir")+"\\src\\randommusik\\src.html";
       
       //default text of notes.html to notes.txt
       defaultFile(file1, file2);
       
       String file = file1;
       
       
       FileWriter f = new FileWriter(file, true);
       BufferedWriter w = new BufferedWriter(f);
       
       MusicalNode temp;
       temp=this.note;
       w.write("\n    <div id=\"number_of_notes\">"+length+"</div>\n");
       //Set up to print sheet music
       
       w.write("    <div id=\"note_characters\">");
       
       while(temp.next!=null)
       {
           w.write(temp.note);
           temp=temp.next;
       }
       
       w.write("</div>");
       w.write("\n</body>\n</html>");
       
       w.close();
       f.close();
       
       openFile(file1);
       
       this.playComposition();
       
   }
   
   public void printToFile(String file, ArrayList a) throws IOException
   {
       FileWriter f = new FileWriter(file);
       BufferedWriter w = new BufferedWriter(f);
       
       String s="";
       
       for(Object o: a)
           s+=o.toString();
       
       w.write(s);
       
       w.close();
       f.close();
   }
   
   /**
    * Will open the HTML file generated by the printToFile function
    * 
    * @see printToFile
    * @param f the html file to be opened
    * @throws IOException 
    */
   public void openFile(String f) throws IOException
   {
        File html = new File(f);
        Desktop.getDesktop().browse(html.toURI());
   }
   
   /**
    * Will default the HTML file to original text
    * 
    * @param f1 the final file
    * @param f2 the file containing template text
    * @throws IOException 
    */
   public void defaultFile(String f1, String f2) throws IOException
   {
       StringBuilder content = new StringBuilder();
       String s;
       
       FileReader fr = new FileReader(f2);
       BufferedReader br = new BufferedReader(fr);
       
       FileWriter fw = new FileWriter(f1);
       BufferedWriter bw = new BufferedWriter(fw);
       
       while((s=br.readLine())!=null)
       {
           content.append(s);
       }
       
       //System.out.println(content.toString());
       
       bw.write(content.toString());
       
       bw.close();
       br.close();
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
   
   /**
    * Plays notes directly from an ArrayList
    * 
    * @param a an arraylist containing notes to play
    * @throws InterruptedException 
    */
   public void playComposition(ArrayList a) throws InterruptedException
   {
       System.out.print("Performance: ");
       
       for(Object o: a)
       {
           PlayNotes.play((char)o);
           TimeUnit.MILLISECONDS.sleep(250);
       }
       TimeUnit.MILLISECONDS.sleep(500);
       System.out.println();
   }
   
}
