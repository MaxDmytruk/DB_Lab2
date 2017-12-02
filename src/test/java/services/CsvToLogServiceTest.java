package services;

import models.ServerLog;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

 class CsvToLogServiceTest {
    @Test
    public void convert() {
        CsvToLogService converter = new CsvToLogService();
        String testDoc = "Document{{_id=5a1ab1f06720861b80912b95, url=http://test.com, ip=1.1.1, timestamp=Sun Nov 26 10:40:24 EET 2017, time_spent=111}}";
        ServerLog result = converter.convert(testDoc);
        assertEquals("http://test.com", result.getUrl());
        assertEquals("1.1.1", result.getIp());
        assertEquals(111, result.getTimeSpent());
    }

}