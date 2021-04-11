package util.impl;

import util.TimeUTIL;

import java.util.Calendar;
import java.util.Date;

public class TimeUTILImpl implements TimeUTIL {

    @Override
    public Date setDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
        calendar.set(Calendar.YEAR, year);
        //calendar月份从0开始算！
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    @Override
    public Date setDate(int year, int month, int day) {
        return setDate(year, month, day, 1, 1, 1);
    }

    @Override
    public Date setDateStart(int year, int month, int day) {
        return setDate(year, month, day, 0, 0, 30);
    }

    @Override
    public Date setDateEnd(int year, int month, int day) {
        return setDate(year, month, day, 23, 59, 30);
    }
}
