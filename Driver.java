import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Runs the program.
 * 
 * @author Elijah Boulton
 * @version 2018-10-16
 * Project 3
 */

public class Driver
{

   public static void main(String[] args) throws FileNotFoundException, IOException {

      final int YEAR = 2018;
      final int MONTH = 8;
      final int DAY = 30;
      final int HOUR = 17;
      final int MINUTE = 45;

      final String directory = "data";

      MapData mapData = new MapData(YEAR, MONTH, DAY, HOUR, MINUTE, directory);

      mapData.parseFile();
      System.out.println(mapData);
      
   }

}
