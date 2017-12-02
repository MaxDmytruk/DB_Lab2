package services;

import java.sql.Timestamp;
import java.util.Calendar;

public class CalendarToTimestamp {
    public Timestamp convert(Calendar calendarDate){
        return new Timestamp(calendarDate.getTimeInMillis());
    }
}
