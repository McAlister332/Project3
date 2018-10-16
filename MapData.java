import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Parses a file, holds weather data, and calculates statistics
 * based upon that data.
 * 
 * @author Elijah Boulton
 * @version 2018-10-16
 * Project 3
 */

public class MapData
{
   
   /**
    * Data for solar radiation.
    */
   public ArrayList<Observation> sradData;
   
   /**
    * Data for air temperature at 1.5 meters.
    */
   public ArrayList<Observation> tairData;
   
   /**
    * Data for air temperature at 9 meters.
    */
   public ArrayList<Observation> ta9mData;
   
   /**
    * Number of observations that can be invalid and still maintain valid statistics.
    */
   private int NUMBER_OF_MISSING_OBSERVATIONS = 10;
   
   /**
    * Marker that represents temperature at 9 meters in the data.
    */
   private String TA9M = "TA9M";
   
   /**
    * Marker that represents temperature at 1.5 meters in the data.
    */
   private String TAIR = "TAIR";
   
   /**
    * Marker that represents solar radiation in the data.
    */
   private String SRAD = "SRAD";
   
   /**
    * Marker that represents station ID in the data.
    */
   private String STID = "STID";
   
   /**
    * Holds the position of station ID in the data.
    */
   private int stid = -1;
   
   /**
    * Holds the position of temperature at 1.5 meters in the data.
    */
   private int tair = -1;
   
   /**
    * Holds the position of temperature at 9 meters in the data.
    */
   private int ta9m = -1;
   
   /**
    * Holds the position of solar radiation in the data.
    */
   private int srad = -1;
   
   /**
    * String holding the web site name Mesonet.
    */
   private String MESONET = "Mesonet";
   
   /**
    * Contains the directory where data is stored.
    */
   private String directory;
   
   /**
    * Holds the minimum air temperature at 1.5 meters.
    */
   private Statistics tairMin;
   
   /**
    * Holds the maximum air temperature at 1.5 meters.
    */
   private Statistics tairMax;
   
   /**
    * Holds the average air temperature at 1.5 meters.
    */
   private Statistics tairAverage;
   
   /**
    * Holds the minimum air temperature at 9 meters.
    */
   private Statistics ta9mMin;
   
   /**
    * Holds the maximum air temperature at 9 meters.
    */
   private Statistics ta9mMax;
   
   /**
    * Holds the average air temperature at 9 meters.
    */
   private Statistics ta9mAverage;
   
   /**
    * Holds the minimum solar radiation.
    */
   private Statistics sradMin;
   
   /**
    * Holds the maximum solar radiation.
    */
   private Statistics sradMax;
   
   /**
    * Holds the average solar radiation.
    */
   private Statistics sradAverage;
   
   /**
    * Holds the total solar radiation.
    */
   private Statistics sradTotal;
   
   /**
    * Contains the file name from which data is parsed.
    */
   private String fileName;
   
   /**
    * Contains the date and time when the data was taken.
    */
   private GregorianCalendar utcDateTime;
   
   /**
    * Constructor, initializes utcDateTime, fileName,
    * 
    * @param year Year the data was taken.
    * @param month Month the data was taken.
    * @param day Day the data was taken.
    * @param hour Hour the data was taken.
    * @param minute Minute the data was taken.
    * @param directory The file location for the data.
    */
   public MapData(int year, int month, int day, int hour, int minute, String directory)
   {
      
      utcDateTime = new GregorianCalendar(year, month, day, hour, minute);
      this.directory = directory;
      fileName = this.createFileName(year,  month, day, hour, minute, directory);
      sradData = new ArrayList<Observation>();
      tairData = new ArrayList<Observation>();
      ta9mData = new ArrayList<Observation>();
      
   }
   
   /**
    * Creates a file for a certain date and time.
    * 
    * @param year Year the data was taken.
    * @param month Month the data was taken.
    * @param day Day the data was taken.
    * @param hour Hour the data was taken.
    * @param minute Minute the data was taken.
    * @param directory The file location for the data.
    * 
    * @return fileName for a given date and time
    */
   public String createFileName(int year, int month, int day, int hour, int minute, String directory)
   {
      
      String str = this.directory;
      return String.format("%s/%04d%02d%02d%02d%02d.mdf", str, year, month, day, hour, minute);
   }
   
