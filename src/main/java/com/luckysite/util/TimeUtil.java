package com.luckysite.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String transFormDate(Date date){
        return format.format(date);
    }
}
