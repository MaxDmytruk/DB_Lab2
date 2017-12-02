package models;

import org.junit.jupiter.api.BeforeAll;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class ServerLogTest {
    static ServerLog target;

    @BeforeAll
    static void init(){
        target = new ServerLog();

        target.setTimeSpent(10);
        target.setUrl("test url");
        target.setTimestamp(new Timestamp(100));
        target.setIp("1.1.1.1");
    }

    @org.junit.jupiter.api.Test
    void getUrl() {
        assertEquals("test url", target.getUrl());
    }

    @org.junit.jupiter.api.Test
    void setUrl() {
        target.setUrl("new test");
        assertEquals("new test", target.getUrl());
    }

    @org.junit.jupiter.api.Test
    void getIp() {
        assertEquals("1.1.1.1", target.getIp());
    }

    @org.junit.jupiter.api.Test
    void setIp() {
        target.setIp("2.2.2");
        assertEquals("2.2.2", target.getIp());
    }

    @org.junit.jupiter.api.Test
    void getTimestamp() {
        Timestamp expected = new Timestamp(100);
        assertEquals(expected, target.getTimestamp());
    }

    @org.junit.jupiter.api.Test
    void setTimestamp() {
        Timestamp expected = new Timestamp(111);
        target.setTimestamp(expected);
        assertEquals(expected, target.getTimestamp());
    }

    @org.junit.jupiter.api.Test
    void getTimeSpent() {
        assertEquals(10, target.getTimeSpent());
    }

    @org.junit.jupiter.api.Test
    void setTimeSpent() {
        target.setTimeSpent(12);
        assertEquals(12,target.getTimeSpent());
    }

}