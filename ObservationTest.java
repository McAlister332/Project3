import org.junit.Test;
import org.junit.Assert;

/**
 * Tests the Observation class.
 * 
 * @author Elijah Boulton
 * @version 2018-10-16
 * Project 3
 */

public class ObservationTest
{

   @Test
   /**
    * Tests the Observation classs constructor.
    */
   public void testObservation() {
      
      Observation test = new Observation(67.0, "MIAM");
      
      Assert.assertTrue(test.getStid().equals("MIAM"));
      Assert.assertEquals(test.getValue(), 67.0, .1);
      
   }
   
   @Test
   /**
    * Tests the isValid method.
    */
   public void testIsValid() {
      
      Observation test1 = new Observation(897.0, "MIAM");
      Observation test2 = new Observation(-990, "NRMN");
      
      Assert.assertTrue(test1.valid);
      Assert.assertFalse(test2.valid);
      
   }
   
   @Test
   /**
    * Tests the toString method.
    */
   public void testToString() {

      Observation test1 = new Observation(43, "MIAM");
      
      Assert.assertTrue(String.format("Station ID: %s   "
            + "Value: %.02f   Valid? %b\n", "MIAM", 43.0, true).equals(test1.toString()));
      
   }

}
