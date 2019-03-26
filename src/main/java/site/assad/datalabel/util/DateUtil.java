package site.assad.datalabel.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    
    public final static String DATE_TIME_PATTERN = "yyyy-mm-dd HH:mm:ss";
    public final static String DATE_PATTERN = "yyyy-mm-dd";
    
    public static String formatToDateTime(Date date){
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DATE_TIME_PATTERN).format(date);
    }
    public static String formatToDate(Date date){
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DATE_PATTERN).format(date);
    }
}
