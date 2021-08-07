/**
 * Applies the linear scan strategy to counting the number of negative
 * values in an array.
 */
public class CountNegatives {

   /**
    * Returns the number of negative values in the given array.
    */
   public static int countNegatives(int[] a) {
      int negatives = 0;
      for (int value : a) {
         if (value < 0) {
            negatives++;
         }
      }
      return negatives;
   }
}
