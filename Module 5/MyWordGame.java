import java.util.TreeSet;
import java.util.SortedSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.File;
import java.util.Iterator;


/** 
 * Class for word search game interface.
 *
 * @author Mary Mitchell (mem0250)
 * @version 3/28/2021
 */

public class MyWordGame implements WordSearchGame {
   private TreeSet<String> lexicon;
   private String[][] board;
   private Boolean[][] beenTo;
   private boolean lexIn;
   private ArrayList<Integer> track;
   private List<Location> track2;
   private SortedSet<String> goodWords;
   private int mag;
   private String wordTrack;
   
   
   
   public MyWordGame() {
      lexicon = new TreeSet<String>();
      goodWords = new TreeSet<String>();
      track = new ArrayList<Integer>();
      track2 = new ArrayList<Location>();
   }
   
   
   /**
     * Loads the lexicon into a data structure for later use. 
     * 
     * @param fileName A string containing the name of the file to be opened.
     * @throws IllegalArgumentException if fileName is null
     * @throws IllegalArgumentException if fileName cannot be opened.
     */
   public void loadLexicon(String fileName) {
      if (fileName == null) {
         throw new IllegalArgumentException();
      }
      try {
         Scanner scanLine = new Scanner(new File(fileName));
         lexicon = new TreeSet<String>();
         while (scanLine.hasNext()) {
            String lines;
            lines = scanLine.nextLine();
            lexicon.add(new Scanner(lines).next().toLowerCase());
         }
      }
      catch (Exception ex) {
         throw new IllegalArgumentException();
      }
      lexIn = true;
   }
    
    /**
     * Stores the incoming array of Strings in a data structure that will make
     * it convenient to find words.
     * 
     * @param letterArray This array of length N^2 stores the contents of the
     *     game board in row-major order. Thus, index 0 stores the contents of board
     *     position (0,0) and index length-1 stores the contents of board position
     *     (N-1,N-1). Note that the board must be square and that the strings inside
     *     may be longer than one character.
     * @throws IllegalArgumentException if letterArray is null, or is  not
     *     square.
     */
   public void setBoard(String[] letterArray) {
      if (letterArray == null) {
         throw new IllegalArgumentException();
      }
      int d = (int) Math.sqrt(letterArray.length);
      if ((d * d) != letterArray.length) {
         throw new IllegalArgumentException();
      }
      else {
         mag = (int) d;
         board = new String[mag][mag];
         beenTo = new Boolean[mag][mag];
         int index = 0;
         for (int i = 0; i < mag; i++) {
            for (int j = 0; j < mag; j++) {
               beenTo[i][j] = false;
               board[i][j] = letterArray[index].toLowerCase();
               index++;
            }
         } 
      }
   }
    
    /**
     * Creates a String representation of the board, suitable for printing to
     *   standard out. Note that this method can always be called since
     *   implementing classes should have a default board.
     */
   public String getBoard() {
      String output = "";
      for (int i = 0; i < mag; i++) {
         if (i > 0) {
            output = output + "\n";
         }
         for (int k = 0; k < mag; k++) {
            output = output + board[i][k] + " ";
         }
      }
      return output;
   }
    
    /**
     * Retrieves all scorable words on the game board, according to the stated game
     * rules.
     * 
     * @param minimumWordLength The minimum allowed length (i.e., number of
     *     characters) for any word found on the board.
     * @return java.util.SortedSet which contains all the words of minimum length
     *     found on the game board and in the lexicon.
     * @throws IllegalArgumentException if minimumWordLength < 1
     * @throws IllegalStateException if loadLexicon has not been called.
     */
   public SortedSet<String> getAllScorableWords(int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      } 
      if (!lexIn) {
         throw new IllegalStateException();
      } 
      goodWords = new TreeSet<String>();
      track2 = new ArrayList<Location>();
      wordTrack = "";
      for (int i = 0; i < mag; i++) {
         for (int k = 0; k < mag; k++) {
            wordTrack = board[i][k];
            if (isValidWord(wordTrack) && wordTrack.length() >= minimumWordLength) {
               goodWords.add(wordTrack);
            }
            if (isValidPrefix(wordTrack)) {
               Location hold = new Location(i, k);
               track2.add(hold);
               
            }
         }
      }
      
      return null;
   }
    
  /**
    * Computes the cummulative score for the scorable words in the given set.
    * To be scorable, a word must (1) have at least the minimum number of characters,
    * (2) be in the lexicon, and (3) be on the board. Each scorable word is
    * awarded one point for the minimum number of characters, and one point for 
    * each character beyond the minimum number.
    *
    * @param words The set of words that are to be scored.
    * @param minimumWordLength The minimum number of characters required per word
    * @return the cummulative score of all scorable words in the set
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException if loadLexicon has not been called.
    */  
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      if (!lexIn) {
         throw new IllegalStateException();
      }
      int output = 0;
      Iterator<String> iterate = words.iterator();
      while (iterate.hasNext()) {
         String stg = iterate.next();
         if (stg.length() >= minimumWordLength && isValidWord(stg) && !isOnBoard(stg).isEmpty()) {
            output = output + (stg.length() - minimumWordLength) + 1;
         }
      }
      return output;    
   }
    
    /**
     * Determines if the given word is in the lexicon.
     * 
     * @param wordToCheck The word to validate
     * @return true if wordToCheck appears in lexicon, false otherwise.
     * @throws IllegalArgumentException if wordToCheck is null.
     * @throws IllegalStateException if loadLexicon has not been called.
     */
   public boolean isValidWord(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      return lexicon.contains(wordToCheck.toLowerCase());
   }
    
    /**
     * Determines if there is at least one word in the lexicon with the 
     * given prefix.
     * 
     * @param prefixToCheck The prefix to validate
     * @return true if prefixToCheck appears in lexicon, false otherwise.
     * @throws IllegalArgumentException if prefixToCheck is null.
     * @throws IllegalStateException if loadLexicon has not been called.
     */
   public boolean isValidPrefix(String prefixToCheck) {
      if (prefixToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      return lexicon.ceiling(prefixToCheck).startsWith(prefixToCheck);
   }
        
    /**
     * Determines if the given word is in on the game board. If so, it returns
     * the path that makes up the word.
     * @param wordToCheck The word to validate
     * @return java.util.List containing java.lang.Integer objects with  the path
     *     that makes up the word on the game board. If word is not on the game
     *     board, return an empty list. Positions on the board are numbered from zero
     *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
     *     board, the upper left position is numbered 0 and the lower right position
     *     is numbered N^2 - 1.
     * @throws IllegalArgumentException if wordToCheck is null.
     * @throws IllegalStateException if loadLexicon has not been called.
     */
   public List<Integer> isOnBoard(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      return null;
   }


}