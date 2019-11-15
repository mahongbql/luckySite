package com.luckysite.util;

import com.luckysite.common.enums.WeekDaysEnum;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.apache.commons.lang3.time.DateUtils.isSameDay;

@Slf4j
public class TimeUtil {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_MMDD = "MM-dd";
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String DATE_LONG_FORMAT = "EEE Dec d HH:mm:ss CST yyyy";
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_LONG_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private static Calendar CALENDAR = Calendar.getInstance();

    public static SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);

    public static SimpleDateFormat getDateFormat(String format) {
        return new SimpleDateFormat(format);
    }

    public static String getCurrentDate() {
        return getDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
    }

    public static Date getNow() {
        return new Date();
    }

    public static String transFormDate(Date date){
        return format.format(date);
    }

    public static String getCurrentDate(String format) {
        return getDateFormat(format).format(System.currentTimeMillis());
    }

    public static String getCurrentTime() {
        return getDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
    }

    public static String getCurrentTime(String format) {
        return getDateFormat(format).format(System.currentTimeMillis());
    }

    public static String dateToString(Object object) {
        return getDateFormat("yyyy-MM-dd").format(object);
    }

    public static String dateToString(Object object, String format) {
        return getDateFormat(format).format(object);
    }

    public static String dateToString(Date date) {
        return getDateFormat("yyyy-MM-dd").format(date);
    }

    public static int dateToyyyyMMddInt(Date date) {
        return Integer.parseInt(getDateFormat("yyyyMMdd").format(date));
    }

    public static String dateToString(Date date, String format) {
        return getDateFormat(format).format(date);
    }

    public static Date getDateByString(String value) {
        Date date = null;

        try {
            date = getDateFormat("yyyy-MM-dd").parse(value);
            return date;
        } catch (ParseException var3) {
            log.info("日期格式错误");
            throw new RuntimeException("日期格式错误");
        }
    }

    public static Date getDateByString(String value, String format) {
        Date date = null;

        try {
            date = getDateFormat(format).parse(value);
            return date;
        } catch (ParseException var4) {
            log.info("日期格式错误");
            throw new RuntimeException("日期格式错误");
        }
    }

    public static Date getDateTimeByString(String value) {
        Date date = null;

        try {
            date = getDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
            return date;
        } catch (ParseException var3) {
            log.info("日期格式错误");
            throw new RuntimeException("日期格式错误");
        }
    }

    public static Date endOfDay(Date date) {
        Calendar calendar = CALENDAR;
        synchronized(calendar) {
            calendar.setTime(date);
            calendar.set(11, 23);
            calendar.set(14, 999);
            calendar.set(13, 59);
            calendar.set(12, 59);
            return calendar.getTime();
        }
    }

    public static Date startOfDay(Date date) {
        Calendar calendar = CALENDAR;
        synchronized(calendar) {
            calendar.setTime(date);
            calendar.set(11, 0);
            calendar.set(14, 0);
            calendar.set(13, 0);
            calendar.set(12, 0);
            return calendar.getTime();
        }
    }

    public static Date startOfDayInMillis(Date date) {
        Calendar calendar = CALENDAR;
        synchronized(calendar) {
            calendar.setTime(date);
            calendar.set(11, 0);
            calendar.set(14, 0);
            calendar.set(13, 0);
            calendar.set(12, 0);
            return calendar.getTime();
        }
    }

    public static Date endOfDayInMillis(Date date) {
        Calendar calendar = CALENDAR;
        synchronized(calendar) {
            calendar.setTime(date);
            calendar.set(11, 23);
            calendar.set(14, 999);
            calendar.set(13, 59);
            calendar.set(12, 59);
            return calendar.getTime();
        }
    }

    public static Date nextDay(Date date) {
        return addDays(date, 1);
    }

    public static Date addDays(Date time, int amount) {
        Calendar calendar = CALENDAR;
        synchronized(calendar) {
            calendar.setTime(time);
            calendar.add(5, amount);
            return calendar.getTime();
        }
    }

    public static Date nextWeek(Date date) {
        return addDays(date, 7);
    }

    public static int getDaysDiff(long t1, long t2, boolean checkOverflow) {
        if (t1 > t2) {
            long tmp = t1;
            t1 = t2;
            t2 = tmp;
        }

        Calendar calendar = CALENDAR;
        synchronized(calendar) {
            calendar.setTimeInMillis(t1);

            int delta;
            for(delta = 0; calendar.getTimeInMillis() < t2; ++delta) {
                calendar.add(5, 1);
            }

            if (checkOverflow && calendar.getTimeInMillis() > t2) {
                --delta;
            }

            return delta;
        }
    }

    public static int getDaysDiff(long t1, long t2) {
        return getDaysDiff(t1, t2, true);
    }

    public static boolean isFirstOfYear(Date date) {
        boolean ret = false;
        Calendar calendar = CALENDAR;
        synchronized(calendar) {
            calendar.setTime(date);
            int currentYear = calendar.get(1);
            calendar.add(5, -1);
            int yesterdayYear = calendar.get(1);
            ret = currentYear != yesterdayYear;
            return ret;
        }
    }

    public static boolean isFirstOfMonth(Date date) {
        boolean ret = false;
        Calendar calendar = CALENDAR;
        synchronized(calendar) {
            calendar.setTime(date);
            int currentMonth = calendar.get(2);
            calendar.add(5, -1);
            int yesterdayMonth = calendar.get(2);
            ret = currentMonth != yesterdayMonth;
            return ret;
        }
    }

    public static Date previousDay(Date date) {
        return addDays(date, -1);
    }

    public static Date previousWeek(Date date) {
        return addDays(date, -7);
    }

    public static Date getPreviousDay(Date date, int startOfWeek) {
        return getDay(date, startOfWeek, -1);
    }

    public static Date getNextDay(Date date, int startOfWeek) {
        return getDay(date, startOfWeek, 1);
    }

    private static Date getDay(Date date, int startOfWeek, int increment) {
        Calendar calendar = CALENDAR;
        synchronized(calendar) {
            calendar.setTime(date);

            for(int day = calendar.get(7); day != startOfWeek; day = calendar.get(7)) {
                calendar.add(5, increment);
            }

            return startOfDayInMillis(calendar.getTime());
        }
    }

    public static Date getPreviousMonth(Date date) {
        return incrementMonth(date, -1);
    }

    public static Date getNextMonth(Date date) {
        return incrementMonth(date, 1);
    }

    private static Date incrementMonth(Date date, int increment) {
        Calendar calendar = CALENDAR;
        synchronized(calendar) {
            calendar.setTime(date);
            calendar.add(2, increment);
            return calendar.getTime();
        }
    }

    public static Date getStartOfMonth(Date date) {
        return getMonth(date, -1);
    }

    public static Date getEndOfMonth(Date date) {
        return getMonth(date, 1);
    }

    private static Date getMonth(Date date, int increment) {
        Calendar calendar = CALENDAR;
        synchronized(calendar) {
            calendar.setTime(date);
            if (increment == -1) {
                calendar.set(5, 1);
                return startOfDayInMillis(calendar.getTime());
            } else {
                calendar.add(2, 1);
                calendar.set(5, 1);
                calendar.set(11, 0);
                calendar.set(14, 0);
                calendar.set(13, 0);
                calendar.set(12, 0);
                calendar.add(14, -1);
                return calendar.getTime();
            }
        }
    }

    public static int getDayOfWeek(Date date) {
        Calendar calendar = CALENDAR;
        synchronized(calendar) {
            calendar.setTime(date);
            return calendar.get(7);
        }
    }

    public static String getDayOfWeekCN(Date date) {
        Calendar calendar = CALENDAR;
        synchronized(calendar) {
            calendar.setTime(date);
            return WeekDaysEnum.getTypeNameById(calendar.get(7));
        }
    }

    public static Date getDateAddYear(Date date, int years) {
        if (date == null) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(date.getTime()));
            int year = calendar.get(1);
            calendar.set(1, year + years);
            return new java.sql.Date(calendar.getTime().getTime());
        }
    }

    public static Date getDateAddMonth(Date date, int month) {
        if (date == null) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(date.getTime()));
            int year = calendar.get(2);
            calendar.set(2, year + month);
            return new java.sql.Date(calendar.getTime().getTime());
        }
    }

    public static int compareDate(Date date1, Date date2) {
        return isSameDay(date1, date2) ? 0 : date1.compareTo(date2);
    }

    public static int compareDateTime(Date date1, Date date2) {
        return date1.compareTo(date2);
    }

    public static int getWeekOfMonth(Date date) {
        Calendar calendar = CALENDAR;
        synchronized(calendar) {
            calendar.setTime(date);
            return calendar.get(4);
        }
    }

    public static int getWeekOfYear(Date date) {
        Calendar calendar = CALENDAR;
        synchronized(calendar) {
            calendar.setTime(date);
            return calendar.get(3);
        }
    }

    public static Date getDateAfter5Minute() {
        Calendar now = Calendar.getInstance();
        now.add(12, 5);
        return now.getTime();
    }
}
