package com.wakaka.util;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoZonedDateTime;
import java.util.*;

/**
 * @author loan hello@1234.com
 * @version V1.0
 * @Description: 日期时间工具类
 * @date 2017-07-05
 * @Copyright: loan
 */
public class DateUtil {

    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_YYYYMMDDHHMMSSSSS = "yyyyMMddhhmmssSSS";
    public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddhhmmss";
    public final static String webFormat = "yyyy-MM-dd";

    public static String getCurrentDate() {
        String formatPattern_Short = "yyyyMMddhhmmss";
        SimpleDateFormat format = new SimpleDateFormat(formatPattern_Short);
        return format.format(new Date());
    }

    public static String getSeqString() {
        SimpleDateFormat fm = new SimpleDateFormat("yyyyMMddHHmmss"); // "yyyyMMdd G
        return fm.format(new Date());
    }

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取当前时间，格式为 yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurrentTimeStr(String format) {
        format = StringUtils.isBlank(format) ? FORMAT_YYYY_MM_DD_HH_MM_SS : format;
        Date now = new Date();
        return date2Str(now, format);
    }


    public static String date2Str(Date date) {
        return date2Str(date, FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 时间转换成 String类型
     *
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date, String format) {
        if ((format == null) || format.equals("")) {
            format = FORMAT_YYYY_MM_DD_HH_MM_SS;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
            return sdf.format(date);
        }
        return "";
    }

    public static Date stringToDate(String str, String format) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            if (!StringUtils.isBlank(str)) {
                date = sdf.parse(str);
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取批量付款预约时间
     *
     * @return
     */
    public static String getRevTime() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        String dateString = new SimpleDateFormat(DateUtil.FORMAT_YYYYMMDDHHMMSS).format(cal.getTime());
        System.out.println(dateString);
        return dateString;
    }

    /**
     * 时间比较
     *
     * @param date1
     * @param date2
     * @return DATE1>DATE2返回1，DATE1<DATE2返回-1,等于返回0
     */
    public static int compareDate(String date1, String date2, String format) {

        DateFormat df = new SimpleDateFormat(format);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 把给定的时间减掉给定的分钟数
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date minusDateByMinute(Date date, int minute) {
        Date newDate = new Date(date.getTime() - (minute * 60 * 1000));
        return newDate;
    }

    public static Date getNowTime() {
        String now = date2Str(new Date());

        return stringToDate(now, FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 把给定的时间加上给定的分钟数
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date addDateByMinute(Date date, int minute) {
        Date newDate = new Date(date.getTime() + (minute * 60 * 1000));
        return newDate;
    }

    /**
     * 时间比较
     *
     * @param date1
     * @param date2
     * @return DATE1>DATE2返回1，DATE1<DATE2返回-1,等于返回0
     */
    public static int compareDate(Date date1, Date date2) {
        if (date2 == null) {
            return 1;
        }
        if (date1 == null) {
            return -1;
        }
        try {

            if (date1.getTime() > date2.getTime()) {
                return 1;
            } else if (date1.getTime() < date2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 把给定的时间加上给定的天数
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDateByDay(Date date, int day) {
        Date newDate = new Date(date.getTime() + (day * 24 * 3600 * 1000));
        return newDate;
    }

    /**
     * 把给定的时间减去给定的天数
     *
     * @param date
     * @param day
     * @return
     */
    public static Date minusDateByDay(Date date, int day) {
        Date newDate = new Date(date.getTime() - (day * 24 * 3600 * 1000));
        return newDate;
    }

    /**
     * 把给定的时间转换成**年**月**日 **时**分
     */
    public static String getTimeByCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);//获取年份
        int month = cal.get(Calendar.MONTH) + 1;//获取月份
        int day = cal.get(Calendar.DATE);//获取日
        int hour = cal.get(Calendar.HOUR_OF_DAY);//小时
        int minute = cal.get(Calendar.MINUTE);//分
        int second = cal.get(Calendar.SECOND);//秒

        String str = year + "年" + month + "月" + day + "日      " + hour + "时" + minute + "分";

        return str;
    }

    /**
     * 获取两个日期之间的时间差
     *
     * @param date1
     * @param date2
     * @param date1-date2
     * @return long *天
     */
    public static long subtractionDate(Date date1, Date date2) {
        long nd = 1000 * 24 * 60 * 60;//天
        if (compareDate(date1, date2) >= 1) {
            long off = date1.getTime() - date2.getTime();
            long day = off / nd;
            if (off % nd > 0) {
                return day + 1;
            }
            return day;
        }
        return 0;
    }

    /**
     * 日期相减获得分钟
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getMinuteSubDate(Date date1, Date date2) {
        long nd = 1000L * 60;//分钟
        if (compareDate(date1, date2) >= 1) {
            long off = date1.getTime() - date2.getTime();
            long day = off / nd;
            if (off % nd > 0) {
                return day + 1;
            }
            return day;
        }
        return 0;
    }

    /**
     * @param date
     * @return
     */
    public static LocalDate DateToLocaleDate(Date date) {
        Calendar curr = Calendar.getInstance();

        curr.set(Calendar.DAY_OF_MONTH, curr.get(Calendar.DAY_OF_MONTH) - 7);

        date = curr.getTime();

        Instant instant = date.toInstant();

        ZoneId zoneId = ZoneId.systemDefault();

        return instant.atZone(zoneId).toLocalDate();

    }

    /**
     * @param localDate
     * @return
     */
    public static Date LocalDateToDate(LocalDate localDate) {


        ZoneId zoneId = ZoneId.systemDefault();

        ChronoZonedDateTime<LocalDate> zonedDateTime = localDate.atStartOfDay(zoneId);

        return Date.from(zonedDateTime.toInstant());

    }

    /**
     * 当前时间所在一周的时间
     *
     * @param
     * @return
     */
    public static Map<String, String> getWeekDate() {
        Map<String, String> map = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = new GregorianCalendar();
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (dayWeek == 1) {
            dayWeek = 8;
        }
        System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期

        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);

        cal.setTime(mondayDate);
        cal.add(cal.DATE, 1);
        Date tuesday = cal.getTime();
        String tues = sdf.format(tuesday);

        cal.setTime(mondayDate);
        cal.add(cal.DATE, 2);
        Date wedesday = cal.getTime();
        String wedes = sdf.format(wedesday);

        cal.setTime(mondayDate);
        cal.add(cal.DATE, 3);
        Date thursday = cal.getTime();
        String thurs = sdf.format(thursday);


        cal.setTime(mondayDate);
        cal.add(cal.DATE, 4);
        Date friday = cal.getTime();
        String fri = sdf.format(friday);

        cal.setTime(mondayDate);
        cal.add(cal.DATE, 5);
        Date saturday = cal.getTime();
        String satur = sdf.format(saturday);

        cal.setTime(mondayDate);
        cal.add(cal.DATE, 6);
        Date sunday = cal.getTime();
        String sun = sdf.format(sunday);


        map.put("mondayDate", weekBegin);
        map.put("tuesdayDate", tues);
        map.put("wedesdayDate", wedes);
        map.put("thursdayDate", thurs);
        map.put("fridayDate", fri);
        map.put("saturdayDate", satur);
        map.put("sundayDate", sun);
        return map;
    }

    /**
     * 获取时间段 起始日期--终止日期
     *
     * @param date     终止日期
     * @param time     时间长度
     * @param timeType 时间长度类型 day-天  minute-分
     * @param format   日期格式
     * @return map
     * {"startTime":"yyyy-MM-dd","endTime":"yyyy-MM-dd"}
     */
    public static Map<String, Object> getTimeSlot(Date date, int time, String timeType, String format) {
        Map<String, Object> map = new HashMap<>();
        format = StringUtils.isBlank(format) ? webFormat : format;

        Date endTime = stringToDate(date2Str(date), format);
        Date startTime = null;
        if ("day".equals(timeType)) {
            startTime = minusDateByDay(endTime, time);
        } else if ("minute".equals(timeType)) {
            startTime = minusDateByMinute(endTime, time);
        }
        map.put("endTime", endTime);
        map.put("startTime", startTime);
        return map;
    }

    /**
     * 把给定的时间转换成**年**月**日
     */
    public static String date2String(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);//获取年份
        int month = cal.get(Calendar.MONTH) + 1;//获取月份
        int day = cal.get(Calendar.DATE);//获取日

        String str = year + "年" + month + "月" + day + "日 ";

        return str;
    }

}
