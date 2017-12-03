package models;

import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class ServerLogTest {
    static ServerLog target;

    @BeforeClass
    public static void init(){
        target = new ServerLog();

        target.setTimeSpent(10);
        target.setUrl("test url");
        target.setTimestamp(new Timestamp(100));
        target.setIp("1.1.1.1");
    }

    @Test
    public void getUrl() {
        assertEquals("test url", target.getUrl());
    }

    @Test
    public void setUrl() {
        target.setUrl("new test");
        assertEquals("new test", target.getUrl());
    }

    @Test
    public void getIp() {
        assertEquals("1.1.1.1", target.getIp());
    }

    @Test
    public void setIp() {
        target.setIp("2.2.2");
        assertEquals("2.2.2", target.getIp());
    }

    @Test
    public void getTimestamp() {
        Timestamp expected = new Timestamp(100);
        assertEquals(expected, target.getTimestamp());
    }

    @Test
    public void setTimestamp() {
        Timestamp expected = new Timestamp(111);
        target.setTimestamp(expected);
        assertEquals(expected, target.getTimestamp());
    }

    @Test
    public void getTimeSpent() {
        assertEquals(10, target.getTimeSpent());
    }

    @Test
    public void setTimeSpent() {
        target.setTimeSpent(12);
        assertEquals(12,target.getTimeSpent());
    }

}