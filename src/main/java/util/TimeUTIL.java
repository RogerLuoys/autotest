package util;

import java.util.Date;

public interface TimeUTIL {

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
    Date setDate(int year, int month, int day, int hour, int minute, int second);

    /**
     * 设置时间，时分秒都默认为1
     *
     * @param year  年
     * @param month 月（1-12）
     * @param day 日（1-31）
     * @return -
     */
    Date setDate(int year, int month, int day);

    /**
     * 设置时间，时分秒都默认为0\0\30
     *
     * @param year  年
     * @param month 月（1-12）
     * @param day 日（1-31）
     * @return -
     */
    Date setDateStart(int year, int month, int day);

    /**
     * 设置时间，时分秒都默认为23/59/30
     *
     * @param year  年
     * @param month 月（1-12）
     * @param day 日（1-31）
     * @return -
     */
    Date setDateEnd(int year, int month, int day);
}