   /**
    * Determines what column the data for srad, ta9m, and tair are in
    * 
    * @param inParamStr The type of data the method searches for.
    * @throws FileNotFoundException In case of an incorrect directory.
    * @throws IOException In case of an improperly formatted file.
    */
   private void parseParamHeader(String inParamStr) throws FileNotFoundException, IOException
   {
      
      BufferedReader br = new BufferedReader(new FileReader(inParamStr));
      
      br.readLine();
      br.readLine();
      String searchLine = br.readLine();
      
      String[] temp = searchLine.split(" ");
      ArrayList<String> datatype = new ArrayList<String>();
      for (int i = 0; i < temp.length; ++i)
      {
         
         if (!temp[i].equals(""))
         {
            datatype.add(temp[i]);
         }
         
      }
      
      for (int i = 0; i < datatype.size(); ++i)
      {
         
         if(datatype.get(i).equals(SRAD))
         {
            srad = i;
         }
         else if(datatype.get(i).equals(TA9M))
         {
            ta9m = i;
         }
         else if(datatype.get(i).equals(TAIR))
         {
            tair = i;
         }
         else if(datatype.get(i).equals(STID))
         {
            stid = i;
         }
         
      }
      
      br.close();
      
   }
   
   /**
    * Takes input from the file located at fileName, runs through the data and sorts it into
    * ArrayLists sradData, tairData, and ta9mData, then calls calculate Statistics for each
    * average, minimum, maximum, and total (srad only).
    * 
    * @throws FileNotFoundException In case of an incorrect directory.
    * @throws IOException In case of an improperly formatted file.
    */
   public void parseFile() throws FileNotFoundException, IOException
   {
      
      this.parseParamHeader(fileName);
      
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      ArrayList<String> str = new ArrayList<>();
      String temp;
      
      //puts each line of data into an arraylist
      while((temp = br.readLine()) != null)
      {
         str.add(temp);
      }
      br.close();
      
      str.remove(0);
      str.remove(0);
      str.remove(0);
      int numberOfStations = str.size() - 1;
      
      ArrayList<String[]> temp2 = new ArrayList<>();
      
      //splits each line into strings and adds them an arraylist
      for (int i = 0; i < str.size(); ++i)
      {
         temp2.add(str.get(i).split(" "));
      }
      
      //removes blank elements and puts results into a final arraylist
      ArrayList<String> temp3 = new ArrayList<>();
      for (int i = 0; i < temp2.size(); ++i)
      {
         for(int c = 0; c < temp2.get(i).length; ++c)
         {
            if (!temp2.get(i)[c].equals(""))
            {
               temp3.add(temp2.get(i)[c]);
            }
            
         }
         
      }
      
      
      //sorts data into the three arrays initialized in the constructor
      for (int i = 0; i < numberOfStations; ++i)
      {
         
         sradData.add(new Observation(Double.parseDouble(temp3.get(srad)), temp3.get(stid)));
         //System.out.println(sradData[i].toString());
         tairData.add(new Observation(Double.parseDouble(temp3.get(tair)), temp3.get(stid)));
         //System.out.println(tairData[i].toString());
         ta9mData.add(new Observation(Double.parseDouble(temp3.get(ta9m)), temp3.get(stid)));
         //System.out.println(ta9mData[i].toString());
         
         for (int c = 0; c < 24; ++c)
         {
            temp3.remove(0);
         }
         
      }
      
      for (int c = 0; c < 24; ++c)
      {
         if (temp3.size() > 0)
         {
            temp3.remove(0);
         }
            
      }
      
      calculateStatistics(sradData, SRAD);
      calculateStatistics(tairData, TAIR);
      calculateStatistics(ta9mData, TA9M);
      
   }
   
