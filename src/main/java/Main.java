import com.mongodb.Block;
import com.mongodb.InsertOptions;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import models.ServerLog;
import org.bson.Document;
import repositories.ServerLogRepository;
import services.CsvToLogService;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.*;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

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

        CsvToLogService converter = new CsvToLogService();
        ServerLog test = converter.convert("");

//        repo.getIpTimeSpent();
//        Map<String, Integer> test = repo.getUrlCountInPeriod(startDate, endDate);

//        ArrayList<String> urls = repo.getUrlsByIp("1.1.1");
//        repo.getUrlSumTimeSpent();
//        repo.getIpsByUrl("http://dot.com");
//        repo.deleteServerLog(log);
//        repo.updateServerLog(log, updatedLog);
//        repo.insertServerLog(log);
//        repo.insertServerLog(updatedLog);
    }
}
