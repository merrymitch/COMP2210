import java.util.Arrays;
/**
 * Autocomplete Program.
 *
 * Assignment 3
 * @author Mary Mitchell COMP 2210-001
 * @version 2/27/2021
 */
public class Autocomplete {
   
   private final Term[] terms;
   
   /**
    * Initializes a data structure from the given array of terms.
    * This method throws a NullPointerException if terms is null.
    *
    * @param terms The array being initialized. 
    * @throws NullPointerException for terms is null.
    */
   public Autocomplete(Term[] terms) { 
      if (terms == null) {
         throw new NullPointerException();
      } 
      this.terms = new Term[terms.length];
      for (int i = 0; i < terms.length; i++) {
         this.terms[i] = terms[i];
      }
      Arrays.sort(this.terms);
   }
   
   /**
    * Returns all terms that start with the given prefix, in descending order
    * of weight. This method throws a NullPointerException if prefix is null.
    *
    * @param prefix The prefix used for the autocomplete.
    * @return The terms that autocomplete the prefix. 
    * @throws NullPointerException for prefix is null. 
    */
   public Term[] allMatches(String prefix) { 
      if (prefix == null) {
         throw new NullPointerException();
      }
      //Range
      int first = BinarySearch.firstIndexOf(terms, new Term(prefix, 0),
                  Term.byPrefixOrder(prefix.length()));
      int last = BinarySearch.lastIndexOf(terms, new Term(prefix, 0),
                 Term.byPrefixOrder(prefix.length()));
      if (first == -1) {
         return new Term[0];
      }
      Term[] matchList = new Term[1 + last - first];
      for (int i = 0; i < matchList.length; i++) {
         matchList[i] = terms[first++];
      }
      Arrays.sort(matchList, Term.byDescendingWeightOrder());
      return matchList;
   }

}