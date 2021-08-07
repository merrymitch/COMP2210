import java.util.Arrays;
/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Mary Mitchell (mem0250@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  1/30/2021
*
*/
public final class Selector {

    /**
     * Can't instantiate this class.
     *
     * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
     *
     */
   private Selector() { }


    /**
     * Selects the minimum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     *
     * @param a The array being searched. 
     * @return The minimum value in the array.
     * @throws IllegalArgumentException if array is null or has 
     * length zero.  
     */
   public static int min(int[] a) {
      /** Illegal Exceptions. **/
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      
      /** Basic linear search for min value. **/
      int minimum = a[0];
      for (int i = 1; i < a.length; i++) {
         if (a[i] < minimum) {
            minimum = a[i];
         }   
      }
      return minimum;
   }


    /**
     * Selects the maximum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     *
     * @param a The array being searched. 
     * @return The maximum value in the array. 
     * @throws IllegalArgumentException if array is null or has 
     * length zero.
     */
   public static int max(int[] a) {
      /** Illegal Exceptions. **/
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      
      /** Basic linear search for max value. **/
      int maximum = a[0];
      for (int i = 1; i < a.length; i++) {
         if (a[i] > maximum) {
            maximum = a[i];
         }
      }
      return maximum;
   }


    /**
     * Selects the kth minimum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth minimum value. Note that there is no kth
     * minimum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     *
     * @param a The array being searched.
     * @param k The kth value being searched for. 
     * @return The kth minimum value in the array. 
     * @throws IllegalArgumentException if array is null, has 
     * length zero, or for specific values of k. 
     */
   public static int kmin(int[] a, int k) {
      /** Illegal Exceptions. **/
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      if (k < 1 || k > a.length) {
         throw new IllegalArgumentException();
      }
      
      int kmin = 0;
      
      /** uniqueValues is to count the distinct numbers
      so as not to include the duplicates in the count. **/
      int uniqueValues = 1;
      
      /** Since int[] a cannot be changed, a copy is made 
      and then sorted to make loops easier. **/
      int[] copyA = new int[a.length];
      for (int i = 0; i < a.length; i++) {
         copyA[i] = a[i];
      }
      Arrays.sort(copyA);
      
      /** holder is just used in the loop to compare values. **/
      int holder = copyA[0];
      
      /** If k is one then kmin is simply the min. **/
      if (k == 1) {
         kmin = copyA[0];
      }
      
      /** Runs through the sorted array. Checks two values  
      to see if they are the same. If they are not then 
      the number of unique values is incremented. This proceeds
      until the kmin unique value is found. **/
      for (int i = 1; i < a.length; i++) {
         if (copyA[i] != holder) {
            uniqueValues++;
            if (uniqueValues == k) {
               kmin = copyA[i];
            }
         }
         holder = copyA[i];
      }
      
      /** After the array has been through the loop,
      if the number of unique values exceeds k then 
      an IllgalArgumentException is thrown. **/
      if (k > uniqueValues) {
         throw new IllegalArgumentException();
      }
      return kmin;
   }


    /**
     * Selects the kth maximum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth maximum value. Note that there is no kth
     * maximum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     *
     * @param a The array being searched.
     * @param k The kth value being searched for.
     * @return The kth maximum value in the array. 
     * @throws IllegalArgumentException if array is null, has 
     * length zero, or for specific values of k.  
     */
   public static int kmax(int[] a, int k) {
   /** The same as the kmin method but it works right to left in the
   array instead of left to right. **/
      /** Illegal Exceptions. **/
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      if (k < 1 || k > a.length) {
         throw new IllegalArgumentException();
      }
      
      /** uniqueValues is to count the distinct numbers
      so as not to include the duplicates in the count. **/
      int uniqueValues = 1;
      int kmax = 0;
      
      /** Since int[] a cannot be changed, a copy is made 
      and then sorted to make loops easier. **/
      int[] copyA = new int[a.length];
      for (int i = 0; i < a.length; i++) {
         copyA[i] = a[i];
      }
      Arrays.sort(copyA);
      
      /** holder is just used in the loop to compare values. **/
      int holder = copyA[copyA.length - 1];
      
      /** If k is one then kmax is simply the max. **/
      if (k == 1) {
         kmax = copyA[copyA.length - 1];
      }
      
      /** Runs backwards through the sorted array. Checks two values  
      to see if they are the same. If they are not then 
      the number of unique values is incremented. This proceeds
      until the kmax unique value is found. **/
      for (int i = copyA.length - 1; i >= 0; i--) {
         if (copyA[i] != holder) {
            uniqueValues++;
            if (uniqueValues == k) {
               kmax = copyA[i];
            }
         }
         holder = copyA[i];
      }
      
      /** After the array has been through the loop,
      if the number of unique values exceeds k then 
      an IllgalArgumentException is thrown. **/
      if (k > uniqueValues) {
         throw new IllegalArgumentException();
      }
      return kmax;
   
   }


    /**
     * Returns an array containing all the values in a in the
     * range [low..high]; that is, all the values that are greater
     * than or equal to low and less than or equal to high,
     * including duplicate values. The length of the returned array
     * is the same as the number of values in the range [low..high].
     * If there are no qualifying values, this method returns a
     * zero-length array. Note that low and high do not have
     * to be actual values in a. This method throws an
     * IllegalArgumentException if a is null or has zero length.
     * The array a is not changed by this method.
     *
     * @param a The array being operated on. 
     * @param low The lowest value in the range. 
     * @param high The highest value in the range. 
     * @return An array with the range of a or zero if there is
     * not a range.
     * @throws IllegalArgumentException if array is null or has 
     * length zero.
     */
   public static int[] range(int[] a, int low, int high) {
      /** Illegal Exceptions. **/
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      
      /** Since int[] a cannot be changed, a copy is made 
      to make loops easier. **/
      int[] copyA = new int[a.length];
      for (int i = 0; i < a.length; i++) {
         copyA[i] = a[i];
      }
      
      /** Uses a loop to put values within the range to 
      the front of the copyA array. **/
      int count = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= low && a [i] <= high) {
            copyA[count] = copyA[i];
            count++;
         }
      }
      
      /** After sorting values in copyA, if there are
      no values that were in the range then a zero 
      array is returned. **/
      if (count == 0) {
         int[] zero = {};
         return zero;
      }
      
      /** Otherwise the values that were in the range
      of copyA are copied to a new array and returned. **/
      else {
         int[] rangeA = new int[count];
         for (int i = 0; i < rangeA.length; i++) {
            rangeA[i] = copyA[i];
         }
         return rangeA;
      }
   }


    /**
     * Returns the smallest value in a that is greater than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     *
     * @param a The array being searched. 
     * @param key The restraint number.
     * @return The ceiling number.
     * @throws IllegalArgumentException if array is null, has 
     * length zero, or for specific key values.
     */
   public static int ceiling(int[] a, int key) {
      /** Illegal Exceptions. **/
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      if (a.length == 1 && a[0] >= key) {
         return a[0];
      }
      
      /** Since int[] a cannot be changed, a copy is made 
      and then sorted to make loops easier. **/
      int[] copyA = new int[a.length];
      for (int i = 0; i < a.length; i++) {
         copyA[i] = a[i];
      }
      Arrays.sort(copyA);
      
      /** Simple for-each loop to search through sorted array
      and return the first value that is greater than or equal 
      to key (which will be the smallest value that meets the 
      restraints since the array is sorted). **/
      for (int number : copyA) {
         if (number >= key) {
            return number;
         }
      }
      
      /** If nothing is returned then an Illegal Exception
      is thrown. **/
      throw new IllegalArgumentException();
   }


    /**
     * Returns the largest value in a that is less than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     *
     * @param a The array being searched.
     * @param key The restraint number. 
     * @return The floor number. 
     * @throws IllegalArgumentException if array is null, has 
     * length zero, or for specific key values. 
     */
   public static int floor(int[] a, int key) {
      /** Illegal Exceptions. **/
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      if (a.length == 1 && a[0] <= key) {
         return a[0];
      }
      
      /** Since int[] a cannot be changed, a copy is made 
      and then sorted to make loops easier. **/
      int[] copyA = Arrays.copyOf(a, a.length);
      Arrays.sort(copyA);
      
      int number = 0;
      
      /** Simple loop to search through the sorted array backwards
      and return the first value that is less than or equal 
      to key (which will be the biggest value that meets the 
      restraints since the array is sorted). **/
      for (int i = copyA.length - 1; i >= 0; i--) {
         if (copyA[i] <= key) {
            number = copyA[i];
            return number;
         }
      }
      
      /** If nothing is returned then an Illegal Exception
      is thrown. **/
      throw new IllegalArgumentException();
   }

}