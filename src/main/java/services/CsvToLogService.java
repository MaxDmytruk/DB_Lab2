package services;

import models.ServerLog;

import java.util.HashMap;
import java.util.Map;

public class CsvToLogService {
    public ServerLog convert(String stringToConvert) throws IndexOutOfBoundsException{
        Map<String, String> fieldResultDictionary = new HashMap<>();
        int beginCut = stringToConvert.indexOf('{') + 1;
        int finishCut = stringToConvert.lastIndexOf('}');
        stringToConvert = stringToConvert.substring(beginCut, finishCut);
        beginCut = stringToConvert.indexOf('{') + 1;
        finishCut = stringToConvert.lastIndexOf('}');
        stringToConvert = stringToConvert.substring(beginCut, finishCut);
        String[] fields = stringToConvert.split(",");

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
