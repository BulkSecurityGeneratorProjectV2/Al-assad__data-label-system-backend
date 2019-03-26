package site.assad.datalabel.util;


import org.apache.commons.lang3.tuple.Pair;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    
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
    
    public static Pair<String, String> getMonAndDate(Date date){
        if (date == null) {
            return Pair.of("", "");
        }
        String[] parts = date.toString().split(" ");
        return Pair.of(parts[1], parts[2]);
    }
}
