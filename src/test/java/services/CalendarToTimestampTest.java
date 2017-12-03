package services;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Calendar;

import static org.junit.Assert.*;

public class CalendarToTimestampTest {
    @Test
    public void convert() {
        CalendarToTimestamp converter = new CalendarToTimestamp();
        Calendar test = Calendar.getInstance();
        test.set(1970, Calendar.JANUARY, 1, 0, 0, 1);
        assertEquals(new Timestamp(test.getTimeInMillis()), converter.convert(test));
    }
}