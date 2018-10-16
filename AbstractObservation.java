/**
 * Holds general data for the Observation class.
 * 
 * @author Elijah Boulton
 * @version 2018-10-16
 * Project 3
 */

public abstract class AbstractObservation
{

   /**
    * Whether or not an observation contains valid data.
    */
   public boolean valid;
   
   /**
    * Constructor for the class.
    */
   public AbstractObservation()
   {
      /*
       * there is nothing to construct and no values to assign without the rest
       * of the observation class.
       */
   }
   
   /**
    * Determines whether or not an observation is valid.
    * 
    * @return boolean valid
    */
   public boolean isValid()
   {
      
      return valid;
      
   }
   
}