   /**
    * Calculates statistics for a type of measurement (tair, ta9m, or srad).
    * 
    * @param inData An arraylist of Objects to be used in calculations.
    * @param paramId The type of statistic (srad, tair, or ta9m) to be handled.
    */
   private void calculateStatistics(ArrayList<Observation> inData, String paramId)
   {
      
      if (paramId.equals(SRAD))
      {
         double total = 0;
         int num = 0;
         double average = 0;
         double min = Integer.MAX_VALUE;
         int maxindx = -1;
         int minindx = -1;
         double max = Integer.MIN_VALUE;
         int badObvsCntr = 0;
         
         
         for (int i = 0; i < inData.size(); ++i)
         {
            
            double val = inData.get(i).getValue();
            
            if (inData.get(i).isValid())
            {
               
               total += val;
               ++num;
               
               if (val < min)
               {
                  min = val;
                  minindx = i;
                           
               }
               
               if (val > max)
               {
                  max = val;
                  maxindx = i;
               }
               
            }
            else
            {
               ++badObvsCntr;
            }
            
         }
         
         average = (total / num);
         
         if (badObvsCntr < NUMBER_OF_MISSING_OBSERVATIONS)
         {
            sradMin = new Statistics(min, sradData.get(minindx).getStid(), utcDateTime, (sradData.size() - badObvsCntr), StatsType.MINIMUM);
            sradMax = new Statistics(max, sradData.get(maxindx).getStid(), utcDateTime, (sradData.size() - badObvsCntr), StatsType.MAXIMUM);
            sradAverage = new Statistics(average, MESONET, utcDateTime, (sradData.size() - badObvsCntr), StatsType.AVERAGE);
            sradTotal = new Statistics(total, MESONET, utcDateTime, (sradData.size() - badObvsCntr), StatsType.TOTAL);
         }
         
         else
         {
            
            sradMin = new Statistics(0, "NULL", utcDateTime, (sradData.size() - badObvsCntr), StatsType.MINIMUM);
            sradMax = new Statistics(0, "NULL", utcDateTime, (sradData.size() - badObvsCntr), StatsType.MAXIMUM);
            sradAverage = new Statistics(0, "NULL", utcDateTime,  (sradData.size() - badObvsCntr), StatsType.AVERAGE);
            sradTotal = new Statistics(0, "NULL", utcDateTime, (sradData.size() - badObvsCntr), StatsType.TOTAL);
            
         }
         
      }
      
      else if (paramId.equals(TAIR))
      {
         
         double total = 0;
         int num = 0;
         double average = 0;
         double min = Integer.MAX_VALUE;
         int maxindx = -1;
         int minindx = -1;
         double max = Integer.MIN_VALUE;
         int badObvsCntr = 0;
         
         
         for (int i = 0; i < inData.size(); ++i)
         {
            
            double val = inData.get(i).getValue();
            
            if (inData.get(i).isValid())
            {
               
               total += val;
               ++num;
               
               if (val < min)
               {
                  min = val;
                  minindx = i;
                           
               }
               
               if (val > max)
               {
                  max = val;
                  maxindx = i;
               }
               
            }
            else
            {
               ++badObvsCntr;
            }
            
         }
         
         average = (total / num);
         
         if (badObvsCntr < NUMBER_OF_MISSING_OBSERVATIONS)
         {
            tairMin = new Statistics(min, inData.get(minindx).getStid(), utcDateTime, (inData.size() - badObvsCntr), StatsType.MINIMUM);
            tairMax = new Statistics(max, inData.get(maxindx).getStid(), utcDateTime, (inData.size() - badObvsCntr), StatsType.MAXIMUM);
            tairAverage = new Statistics(average, MESONET, utcDateTime, (inData.size() - badObvsCntr), StatsType.AVERAGE);
         }
         else
         {
            
            tairMin = new Statistics(0, "NULL", utcDateTime, (sradData.size() - badObvsCntr), StatsType.MINIMUM);
            tairMax = new Statistics(0, "NULL", utcDateTime, (sradData.size() - badObvsCntr), StatsType.MAXIMUM);
            tairAverage = new Statistics(0, "NULL", utcDateTime,  (sradData.size() - badObvsCntr), StatsType.AVERAGE);
            
         }
         
      }
      
      else if (paramId.equals(TA9M))
         
      {
         
         double total = 0;
         int num = 0;
         double average = 0;
         double min = Integer.MAX_VALUE;
         int maxindx = -1;
         int minindx = -1;
         double max = Integer.MIN_VALUE;
         int badObvsCntr = 0;
         
         
         for (int i = 0; i < inData.size(); ++i)
         {
            
            double val = inData.get(i).getValue();
            
            if (inData.get(i).isValid())
            {
               
               total += val;
               ++num;
               
               if (val < min)
               {
                  min = val;
                  minindx = i;
                           
               }
               
               if (val > max)
               {
                  max = val;
                  maxindx = i;
               }
               
            }
            else
            {
               ++badObvsCntr;
            }
            
         }
         
         average = (total / num);
         
         if (badObvsCntr < NUMBER_OF_MISSING_OBSERVATIONS)
         {
            ta9mMin = new Statistics(min, inData.get(minindx).getStid(), utcDateTime, (inData.size() - badObvsCntr), StatsType.MINIMUM);
            ta9mMax = new Statistics(max, inData.get(maxindx).getStid(), utcDateTime, (inData.size() - badObvsCntr), StatsType.MAXIMUM);
            ta9mAverage = new Statistics(average, MESONET, utcDateTime, (inData.size() - badObvsCntr), StatsType.AVERAGE);
         }
         else
         {
            
            ta9mMin = new Statistics(0, "NULL", utcDateTime, (sradData.size() - badObvsCntr), StatsType.MINIMUM);
            ta9mMax = new Statistics(0, "NULL", utcDateTime, (sradData.size() - badObvsCntr), StatsType.MAXIMUM);
            ta9mAverage = new Statistics(0, "NULL", utcDateTime,  (sradData.size() - badObvsCntr), StatsType.AVERAGE);
            
         }
         
      }
      
   }
   
