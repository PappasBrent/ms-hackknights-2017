package randommusik;

/**
 * <h1>MusicalNode</h1>
 * <p>
 * The MusicalNode class contains the different notes and their properties.
 * </p>
 * @author Alexander McLaughlin
 * @version 1.0.0
 * @since 11/10/2017
 * 
 */
public class MusicalNode
{
    /** 
     * Simply says what the note is in a character; Will be an uppercase C
     * if the note on a keyboard is C and will be a lowercase c if the 
     * note on the keyboard is C#, same goes for Bflat
     */
    public char note;

    /**
     * Holds the numbers and notes that are harmonic relative to this note
     */
    public int[][] harmonic;

    /**
     * The note immediately following 
     */
    public MusicalNode next;

    /**
     * Sets fields to defaults
     * 
     * @param none
     */
    MusicalNode()
    {
        // Note will begin at C if not otherwise noted

        // HashMap sizes will automatically be initialized to 12 because there
        // are twelve notes in an octave, this should account for if the note
        // were to be played twice
        note = 'C';
        harmonic = new int[2][12];

        next=null;

        this.fillHarmonic();
    }
   
    /**
     * Set all fields to default except for the initial note
     * 
     * @param note 
     */
    MusicalNode(char note)
    {
        // HashMap sizes will automatically be initialized to 12 because there
        // are twelve notes in an octave, this should account for if the note
        // were to be played twice subsequently
        this.note = note;
        harmonic = new int[2][12];

        next=null;

        this.fillHarmonic();
    }

    /**
     * Function will take all of the notes and produce a "harmonyArray"
     * or how nicely a particular note goes with each note in the octave
     * on a scale of 0 to 3.
     * 
     */
    public void fillHarmonic()
    {
        // Simply contains all of the different notes
        char[] noteArray = {'C','c','D','e','E','F','f','G','a','A','b','B'};
        // Will contain each notes harmonic counterparts
        int[] harmonyArray = new int[12];

        // C Harmonic Series:
        //+---+---+---+---+---+---+---+---+---+---+---+---+
        //| C   c   D   e   E   F   f   G   a   A   b   B | <- These lines show how nicely on a scale of 
        //| 0   3   2   1   0   0   3   0   3   1   2   3 | <- 0-3 these notes go together
        //+---+---+---+---+---+---+---+---+---+---+---+---+
        //Top: Note Array, Bottom: Harmony Array
        //TL:DR The Harmony Array is the edge weight between nodes

        if(this.note == 'C')
            harmonyArray = new int[]{0,3,2,1,0,0,3,0,3,1,2,3};
        else if(this.note == 'c')
            harmonyArray = new int[]{3,0,3,0,0,2,0,3,0,2,0,2};
        else if(this.note == 'D')
            harmonyArray = new int[]{2,3,0,3,2,2,0,0,3,1,3,1};
        else if(this.note == 'e')
            harmonyArray = new int[]{1,1,3,0,3,3,1,0,1,2,0,2};
        else if(this.note == 'E')
            harmonyArray = new int[]{0,2,3,3,0,3,2,2,0,0,3,1};
        else if(this.note == 'F')
            harmonyArray = new int[]{1,2,1,3,3,0,3,3,1,0,0,3};
        else if(this.note == 'f')
            harmonyArray = new int[]{3,1,0,2,1,3,0,3,2,1,2,0};
        else if(this.note == 'G')
            harmonyArray = new int[]{0,3,1,2,1,2,3,0,3,3,2,1};
        else if(this.note == 'a')
            harmonyArray = new int[]{3,0,3,1,0,1,2,3,0,3,2,1};
        else if(this.note == 'A')
            harmonyArray = new int[]{2,2,1,3,1,0,2,2,3,0,3,2};
        else if(this.note == 'b')
            harmonyArray = new int[]{3,1,2,0,3,0,0,1,2,3,0,3};
        else if(this.note == 'B')
            harmonyArray = new int[]{3,2,2,1,0,2,1,2,1,3,3,0};
        for(int i=0; i<12; i++)
        {
            harmonic[1][i]=harmonyArray[i];
            harmonic[0][i]=noteArray[i];
        }

    }
}