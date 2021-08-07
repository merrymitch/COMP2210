import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Mary Mitchell (mem0250@auburn.edu)
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
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      /** Illegal Exceptions. **/
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      /** Creates an iterator for the loop and initializes minimum value
      for comparison. **/
      Iterator<T> iterate = coll.iterator();
      T minimum = iterate.next();
      /** Goes through the collection and uses comparator
      to find minimum. **/
      if (iterate.hasNext()) {
         for (T value : coll) {
            if (comp.compare(value, minimum) < 0) {
               minimum = value;
            }
         }
      }
      return minimum;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      /** Illegal Exceptions. **/
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      /** Creates an iterator for the loop and initializes maximum value
      for comparison. **/
      Iterator<T> iterate = coll.iterator();
      T maximum = iterate.next();
      /** Loops through the collection and uses comparator
      to find maximum. **/
      if (iterate.hasNext()) {
         for (T value : coll) {
            if (comp.compare(value, maximum) > 0) {
               maximum = value;
            }
         }
      }
      return maximum;
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
      /** Illegal Exceptions. **/
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      } 
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      if (k <= 0) {
         throw new NoSuchElementException();
      }
      /** Creates copy of collection and sorts the arraylist. **/
      ArrayList<T> copy = new ArrayList<T>(coll);
      java.util.Collections.sort(copy, comp);
      /** Exception for k. **/
      if (k > copy.size()) {
         throw new NoSuchElementException();
      }
      /** Trivial solution. **/
      if (k == 1) {
         return copy.get(0);
      }
      /** Variables are initialized for comparison. **/
      T kmin = null;
      int uniqueValues = 1;
      T holder = copy.get(0);
      /** Loop to run through the arraylist and determine how many 
      unique values there are. If the unique values match k then the kmin
      is set. **/
      for (int i = 1; i < copy.size(); i++) {
         if (copy.get(i) != holder) {
            uniqueValues++;
            if (k == uniqueValues) {
               kmin = copy.get(i);
            }
         }
         holder = copy.get(i);
      }
      /** If k is greater than the amount of unique values
      exception is thrown. **/
      if (k > uniqueValues) {
         throw new NoSuchElementException();
      }
      return kmin;
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      /** Illegal Exceptions. **/
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      } 
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      if (k <= 0) {
         throw new NoSuchElementException();
      }
      /** Creates copy of collection and sorts the arraylist. **/
      ArrayList<T> copy = new ArrayList<T>(coll);
      java.util.Collections.sort(copy, comp);
      /** Exception for k. **/
      if (k > copy.size()) {
         throw new NoSuchElementException();
      }
      /** Trivial Solution. **/
      if (k == 1) {
         return copy.get(copy.size() - 1);
      }
      /** Variables are initialized for comparison. **/
      T kmax = null;
      int uniqueValues = 1;
      T holder = copy.get(copy.size() - 1);
      /** Loop to run through the arraylist and determine how many 
      unique values there are. If the unique values match k then the kmax
      is set. **/
      for (int i = copy.size() - 2; i >= 0; i--) {
         if (copy.get(i) != holder) {
            uniqueValues++;
            if (k == uniqueValues) {
               kmax = copy.get(i);
            }
         }
         holder = copy.get(i);
      }
      /** If k is greater than the amount of unique values
      exception is thrown. **/
      if (k > uniqueValues) {
         throw new NoSuchElementException();
      }
      return kmax;
   
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                                     Comparator<T> comp) {
      /** Illegal Exceptions. **/
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      } 
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      /** Makes a new list for the values in the range. **/
      ArrayList<T> rangeList = new ArrayList<T>();
      /** Loop to add values in the range to the arraylist. **/
      for (T value : coll) {
         if (comp.compare(value, low) >= 0 && comp.compare(value, high) <= 0) {
            rangeList.add(value);
         }
      }
      /** If no values get added an exception is thrown. **/
      if (rangeList.isEmpty()) {
         throw new NoSuchElementException();
      }
      return rangeList;
   
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      /** Illegal Exceptions. **/
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      } 
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      /** Creates iterator for loops and initiates ceiling. **/
      Iterator<T> iterate = coll.iterator();
      T ceiling = iterate.next();
      /** First sets the ceiling to the maximum in the collection. **/
      if (iterate.hasNext()) {
         for (T value : coll) {
            if (comp.compare(value, ceiling) > 0) {
               ceiling = value;
            }
         }
      }
      /** Test variable for exceptions after loop. **/
      int test = 0;
      /** Loops through the collection and determines the values that are 
      greater or equal to key and less than or equal to the current ceiling. **/
      for (T value : coll) {
         if ((comp.compare(value, key) >= 0) && (comp.compare(value, ceiling) <= 0)) {
            test++;
            ceiling = value;
         }
      }
      /** If there are no values in the array that meet critera, throw
      exception. **/
      if (test == 0) {
         throw new NoSuchElementException();
      }
      return ceiling;      
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      /** Illegal Exceptions. **/
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      } 
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      /** Creates iterator for loops and initiates ceiling. **/
      Iterator<T> iterate = coll.iterator();
      T floor = iterate.next();
      /** First sets the floor to the minimum in the collection. **/
      if (iterate.hasNext()) {
         for (T value : coll) {
            if (comp.compare(value, floor) < 0) {
               floor = value;
            }
         }
      }
      /** Test variable for exceptions after loop. **/
      int test = 0;
      /** Loops through the collection and determines the values that are 
      less or equal to key and greater than or equal to the current floor. **/
      for (T value : coll) {
         if ((comp.compare(value, key) <= 0) && (comp.compare(value, floor) >= 0)) {
            test++;
            floor = value;
         }
      }
      /** If there are no values in the array that meet critera, throw
      exception. **/
      if (test == 0) {
         throw new NoSuchElementException();
      }
      return floor;
   }
}
