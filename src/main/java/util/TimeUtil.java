package util;

import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    /**
     * 设置时间
     *
     * @param year  年
     * @param month 月（1-12）
     * @param day 日（1-31）
     * @param hour 时（0-23）
     * @param minute 分（0-59）
     * @param second 秒（0-59）
     * @return -
     */
    public Date setDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        //calendar月份从0开始算！
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 设置时间，时分秒都默认为1
     *
     * @param year  年
     * @param month 月（1-12）
     * @param day 日（1-31）
     * @return -
     */
    public Date setDate(int year, int month, int day) {
        return setDate(year, month, day, 1, 1, 1);
    }

    /**
     * 设置时间，时分秒都默认为0\0\30
     *
     * @param year  年
     * @param month 月（1-12）
     * @param day 日（1-31）
     * @return -
     */
    public Date setDateStart(int year, int month, int day) {
        return setDate(year, month, day, 0, 0, 30);
    }

    /**
     * 设置时间，时分秒都默认为23/59/30
     *
     * @param year  年
     * @param month 月（1-12）
     * @param day 日（1-31）
     * @return -
     */
    public Date setDateEnd(int year, int month, int day) {
        return setDate(year, month, day, 23, 59, 30);
    }

    /**
     * 根据日期取得星期几
     * @param date
     * @return
     */
    public int getWeek(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }
    public static Date getSundayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 7);
        return c.getTime();
    }
//    public Date getMonday(Date date) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        cal.getFirstDayOfWeek();
//    }
}
