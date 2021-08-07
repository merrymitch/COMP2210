import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class MinOfThreeTest {
   
   /** Test method 1 with 4, 2, 1. **/
   @Test 
   public void firstTestMin1() {
      int expected = 1;
      int actual = MinOfThree.min1(4, 2, 1);
      assertEquals(expected, actual);
   }
   
   /** Test method 1 with 1, 2, 3. **/
   @Test 
   public void secondTestMin1() {
      int expected = 1;
      int actual = MinOfThree.min1(1, 2, 3);
      assertEquals(expected, actual);
   }
   
   /** Test method 1 with 2, 3, 1. **/
   @Test 
   public void thirdTestMin1() {
      int expected = 1;
      int actual = MinOfThree.min1(2, 3, 1);
      assertEquals(expected, actual);
   }
   
   /** Test method 1 with 2, 2, 4. **/
   @Test 
   public void fourthTestMin1() {
      int expected = 2;
      int actual = MinOfThree.min1(2, 2, 4);
      assertEquals(expected, actual);
   }


}
