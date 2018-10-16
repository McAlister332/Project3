
/**
 * Holds a single Observation and determines if it is valid or not.
 * 
 * @author Elijah Boulton
 * @version 2018-10-16
 * Project 3
 */

public class Observation extends AbstractObservation
{
   
   /**
    * Value of the data for the observation.
    */
   private double value;
   
   /**
    * Station Id where the data for the observation was taken.
    */
   private String stid;
   
   /**
    * Constructor, initializes value and stid, then calls isValid.
    * 
    * @param value The value of the observation.
    * @param stid The ID of the station where the observation was taken.
    */
   public Observation(double value, String stid)
   {
      
      this.value = value;
      this.stid = stid;
      
      this.isValid();
      
   }
   
   /**
    * Returns the value held in the observation.
    * 
    * @return double value
    */
   public double getValue()
   {
      return this.value;
   }
   
   /**
    * Determines whether or not the observation is balid and sets the
    * boolean valid accordingly.
    * 
    * @return boolean valid
    */
   public boolean isValid()
   {
      
      if (this.value < -900)
      {
         this.valid = false;
      }
      else
      {
         this.valid = true;
      }
      
      return valid;
      
   }
   
   /**
    * Returns the Id of the station at which the observation was recorded.
    * 
    * @return String stid
    */
   public String getStid()
   {
      return this.stid;
   }
   
   /**
    * Returns a formatted String holding the data from the observation.
    * 
    * @return String formatted holding value, stid, and valid from Observation
    */
   public String toString()
   {
      return String.format("Station ID: %s   "
            + "Value: %.02f   Valid? %b\n", this.stid, this.value, this.valid);
   }

}
