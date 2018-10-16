import java.util.GregorianCalendar;
import org.junit.Test;
import org.junit.Assert;

/**
 * Tests the Statistics class.
 * 
 * @author Elijah Boulton
 * @version 2018-10-16
 * Project 3
 */

public class StatisticsTest
{

   @Test
   /**
    * Tests both Statistics constructors by comparing the UTCTimeDateString they
    * produce when constructed using the same time, in different formats.
    */
   public void testStatistics() {
      
      GregorianCalendar test = new GregorianCalendar(2017, 8, 30, 17, 45, 0);
      
      Statistics test1 = new Statistics(33.0, "MIAM", "2017-08-30'T'17:45:00 z", 9, StatsType.AVERAGE);
      Statistics test2 = new Statistics(33.0, "MIAM", test, 9, StatsType.AVERAGE);
      
      Assert.assertEquals(9, test1.getNumberOfReportingStations());
      Assert.assertEquals(StatsType.AVERAGE, test2.getStatType());
      Assert.assertTrue(test1.getUTCDateTimeString().equals(test2.getUTCDateTimeString()));
      
   }
   
   @Test
   /**
    * Compares a manually created calendar to one created by the Statistics class.
    */
   public void testCreateDateFromString() {

      GregorianCalendar test = new GregorianCalendar(2017, 8, 30, 18, 45, 0);
      
      Statistics test1 = new Statistics(33.0, "MIAM", "2017-08-30'T'18:45:00 z", 9, StatsType.MAXIMUM);
      
      Assert.assertEquals(StatsType.MAXIMUM, test1.getStatType());
      Assert.assertEquals(test1.getUTCCalendar(), test);
      
   }
   
   @Test
   /**
    * Compares a manually created date string with the Statistics class's return for
    * the same date and time.
    */
   public void testCreateStringFromDate() {

      String date = "2017-08-30'T'17:45:00 z";
      GregorianCalendar test = new GregorianCalendar(2017, 8, 30, 17, 45, 0);
      
      Statistics test2 = new Statistics(33.0, "MIAM", test, 9, StatsType.MINIMUM);
      
      Assert.assertEquals(StatsType.MINIMUM, test2.getStatType());
      Assert.assertTrue(test2.getUTCDateTimeString().equals(date));
      
   }
   
   @Test
   /**
    * Compares two Statistics to test the newerThan method.
    */
   public void testNewerThan() {

      
      GregorianCalendar test = new GregorianCalendar(2017, 8, 30, 17, 45, 0);
      
      Statistics test1 = new Statistics(33.0, "MIAM", "2018-08-30'T'17:45:00 z", 9, StatsType.AVERAGE);
      Statistics test2 = new Statistics(33.0, "MIAM", test, 9, StatsType.TOTAL);
      
      Assert.assertEquals(StatsType.TOTAL, test2.getStatType());
      
      Assert.assertTrue(test1.newerThan(test2.getUTCCalendar()));
      Assert.assertFalse(test2.newerThan(test1.getUTCCalendar()));
      
   }
   
   @Test
   /**
    * Compares two statistics to test the olderThan method.
    */
   public void testOlderThan() {

      GregorianCalendar test = new GregorianCalendar(2017, 8, 30, 17, 45, 0);
      
      Statistics test1 = new Statistics(33.0, "MIAM", "2018-08-30'T'17:45:00 z", 9, StatsType.AVERAGE);
      Statistics test2 = new Statistics(33.0, "MIAM", test, 9, StatsType.AVERAGE);
      
      Assert.assertTrue(test2.olderThan(test1.getUTCCalendar()));
      Assert.assertFalse(test1.olderThan(test2.getUTCCalendar()));
      
   }
   
   @Test
   /**
    * Compares three statistics to test the sameAs method.
    */
   public void testSameAs() {

      GregorianCalendar test = new GregorianCalendar(2017, 8, 30, 17, 45, 0);
      GregorianCalendar testc = new GregorianCalendar(2017, 8, 30, 17, 45, 0);
      
      Statistics test1 = new Statistics(33.0, "MIAM", "2018-08-30'T'17:45:00 z", 9, StatsType.AVERAGE);
      Statistics test2 = new Statistics(33.0, "MIAM", test, 9, StatsType.AVERAGE);
      Statistics test3 = new Statistics(33.0, "MIAM", testc, 9, StatsType.AVERAGE);
      
      Assert.assertTrue(test2.sameAs(test3.getUTCCalendar()));
      Assert.assertFalse(test1.sameAs(test3.getUTCCalendar()));
      
   }
   
   @Test
   /**
    * Tests the toString method.
    */
   public void testToString() {
      
      Statistics test1 = new Statistics(33.0, "MIAM", "2017-08-30'T'17:45:00 z", 9, StatsType.AVERAGE);
      
      String str = String.format("Station ID: %s   " + "Value: %.02f   Valid? %b\n", "MIAM", 33.0, true) +
               String.format("Statistic Type: %s\nNumber of Reporting Stations: %d\nDate and time, formatted (%s):\n     %s\n",
               StatsType.AVERAGE.toString(), 9, "yyyy-MM-dd'T'HH:mm:ss z", "2017-08-30'T'17:45:00 z");
      
      
      Assert.assertTrue(test1.toString().equals(str));
   }

}
