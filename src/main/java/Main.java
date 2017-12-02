import models.ServerLog;
import repositories.ServerLogRepository;

import java.sql.Timestamp;
import java.util.*;

public class Main {
    public static void main(String[] args){
        Calendar firstTimestamp = Calendar.getInstance();
        firstTimestamp.set(2017, Calendar.NOVEMBER, 26, 10, 40, 24);
        ServerLog log = new ServerLog();
        log.setIp("1.1.1");
        log.setUrl("http://lol.com");
        log.setTimestamp(new Timestamp(firstTimestamp.getTimeInMillis()));
        log.setTimeSpent(111);

        Calendar secondTimestamp = Calendar.getInstance();
        secondTimestamp.set(2017, Calendar.NOVEMBER, 26, 12, 05, 28);
        ServerLog updatedLog = new ServerLog();
        updatedLog.setIp("2.2.2");
        updatedLog.setUrl("http://dot.com");
        updatedLog.setTimestamp(new Timestamp(secondTimestamp.getTimeInMillis()));
        updatedLog.setTimeSpent(222);

        Calendar startDate = Calendar.getInstance();
        startDate.set(2017, Calendar.NOVEMBER, 25);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2017, Calendar.NOVEMBER, 27);

        ServerLogRepository repo = new ServerLogRepository();

        ArrayList<String> urls = repo.getUrlsByIp("1.1.1");
    }
}
