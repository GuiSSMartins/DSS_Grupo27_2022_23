package business.subPartidas;


import java.util.Date;
import java.text.SimpleDateFormat;

public class TimeConverter
{
public static String toTimeFormat(long t)
{   
    return (new SimpleDateFormat("mm:ss,SSS")).format(new Date(t));
}
}
