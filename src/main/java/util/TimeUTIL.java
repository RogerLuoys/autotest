package util;

import java.util.Date;

public interface TimeUTIL {

    Date setDate(int year, int month, int day, int hour, int minute, int second);

    Date setDate(int year, int month, int day);

    Date setDateStart(int year, int month, int day);

    Date setDateEnd(int year, int month, int day);
}
