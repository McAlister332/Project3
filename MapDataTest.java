import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the MapData class.
 * 
 * @author Elijah Boulton
 * @version 2018-10-16
 * Project 3
 */

public class MapDataTest
{
   
   @Test
   /**
    * Tests constructors, parseFile and other associated methods.
    * 
    * @throws FileNotFoundException
    * @throws IOException
    */
   public void testParseFile() throws FileNotFoundException, IOException {
      MapData test = new MapData (2017, 9, 25, 17, 45, "data");
      
      test.parseFile();
      
      Assert.assertEquals(test.getTairAverage().getValue(), 33.5, .1);
      Assert.assertEquals(test.getTairMax().getValue(), 35.3, .1);
      Assert.assertEquals(test.getTairMin().getValue(), 31.4, .1);
      
      Assert.assertEquals(test.getTa9mAverage().getValue(), 32.4, .1);
      Assert.assertEquals(test.getTa9mMax().getValue(), 33.9, .1);
      Assert.assertEquals(test.getTa9mMin().getValue(), 30.3, .1);
      
      Assert.assertEquals(test.getSradAverage().getValue(), 833.4, .1);
      Assert.assertEquals(test.getSradMax().getValue(), 899.0, .1);
      Assert.assertEquals(test.getSradMin().getValue(), 639.0, .1);
      Assert.assertEquals(test.getSradTotal().getValue(), 8334.0, .1);
      
   }
   
   @Test
   /**
    * Tests toString method.
    * 
    * @throws FileNotFoundException
    * @throws IOException
    */
   public void testToString() throws FileNotFoundException, IOException {
      
      MapData test = new MapData (2017, 9, 25, 17, 45, "data");
      
      test.parseFile();
      
      Assert.assertTrue(test.toString().equals(String.format("=========================================================\n"
               + "=== %04d-%02d-%02d ===\n"
               + "=========================================================\n"
               + "Maximum Air Temperature(1.5m) = %.1f C at %s\n"
               + "Minimum Air Temperature(1.5m) = %.1f C at %s\n"
               + "Average Air Temperature(1.5m) = %.1f C at %s\n"
               + "=========================================================\n"
               + "=========================================================\n"
               + "Maximum Air Temperature(9.0m) = %.1f C at %s\n"
               + "Minimum Air Temperature(9.0m) = %.1f C at %s\n"
               + "Average Air Temperature(9.0m) = %.1f C at %s\n"
               + "=========================================================\n"
               + "=========================================================\n"
               + "Maximum Solar Radiation = %.1f W/M^2 at %s\n"
               + "Minimum Solar Radiation = %.1f W/M^2 at %s\n"
               + "Average Solar Radiation = %.1f W/M^2 at %s\n"
               + "=========================================================\n",
               2017, 9, 25, 35.3, "BESS", 31.4, "ANT2", 33.5, "Mesonet", 33.9, "BEAV",
               30.3, "ANT2", 32.4, "Mesonet", 899.0, "BESS", 639.0, "ALV2",  833.4,
               "Mesonet")));
      
   }

}
