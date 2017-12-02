package repositories;

import models.ServerLog;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class ServerLogRepositoryTest {
    static ServerLogRepository target;

    @BeforeAll
    static void init(){
        target = new ServerLogRepository();
    }

    @Test
    void connect() {
        int exceptionCount = 0;
        try{
            target.connect();
        }
        catch (Exception e){
            exceptionCount ++;
        }
        assertEquals(0, exceptionCount);
    }

    @Test
    void getAllServerLog(){
        int exceptionCount = 0;

        ServerLog testLog = new ServerLog();

        try {
            target.getAllServerLogs();
        }
        catch (Exception ex) {
            exceptionCount++;
        }
        assertEquals(0, exceptionCount);
    }

    @Test
    void insertServerLog() {
        int exceptionCount = 0;

        ServerLog testLog = new ServerLog();

        Calendar startDate = Calendar.getInstance();
        startDate.set(2017, Calendar.NOVEMBER, 25);

        testLog.setTimestamp(new Timestamp(startDate.getTimeInMillis()));

        try {
            target.insertServerLog(testLog);
        }
        catch (Exception ex) {
            exceptionCount++;
        }
        target.deleteServerLog(testLog);
        assertEquals(0, exceptionCount);
    }

    @Test
    void updateServerLog() {
        int exceptionCount = 0;

        ServerLog testLog = new ServerLog();

        Calendar startDate = Calendar.getInstance();
        startDate.set(2017, Calendar.NOVEMBER, 25);

        ServerLog newLog = new ServerLog();

        testLog.setTimestamp(new Timestamp(startDate.getTimeInMillis()));

        try {
            target.updateServerLog(testLog, newLog);
        }
        catch (Exception ex) {
            exceptionCount++;
        }
        target.deleteServerLog(testLog);
        assertEquals(0, exceptionCount);
    }

    @Test
    void deleteServerLog() {
        int exceptionCount = 0;
        try {
            ServerLog testLog = new ServerLog();

            Calendar startDate = Calendar.getInstance();
            startDate.set(2017, Calendar.NOVEMBER, 25);

            testLog.setTimestamp(new Timestamp(startDate.getTimeInMillis()));
            target.deleteServerLog(testLog);
        }
        catch (Exception ex) {
            exceptionCount++;
        }
        assertEquals(0, exceptionCount);
    }

    @Test
    void getIpsByUrl() {int exceptionCount = 0;
        try {
            target.getIpsByUrl("test");
        }
        catch (Exception ex) {
            exceptionCount++;
        }
        assertEquals(0, exceptionCount);
    }

    @Test
    void getUrlsByPeriod() {
        int exceptionCount = 0;
        try {
            Calendar startDate = Calendar.getInstance();
            startDate.set(2017, Calendar.NOVEMBER, 25);

            Calendar endDate= Calendar.getInstance();
            startDate.set(2017, Calendar.NOVEMBER, 29);

            target.getUrlsByPeriod(startDate, endDate);
        }
        catch (Exception ex) {
            exceptionCount++;
        }
        assertEquals(0, exceptionCount);
    }

    @Test
    void getUrlsByIp() {
        int exceptionCount = 0;
        try {
            target.getUrlsByIp("2.2.2");
        }
        catch (Exception ex) {
            exceptionCount++;
        }
        assertEquals(0, exceptionCount);
    }

    @Test
    void getUrlSumTimeSpent() {
        int exceptionCount = 0;
        try {
            target.getUrlSumTimeSpent();
        }
        catch (Exception ex) {
            exceptionCount++;
        }
        assertEquals(0, exceptionCount);
    }

    @Test
    void getUrlCount() {
        int exceptionCount = 0;
        try {
            target.getUrlCount();
        }
        catch (Exception ex) {
            exceptionCount++;
        }
        assertEquals(0, exceptionCount);
    }

    @Test
    void getUrlCountInPeriod() {
        int exceptionCount = 0;
        try {
            Calendar startDate = Calendar.getInstance();
            startDate.set(2017, Calendar.NOVEMBER, 25);

            Calendar endDate= Calendar.getInstance();
            startDate.set(2017, Calendar.NOVEMBER, 29);

            target.getUrlCountInPeriod(startDate, endDate);
        }
        catch (Exception ex) {
            exceptionCount++;
        }
        assertEquals(0, exceptionCount);
    }

    @Test
    void getIpTimeSpent() {
        int exceptionCount = 0;
        try {
            target.getIpTimeSpent();
        }
        catch (Exception ex) {
            exceptionCount++;
        }
        assertEquals(0, exceptionCount);
    }

}