   /**
    * Returns the average solar radiation.
    * 
    * @return Statistics sradAverage
    */
   public Observation getSradAverage()
   {
      return sradAverage;
   }
   
   /**
    * Returns the maximum solar radiation.
    * 
    * @return Statistics sradMax
    */
   public Observation getSradMax()
   {
      return sradMax;
   }
   
   /**
    * Returns the minimum solar radiation.
    * 
    * @return Statistics sradTotal
    */
   public Observation getSradMin()
   {
      return sradMin;
   }
   
   /**
    * Returns the total solar radiation.
    * 
    * @return Statistics sradTotal
    */
   public Observation getSradTotal()
   {
      return sradTotal;
   }
   
   /**
    * Returns the average air temperature at 9 meters.
    * 
    * @return Statistics ta9mAverage
    */
   public Observation getTa9mAverage()
   {
      return ta9mAverage;
   }
   
   /**
    * Returns the maximum air temperature at 9 meters.
    * 
    * @return Statistics ta9mMax
    */
   public Observation getTa9mMax()
   {
      return ta9mMax;
   }
   
   /**
    * Returns the minimum air temperature at 9 meters.
    * 
    * @return Statistics ta9mMin
    */
   public Observation getTa9mMin()
   {
      return ta9mMin;
   }
   
   /**
    * Returns the average air temperature at 1.5 meters.
    * 
    * @return Statistics tairAverage
    */
   public Observation getTairAverage()
   {
      return tairAverage;
   }
   
   /**
    * Returns the maximum air temperature at 1.5 meters.
    * 
    * @return Statistics tairMax
    */
   public Observation getTairMax()
   {
      return tairMax;
   }
   
   /**
    * Returns the minimum air temperature at 1.5 meters.
    * 
    * @return Statistics tairMin
    */
   public Observation getTairMin()
   {
      return tairMin;
   }
   
   /**
    * Creates a string to represent the data from the file.
    * 
    * @return String containing averages, minimums, and maximums, and where they occurred
    */
   public String toString()
   {
      
      String str = String.format("=========================================================\n"
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
         this.utcDateTime.get(1), this.utcDateTime.get(2), this.utcDateTime.get(5),
         this.tairMax.getValue(), this.tairMax.getStid(),
         this.tairMin.getValue(), this.tairMin.getStid(),
         this.tairAverage.getValue(), this.tairAverage.getStid(),
         this.ta9mMax.getValue(), this.ta9mMax.getStid(),
         this.ta9mMin.getValue(), this.ta9mMin.getStid(),
         this.ta9mAverage.getValue(), this.ta9mAverage.getStid(),
         this.sradMax.getValue(), this.sradMax.getStid(),
         this.sradMin.getValue(), this.sradMin.getStid(),
         this.sradAverage.getValue(), this.sradAverage.getStid());
         
      return str;
      
   }
   
}
