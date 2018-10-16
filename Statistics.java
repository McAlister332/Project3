import java.util.GregorianCalendar;

/**
 * Holds a statistic as well as data about what type of statistic it is, when it was taken, and contains
 * methods to compare time stamps on statistics.
 * 
 * @author Elijah Boulton
 * @version 2018-10-16
 * Project 3
 */

public class Statistics extends Observation implements DateTimeComparable
{
   
   /**
    * Contains the format for date and time data in string form.
    */
   protected String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss z";
   
   /**
    * Holds the date and time for the statistic.
    */
   private GregorianCalendar utcDateTime;
   
   /**
    * Holds the number of stations that reported valid data for this statistic.
    */
   private int numberOfReportingStations;
   
   /**
    * Determines the type of statistic.
    */
   private StatsType statType;
   
   /**
    * Constructor tha takes date and time as a string parameter.
    * 
    * @param value The value of the statistic.
    * @param stid The station ID where the statistic was gathered.
    * @param timeDateStr A string of a specific format that holds date and time information.
    * @param numberOfValidStations The number of stations reporting valid information.
    * @param inStatType The type of statistic.
    */
   public Statistics(double value, String stid, String timeDateStr, int numberOfValidStations, StatsType inStatType)
   {
      
      super(value, stid);
      this.numberOfReportingStations = numberOfValidStations;
      this.statType = inStatType;
      this.utcDateTime = this.createDateFromString(timeDateStr);
      this.statType = inStatType;
      
   }
   
   /**
    * Constructor that takes date and time as a Gregorian calendar parameter.
    * 
    * @param value The value of the statistic.
    * @param stid The station ID where the statistic was gathered.
    * @param dateTime A calendar holding the date and time the statistic was gathered.
    * @param numberOfValidStations The number of stations reporting valid information.
    * @param inStatType The type of statistic.
    */
   public Statistics(double value, String stid, GregorianCalendar dateTime, int numberOfValidStations, StatsType inStatType)
   {
      
      super(value, stid);
      this.numberOfReportingStations = numberOfValidStations;
      this.statType = inStatType;
      this.utcDateTime = dateTime;
      this.statType = inStatType;
      
   }
   
   /**
    * Returns a Gregorian calendar with the date and time that the data on which
    * the statistic is based was gathered, parsed from a string.
    * 
    * @param dateTimeStr String holding date and time information.
    * @return GregorianCalendar parsed from dateTimeStr
    */
   public GregorianCalendar createDateFromString(String dateTimeStr)
   {
      
      int year;
      int month;
      int day;
      int hour;
      int minute;
      int second;
      
      String[] temp = dateTimeStr.split("'T'");
      
      String[] date = temp[0].split("-");
      year = Integer.parseInt(date[0]);
      month = Integer.parseInt(date[1]);
      day = Integer.parseInt(date[2]);
      
      String[] temp2 = temp[1].split(" ");
      String[] time = temp2[0].split(":");
      hour = Integer.parseInt(time[0]);
      minute = Integer.parseInt(time[1]);
      second = Integer.parseInt(time[2]);
      
      GregorianCalendar date1 = new GregorianCalendar(year, month, day, hour, minute, second);
      
      return date1;
   }
   
   /**
    * Returns a String holding the date and time information held in a Gregorian
    * calendar. 
    * 
    * @param calendar Calendar holding date and time information.
    * @return String constructed from GrecorianCalendar calendar
    */
   public String createStringFromDate(GregorianCalendar calendar)
   {
      return String.format("%04d-%02d-%02d'T'%02d:%02d:%02d z", calendar.get(1), calendar.get(2),
               calendar.get(5), calendar.get(11), calendar.get(12), calendar.get(13));
   }
   
   /**
    * Returns the number of stations that reported valid data.
    * 
    * @return int numberOfReportingStations
    */
   public int getNumberOfReportingStations()
   {
      return numberOfReportingStations;
   }
   
   /**
    * Returns the date and time the data that the statistic is based on was gathered
    * 
    * @return String formatted from GregorianCalendar utcDateTime
    */
   public String getUTCDateTimeString()
   {
      return this.createStringFromDate(utcDateTime);
   }
   
   /**
    * Returns the calendar for the date and time for the statistic.
    * 
    * @return GregorianCalendar utcDateTime
    */
   public GregorianCalendar getUTCCalendar()
   {
      return utcDateTime;
   }
   
   /**
    * Returns the type of statistic.
    * 
    * @return StatsType statType
    */
   public StatsType getStatType()
   {
      return statType;
   }
   
   @Override
   /**
    * Compares two gregorian calenders, returns true if the date and time of this statistic is 
    * newer than the parameter date, false if not.
    * 
    * @return boolean true if newer, false if not
    */
   public boolean newerThan(GregorianCalendar inDateTimeUTC) {

      return this.utcDateTime.compareTo(inDateTimeUTC) > 0;
      
   }

   @Override
   /**
    * Compares two gregorian calenders, returns true if the date and time of this statistic is 
    * older than the parameter date, false if not.
    * 
    * @return boolean true if older, false if not
    */
   public boolean olderThan(GregorianCalendar inDateTimeUTC) {

      return this.utcDateTime.compareTo(inDateTimeUTC) < 0;
      
   }

   @Override
   /**
    * Compares two gregorian calenders, returns true if the date and time of this statistic is 
    * equal to that of the the parameter date, false if not.
    * 
    * @return boolean true if equal, false if not
    */
   public boolean sameAs(GregorianCalendar inDateTimeUTC) {

      return this.utcDateTime.compareTo(inDateTimeUTC) == 0;
      
   }
   
   /**
    * Returns a String formatted to represent the data from the statistic.
    * 
    * @return String containing All of the relevant information about the statistic.
    */
   public String toString()
   {
      return super.toString() + String.format("Statistic Type: %s\nNumber of Reporting Stations: %d\nDate and time, formatted (%s):\n     %s\n",
               this.statType.toString(), this.numberOfReportingStations, this.DATE_TIME_FORMAT, this.getUTCDateTimeString());
   }
   
}
