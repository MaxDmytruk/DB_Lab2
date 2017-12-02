package services;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class CalendarToTimestampTest {
    @Test
    void convert() {
        CalendarToTimestamp converter = new CalendarToTimestamp();
        Calendar test = Calendar.getInstance();
        test.set(1970, Calendar.JANUARY, 1, 0, 0, 1);
        assertEquals(new Timestamp(test.getTimeInMillis()), converter.convert(test));
    }

}