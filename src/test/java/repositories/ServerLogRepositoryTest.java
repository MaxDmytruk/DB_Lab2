package repositories;

import com.mongodb.MongoTimeoutException;
import models.ServerLog;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

public class ServerLogRepositoryTest {
    private static ServerLogRepository target;

    @BeforeClass
    public static void init(){
        target = new ServerLogRepository();
    }

    @Test
    public void connect() {
        int exceptionCount = 0;
        try{
            target.connect();
        }
        catch (MongoTimeoutException ex) {
            assertEquals("start your server", ex.getMessage());
        }
        catch (Exception e){
            exceptionCount ++;
        }
        assertEquals(0, exceptionCount);
    }

    @Test
    public void getAllServerLog(){
        int exceptionCount = 0;
        try {
            target.getAllServerLogs();
        }
        catch (MongoTimeoutException ex) {
            assertEquals("start your server", ex.getMessage());
        }
        assertEquals(0, exceptionCount);
    }

    @Test
    public void insertServerLog() {
        int exceptionCount = 0;

        ServerLog testLog = new ServerLog();

        Calendar startDate = Calendar.getInstance();
        startDate.set(2017, Calendar.NOVEMBER, 25);

        testLog.setTimestamp(new Timestamp(startDate.getTimeInMillis()));

        try {
            target.insertServerLog(testLog);
            target.deleteServerLog(testLog);
        }
        catch (MongoTimeoutException ex) {
            assertEquals("start your server", ex.getMessage());
        }
        assertEquals(0, exceptionCount);
    }

    @Test
    public void updateServerLog() {
        int exceptionCount = 0;

        ServerLog testLog = new ServerLog();

        Calendar startDate = Calendar.getInstance();
        startDate.set(2017, Calendar.NOVEMBER, 25);

        ServerLog newLog = new ServerLog();

        testLog.setTimestamp(new Timestamp(startDate.getTimeInMillis()));

        try {
            target.updateServerLog(testLog, newLog);
        }
        catch (MongoTimeoutException ex) {
            assertEquals("start your server", ex.getMessage());
        }
        target.deleteServerLog(testLog);
        assertEquals(0, exceptionCount);
    }

    @Test
    public void deleteServerLog() {
        int exceptionCount = 0;
        try {
            ServerLog testLog = new ServerLog();

            Calendar startDate = Calendar.getInstance();
            startDate.set(2017, Calendar.NOVEMBER, 25);

            testLog.setTimestamp(new Timestamp(startDate.getTimeInMillis()));
            target.deleteServerLog(testLog);
        }
        catch (MongoTimeoutException ex) {
            assertEquals("start your server", ex.getMessage());
        }
        assertEquals(0, exceptionCount);
    }

    @Test
    public void getIpsByUrl() {int exceptionCount = 0;
        try {
            target.getIpsByUrl("test");
        }
        catch (MongoTimeoutException ex) {
            assertEquals("start your server", ex.getMessage());
        }
        assertEquals(0, exceptionCount);
    }

    @Test
    public void getUrlsByPeriod() {
        int exceptionCount = 0;
        try {
            Calendar startDate = Calendar.getInstance();
            startDate.set(2017, Calendar.NOVEMBER, 25);

            Calendar endDate= Calendar.getInstance();
            startDate.set(2017, Calendar.NOVEMBER, 29);

            target.getUrlsByPeriod(startDate, endDate);
        }
        catch (MongoTimeoutException ex) {
            assertEquals("start your server", ex.getMessage());
        }
        assertEquals(0, exceptionCount);
    }

    @Test
    public void getUrlsByIp() {
        int exceptionCount = 0;
        try {
            target.getUrlsByIp("2.2.2");
        }
        catch (MongoTimeoutException ex) {
            assertEquals("start your server", ex.getMessage());
        }
        assertEquals(0, exceptionCount);
    }

    @Test
    public void getUrlSumTimeSpent() {
        int exceptionCount = 0;
        try {
            target.getUrlSumTimeSpent();
        }
        catch (MongoTimeoutException ex) {
            assertEquals("start your server", ex.getMessage());
        }
        catch (Exception ex) {
            assertEquals("Document is null or empty", ex.getMessage());
        }
        assertEquals(0, exceptionCount);
    }

    @Test
    public void getUrlCount() {
        int exceptionCount = 0;
        try {
            target.getUrlCount();
        }
        catch (MongoTimeoutException ex) {
            assertEquals("start your server", ex.getMessage());
        }
        catch (Exception ex) {
            assertEquals("Document is null or empty", ex.getMessage());
        }
        assertEquals(0, exceptionCount);
    }

    @Test
    public void getUrlCountInPeriod() {
        int exceptionCount = 0;
        try {
            Calendar startDate = Calendar.getInstance();
            startDate.set(2017, Calendar.NOVEMBER, 25);

            Calendar endDate= Calendar.getInstance();
            startDate.set(2017, Calendar.NOVEMBER, 29);

            target.getUrlCountInPeriod(startDate, endDate);
        }
        catch (MongoTimeoutException ex) {
            assertEquals("start your server", ex.getMessage());
        }
        assertEquals(0, exceptionCount);
    }

    @Test
    public void getIpTimeSpent() {
        int exceptionCount = 0;
        try {
            target.getIpTimeSpent();
        }
        catch (MongoTimeoutException ex) {
            assertEquals("start your server", ex.getMessage());
        }
        catch (Exception ex) {
            assertEquals("Document is null or empty", ex.getMessage());
        }
        assertEquals(0, exceptionCount);
    }

}