package services;

import models.ServerLog;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class CsvToLogService {
    public ServerLog convert(String csv) throws IndexOutOfBoundsException{
        Map<String, String> fieldResultDictionary = new HashMap<>();
        int beginCut = csv.indexOf('{') + 1;
        int finishCut = csv.lastIndexOf('}');
        csv = csv.substring(beginCut, finishCut);
        beginCut = csv.indexOf('{') + 1;
        finishCut = csv.lastIndexOf('}');
        csv = csv.substring(beginCut, finishCut);
        String[] fields = csv.split(",");

        for (String fieldResultPair : fields) {
            String[] field = fieldResultPair.split("=");
            fieldResultDictionary.put(field[0].trim(), field[1].trim());
        }

        ServerLog resultLog = new ServerLog();

        resultLog.setUrl(fieldResultDictionary.get("url").trim());
        resultLog.setIp(fieldResultDictionary.get("ip").trim());
        resultLog.setTimeSpent(Long.parseLong(fieldResultDictionary.get("time_spent").trim()));

        return resultLog;
    }
}
