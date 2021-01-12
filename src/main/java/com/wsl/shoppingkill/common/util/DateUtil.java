package com.wsl.shoppingkill.common.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 时间工具类
 * @author Wangshilei
 *
 */
@Slf4j
public class DateUtil {
    public static final String DEFAULT_TZ = "Asia/Shanghai";

    /**
     *  是否比当前时间早
     * @author  WangShilei
     * @date 2020/11/7 6:05 下
     **/
    public static boolean isAfter(LocalDateTime localDateTime){
        //获取当前时间
        LocalDateTime nowTime= LocalDateTime.now();
        //比较  现在的时间 比 设定的时间 之前  返回的类型是Boolean类型
        return nowTime.isAfter(localDateTime);
    }

    /**
     * 是否比当前时间晚
     * @author : WangShiLei
     * @date : 2020/11/7 6:06 下午
     **/
    public static boolean isBefore(LocalDateTime localDateTime){
        //获取当前时间
        LocalDateTime nowTime= LocalDateTime.now();
        //比较  现在的时间 比 设定的时间 之后  返回的类型是Boolean类型
        return nowTime.isBefore(localDateTime);
    }
    /**
     * 获取过去第几天的日期
     *
     * @param past：过去几天
     * @return Date：过去时间日期
     */
    public static Date getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        return calendar.getTime();
    }


    /**
     * 获取明天的几点钟点钟
     * 抢购的时候需要
     * @param hours:明天几点 int
     * @return LocalDateTime：明天几点钟钟
     */
    public static LocalDateTime getTomorrow10Clock(int hours) {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        LocalTime ten = LocalTime.of(hours, 0);
        return LocalDateTime.of(tomorrow, ten);
    }

    /**
     * date转LocalDate
     *
     * @param date：date类型
     * @return LocalDate：LocalDate类型
     */
    public static LocalDate date2LocalDate(Date date) {
        if (date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }

    /**
     * 获取今天的几点钟点钟
     * 抢购的时候需要
     * @param hours:今天几点 int
     * @return LocalDateTime
     */
    public static LocalDateTime getToday10Clock(int hours) {
        LocalDate today = LocalDate.now();
        LocalTime ten = LocalTime.of(hours, 0);
        return LocalDateTime.of(today, ten);
    }

    /**
     * 判断选择的日期是否是本周
     * @param time:long类型
     * @return boolean类型
     */
    public static boolean isThisWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(new Date(time));
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        return paramWeek == currentWeek;
    }

    /**判断选择的日期是否是今天
     * @param time :long类型时间
     * @return bool：是否是今天
     **/
    public static boolean isToday(long time) {
        return isThisTime(time, "yyyy-MM-dd");
    }

    /**
     *判断选择的日期是否是本月
     * @param time :long类型时间
     * @return 是否是本月
     */

    public static boolean isThisMonth(long time) {
        return isThisTime(time, "yyyy-MM");
    }

    /**
     * 判断是否是当前时间
     * @param time : long 时间
     * @return pattern ： 时间格式String
     * @author wangshilei
     * @date 2020/11/6 15:50
     **/
    private static boolean isThisTime(long time, String pattern) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        //参数时间
        String param = sdf.format(date);
        //当前时间
        String now = sdf.format(new Date());
        return param.equals(now);
    }

    /**
     * 时间相差秒数.
     *
     * @param start ： 开始时间
     * @param end ：结束时间
     * @return long
     */
    public static long distanceSecond(Date start, Date end) {
        return (end.getTime() - start.getTime()) / 1000;
    }

    /**
     * 时间相差秒数.
     *
     * @param start ： 开始时间
     * @param end ：结束时间
     * @return long
     */
    public static long distanceSecond(LocalDateTime start, LocalDateTime end) {
        return Duration.between(start, end).toMillis()/1000;
    }

    /**
     * 获取String类型时间
     * @return String ：yyyy-MM-dd HH:mm:ss
     */
    public static String getDateString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    /**
     * 时间格式化
     * @param date : 时间
     * @param format :格式类型
     * @return String：返回时间
     * @author wangshilei
     * @date 2020/11/6 15:53
     **/
    public static String format(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }


    /**
     * date转String
     * @param date : 时间
     * @return String ： 时间
     * @author wangshilei
     * @date 2020/11/6 15:54
     **/
    public static String getProPriceTrendDateStr(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");
        return simpleDateFormat.format(date);
    }


    /**
     * 计算当前系统时间是否在起始时间及终止时间范围内
     *
     * @param beginDate：开始时间
     * @param endDate：结束时间
     * @return 是否成功
     */
    public static Boolean dateRange(String beginDate, String endDate) {
        //将字符串格式的日期格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            //将日期转成Date对象作比较
            Date beginDate1 = sdf.parse(beginDate);
            Date endDate2 = sdf.parse(endDate);
            Date nowDate = sdf.parse(sdf.format(new Date()));
            if (nowDate.compareTo(beginDate1) >= 0 && nowDate.compareTo(endDate2) <= 0) {
                return true;
            }
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return false;
    }


    public static Date localDate() {
        if (!DEFAULT_TZ.equals(TimeZone.getDefault().getID())) {
            TimeZone.setDefault(TimeZone.getTimeZone(DEFAULT_TZ));
            System.setProperty("user.timezone", DEFAULT_TZ);
            log.error("时区发生切换，已经自动切回默认时区GMT+8");
        }
        if (!DEFAULT_TZ.equals(System.getProperty("user.timezone"))) {
            TimeZone.setDefault(TimeZone.getTimeZone(DEFAULT_TZ));
            System.setProperty("user.timezone", DEFAULT_TZ);
            log.error("时区发生切换，已经自动切回默认时区GMT+8");
        }
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(DEFAULT_TZ));
        return Date.from(now.toInstant());
    }

    /**
     * 判断时间是不是今天
     *
     * @param date ：时间
     * @return 是返回true，不是返回false
     */
    public static Boolean dateIsToday(Date date) {
        //当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        //获取今天的日期
        String nowDay = sf.format(now);
        //对比的时间
        String day = sf.format(date);

        return day.equals(nowDay);

    }

    /**
     * 现在到明天0点的秒数.
     *
     * @return long
     */
    public static Long toTomorrowSecond() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = cal.getTime();
        Date now = new Date();
        //单位是秒  现在到明天0点的秒数
        return (tomorrow.getTime() - now.getTime()) / 1000;
    }

    /**
     * 获得3天前的时时间（如今天是19号，则返回的是16号0点）
     *
     * @return date
     */
    public static Date toBeforeThirdDay() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, -3);
        return cal.getTime();
    }

}
