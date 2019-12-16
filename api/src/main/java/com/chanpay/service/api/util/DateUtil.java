package com.chanpay.service.api.util;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-16 14:54
 * @Description:
 */

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Slf4j
public class DateUtil {
    private static final FastDateFormat dateFormat = createDateFormat("yyyy-MM-dd");
    private static final FastDateFormat datetimeFormat = createDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final FastDateFormat yyyyMMddHHmmssSSS = createDateFormat("yyyyMMddHHmmssSSS");
    private static final FastDateFormat yyyyMMddHHmmss = createDateFormat("yyyyMMddHHmmss");

    public DateUtil() {
    }

    public static FastDateFormat createDateFormat(String pattern) {
        return FastDateFormat.getInstance(pattern);
    }

    public static Date parseDate(String source) throws ParseException {
        return dateFormat.parse(source);
    }

    public static Date parseDateTime(String source) throws ParseException {
        return datetimeFormat.parse(source);
    }

    public static Date parse(long timestamp) {
        return new Date(timestamp);
    }

    public static Long parseLong(Object obj) {
        if (obj instanceof Long) {
            return (Long)obj;
        } else if (obj instanceof Date) {
            return ((Date)obj).getTime();
        } else if (null == obj) {
            return null;
        } else {
            String dateStr = obj.toString();

            try {
                return dateStr.contains("-") ? parseDateTime(dateStr).getTime() : Long.parseLong(dateStr);
            } catch (Exception var3) {
                log.error(var3.getMessage(), var3);
                return null;
            }
        }
    }

    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }

    public static String formatDate(long timestamp) {
        return dateFormat.format(timestamp);
    }

    public static String formatDate(String source) throws ParseException {
        return formatDate(parseDateTime(source));
    }

    public static String formatDateTime(Date date) {
        return datetimeFormat.format(date);
    }

    public static String formatDateTimeTwo(Date date) {
        return yyyyMMddHHmmss.format(date);
    }

    public static String formatDateTime(long timestamp) {
        return datetimeFormat.format(timestamp);
    }

    public static String formatDateTime(String source) throws ParseException {
        return formatDateTime(parseDate(source));
    }

    public static int compare(String arg1, String arg2) throws ParseException {
        try {
            return parseDateTime(arg1).compareTo(parseDateTime(arg2));
        } catch (ParseException var3) {
            return parseDate(arg1).compareTo(parseDate(arg2));
        }
    }

    public static String getTimeStampString() {
        return yyyyMMddHHmmssSSS.format(System.currentTimeMillis());
    }

    public static Timestamp formatTimestampString(String dateStr) {
        Calendar c = Calendar.getInstance();
        long time = 4133865600000L;
        c.setTimeInMillis(time);
        if (dateStr == null) {
            return new Timestamp(c.getTimeInMillis());
        } else {
            try {
                return new Timestamp(parseDate(dateStr).getTime());
            } catch (Exception var5) {
                return new Timestamp(c.getTimeInMillis());
            }
        }
    }

    public static long floorDay(long day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(day);
        c.set(11, 0);
        c.set(12, 0);
        c.set(13, 0);
        c.set(14, 0);
        return c.getTime().getTime();
    }

    public static Date addMonth(Date currentDay, int monthCount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDay);
        calendar.add(2, monthCount);
        return calendar.getTime();
    }
}
