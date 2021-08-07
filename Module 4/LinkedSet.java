import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author Mary Mitchell (mem0250@auburn.edu)
 *
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

   //////////////////////////////////////////////////////////
   // Do not change the following three fields in any way. //
   //////////////////////////////////////////////////////////

   /** References to the first and last node of the list. */
   Node front;
   Node rear;

   /** The number of nodes in the list. */
   int size;

   /////////////////////////////////////////////////////////
   // Do not change the following constructor in any way. //
   /////////////////////////////////////////////////////////

   /**
    * Instantiates an empty LinkedSet.
    */
   public LinkedSet() {
      front = null;
      rear = null;
      size = 0;
   }


   //////////////////////////////////////////////////
   // Public interface and class-specific methods. //
   //////////////////////////////////////////////////

   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   /**
    * Return a string representation of this LinkedSet.
    *
    * @return a string representation of this LinkedSet
    */
   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }


   ///////////////////////////////////
   // DO NOT CHANGE THE SIZE METHOD //
   ///////////////////////////////////
   /**
    * Returns the current size of this collection.
    *
    * @return  the number of elements in this collection.
    */
   public int size() {
      return size;
   }

   //////////////////////////////////////
   // DO NOT CHANGE THE ISEMPTY METHOD //
   //////////////////////////////////////
   /**
    * Tests to see if this collection is empty.
    *
    * @return  true if this collection contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return (size == 0);
   }


   /**
    * Ensures the collection contains the specified element. Neither duplicate
    * nor null values are allowed. This method ensures that the elements in the
    * linked list are maintained in ascending natural order.
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
   public boolean add(T element) {
      Node e = new Node(element);
      Node f = front;
      if (element == null || this.contains(element)) {
         return false;
      }
      /** "Special" Cases*/
      else if (size() == 0) {
         front = e;
         rear = front;
         rear.next = null;
         size++;
         return true;
      }
      else if (size == 1 && front.element.compareTo(element) > 0) {
         f.prev = e;
         front = f.prev;
         front.next = f;
         f.prev = front;
         rear = front.next;
         size++;
         return true;
      }
      else if (size == 1 && front.element.compareTo(element) < 0) {
         front.next = e;
         rear = front.next;
         rear.prev = front;
         size++;
         return true;
      } 
      /** "Normal" Case */ 
      else {
         Node p = addComparison(element);
         p.next.prev = e;
         e.next = p.next;
         p.next = e ;
         e.prev = p;
         size++;
         return true;
      }
      
   }
   
   
   /**
    * Ensures the collection does not contain the specified element.
    * If the specified element is present, this method removes it
    * from the collection. This method, consistent with add, ensures
    * that the elements in the linked lists are maintained in ascending
    * natural order.
    *
    * @param   element  The element to be removed.
    * @return  true if collection is changed, false otherwise.
    */
   public boolean remove(T element) {
      Node f = front;
      if (element == null) {
         throw new NullPointerException();
      }
      else if (isEmpty()) {
         return false;
      }
      while (f != null) {
         if (f.element.compareTo(element) == 0) {
            if (f == front) {
               front = front.next;
               front.prev = null;
               size--;
               return true;
            }
            else if (f == rear) {
               rear = rear.prev;
               rear.next = null;
               size--;
               return true;
            }
            else {
               f.prev.next = f.next;
               f.next.prev = f.prev;
               size--;
               return true;
            }
         }
         f = f.next;
      }
      return false;
   }


   /**
    * Searches for specified element in this collection.
    *
    * @param   element  The element whose presence in this collection is to be tested.
    * @return  true if this collection contains the specified element, false otherwise.
    */
   public boolean contains(T element) {
      if (isEmpty() || element == null) {
         return false;
      }
      Node f = front;
      boolean isFound = false;
      while (f != null && !isFound) {
         if (f.element.equals(element)) {
            isFound = true;
         }
         f = f.next;
      }
      return isFound;
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(Set<T> s) {
      if (s == null) {
         return false;
      }
      for (T element : s) {
         if (!contains(element)) {
            return false;
         }
      }
      for (T element : this) {
         if (!s.contains(element)) {
            return false;
         }
      }
      return true;
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(LinkedSet<T> s) {
      if (s == null || size != s.size) {
         return false;
      }
      Node f = front;
      Node n = s.front;
      while (f != null) {
         if (f.element.compareTo(n.element) != 0) {
            return false;
         }
         f = f.next;
         n = n.next;
      }
      return true;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(Set<T> s){
      if (s == null || s.isEmpty()) {
         return this;
      }
      if (isEmpty()) {
         return s;
      }
      LinkedSet<T> u = new LinkedSet<T>();
      for (T element : this) {
         u.add(element);
      }
      for (T element : s) {
         u.add(element);
      } 
      return u;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(LinkedSet<T> s){
      if (s == null) {
         throw new NullPointerException();
      }
      LinkedSet<T> u = new LinkedSet<T>();
      Node f = front;
      while (f != null) {
         u.add(f.element);
         f = f.next;
      }
      Iterator<T> itr = s.iterator();
      while (itr.hasNext()) {
         u.add(itr.next());
      }
      return u;
   }


   /**
    * Returns a set that is the intersection of this set and the parameter set.
    *
    * @return  a set that contains elements that are in both this set and the parameter set
    */
   public Set<T> intersection(Set<T> s) {
      if (s == null) {
         throw new NullPointerException();
      }
      LinkedSet<T> i = new LinkedSet<T>();
      Node f = front;
      while (f != null) {
         if (s.contains((T)f.element)) {
            i.add((T)f.element);
         }
         f = f.next;
      }
      return i;
   }

   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   public Set<T> intersection(LinkedSet<T> s) {
      if (s == null) {
         throw new NullPointerException();
      }
      LinkedSet<T> i = new LinkedSet<T>();
      Node p1 = this.front;
      Node p2 = s.front;
      while (p1 != null && p2 != null) {
         if (p1.element.compareTo(p2.element) == 0) {
            i.add((T)p1.element);
            p1 = p1.next;
            p2 = p2.next;
         }
         else if (p1.element.compareTo(p2.element) > 0) {
            p2 = p2.next;
         }
         else if (p1.element.compareTo(p2.element) < 0) {
            p1 = p1.next;
         }
      }
      return i;
   }


   /**
    * Returns a set that is the complement of this set and the parameter set.
    *
    * @return  a set that contains elements that are in this set but not the parameter set
    */
   public Set<T> complement(Set<T> s) {
      if (s == null) {
         throw new NullPointerException();
      }
      LinkedSet<T> c = new LinkedSet<T>();
      Node f = front;
      while (f != null) {
         if (!s.contains((T)f.element)) {
            c.add((T)f.element);
         }
         f = f.next;
      }
      return c;
   }


   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   public Set<T> complement(LinkedSet<T> s) {
      if (s == null) {
         throw new NullPointerException();
      }
      LinkedSet<T> c = new LinkedSet<T>();
      Node f = front;
      while (f != null) {
         if (!s.contains((T)f.element)) {
            c.add((T)f.element);
         }
         f = f.next;
      }
      return c;
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in ascending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> iterator() {
      return new LinkedSetIterator();
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in descending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> descendingIterator() {
      return new SetDescendingIterator(rear);
   }


   /**
    * Returns an iterator over the members of the power set
    * of this LinkedSet. No specific order can be assumed.
    *
    * @return  an iterator over members of the power set
    */
   public Iterator<Set<T>> powerSetIterator() {
      return null;
   }



   //////////////////////////////
   // Private utility methods. //
   //////////////////////////////
   /** Loop method for comparisons within the add method. */
   private Node addComparison(T element) {
      Node e = front;
      while (e != null) {
         if (e.element.compareTo(element) > 0) {
            return e.prev;
         }
         e = e.next;
      }
      return e;
   }


   // Feel free to add as many private methods as you need.

   ////////////////////
   // Nested classes //
   ////////////////////
   /** Iterator Classes */
   private class LinkedSetIterator implements Iterator<T> {
      private Node f = front;
      public boolean hasNext() {
         return f != null;
      }
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         T n = f.element;
         f = f.next;
         return n;
      }
      
   } 
   private class SetDescendingIterator implements Iterator<T> {
      Node n;
      public SetDescendingIterator(Node rear) {
         n = rear;
      }
      public boolean hasNext() {
         boolean rValue = (n != null && n.element != null);
         return rValue;
      }
      public T next() {
         if (n != null) {
            T e = n.element;
            n = n.prev;
            return e;
         }
         return null;
      }
   }
   
   
   //////////////////////////////////////////////
   // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
   //////////////////////////////////////////////

   /**
    * Defines a node class for a doubly-linked list.
    */
   class Node {
      /** the value stored in this node. */
      T element;
      /** a reference to the node after this node. */
      Node next;
      /** a reference to the node before this node. */
      Node prev;
   
      /**
       * Instantiate an empty node.
       */
      public Node() {
         element = null;
         next = null;
         prev = null;
      }
   
      /**
       * Instantiate a node that containts element
       * and with no node before or after it.
       */
      public Node(T e) {
         element = e;
         next = null;
         prev = null;
      }
   }

}
