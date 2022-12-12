
/**
 * Write a description of class TimeConverter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Date;
import java.text.SimpleDateFormat;

public class TimeConverter
{
   public static String toTimeFormat(long t)
   {   
      return (new SimpleDateFormat("mm:ss,SSS")).format(new Date(t));
   }
}
