import java.util.GregorianCalendar;

/**
 * Interface holding a template for a way to compare date and time.
 * 
 * @author Elijah Boulton
 * @version 2018-10-16
 * Project 3
 */

public interface DateTimeComparable
{
   
   /**
    * True if the parameter date is older, false if newer.
    * 
    * @param inDateTimeUTC The date and time to be compared to.
    * @return boolean value determining relative time scale.
    */
   boolean newerThan(GregorianCalendar inDateTimeUTC);
   
   /**
    * True if the parameter date is newer, false if older.
    * 
    * @param inDateTimeUTC the date and time to be compared to.
    * @return boolean value determining relative time scale.
    */
   boolean olderThan(GregorianCalendar inDateTimeUTC);
   
   /**
    * True if the parameter date the same, false if different.
    * 
    * @param inDateTimeUTC The date and time to be compared to.
    * @return boolean value determining relative time scale.
    */
   boolean sameAs(GregorianCalendar inDateTimeUTC);

}
