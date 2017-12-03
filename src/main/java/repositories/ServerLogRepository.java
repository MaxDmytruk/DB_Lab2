package repositories;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import models.ServerLog;
import org.bson.Document;
import services.*;

import java.net.ConnectException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

public class ServerLogRepository {
    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection logsCollection;

    public void connect() throws MongoTimeoutException {
        MongoClientOptions mongoBuilder = new MongoClientOptions.Builder().connectTimeout(1000).build();
        client = new MongoClient("localhost", mongoBuilder);
        try{
            client.getAddress();
        }
        catch (Exception e){
            client.close();
            throw new MongoTimeoutException("start your server");
        }
        database = client.getDatabase("DB_Lab_2");
        logsCollection = database.getCollection("Server-Logs");
    }

    public void insertServerLog(ServerLog log){
        try {
            connect();
        } catch (MongoTimeoutException e) {
            throw new MongoTimeoutException("start your server");
        }
        Document doc = new Document("url", log.getUrl())
                .append("ip", log.getIp())
                .append("timestamp", log.getTimestamp())
                .append("time_spent", log.getTimeSpent());
        logsCollection.insertOne(doc);
        System.out.println("open ex");
        client.close();
    }

    public void updateServerLog(ServerLog log, ServerLog newLog){
        try {
            connect();
        } catch (MongoTimeoutException e) {
            throw new MongoTimeoutException("start your server");
        }

        Document docToUpdate = new Document("url", log.getUrl())
                .append("ip", log.getIp())
                .append("time_spent", log.getTimeSpent());

        Document updatedDoc = new Document("url", newLog.getUrl())
                .append("ip", newLog.getIp())
                .append("timestamp", newLog.getTimestamp())
                .append("time_spent", newLog.getTimeSpent());

        logsCollection.updateOne(docToUpdate, new Document("$set", updatedDoc));
        client.close();
    }

    public void deleteServerLog(ServerLog logToDelete){
        try {
            connect();
        } catch (MongoTimeoutException e) {
            throw new MongoTimeoutException("start your server");
        }
        Document document = new Document("url", logToDelete.getUrl())
                .append("ip", logToDelete.getIp())
                .append("timestamp", logToDelete.getTimestamp())
                .append("time_spent", logToDelete.getTimeSpent());

        logsCollection.deleteOne(document);
        client.close();
    }

    public ArrayList<ServerLog> getAllServerLogs(){
        try {
            connect();
        } catch (MongoTimeoutException e) {
            throw new MongoTimeoutException("start your server");
        }
        ArrayList<ServerLog> logs = new ArrayList<>();
        CsvToLogService convertService = new CsvToLogService();

        for(Object document : logsCollection.find()){
            ServerLog log = convertService.convert(document.toString());
            logs.add(log);
        }

        client.close();
        return logs;
    }

    public ArrayList<String> getIpsByUrl(String url){
        try {
            connect();
        } catch (MongoTimeoutException e) {
            throw new MongoTimeoutException("start your server");
        }
        ArrayList<String> ips = new ArrayList<>();
        for(Object ipObject : logsCollection.find(eq("url", url)).projection(fields(include("ip"), excludeId()))){
            Document doc = (Document)ipObject;
            ips.add(doc.getString("ip"));
        }
        return ips;
    }

    public ArrayList<String> getUrlsByPeriod(Calendar start, Calendar end){
        ArrayList<String> urls = new ArrayList<>();
        CalendarToTimestamp timestampConverter = new CalendarToTimestamp();
        Timestamp startTimestamp = timestampConverter.convert(start);
        Timestamp endTimestamp = timestampConverter.convert(end);

        try {
            connect();
        } catch (MongoTimeoutException e) {
            throw new MongoTimeoutException("start your server");
        }

        for(Object urlObject : logsCollection.find(and(gte("timestamp", startTimestamp), lte("timestamp", endTimestamp))).projection(fields(include("url"), excludeId()))){
            Document document = (Document)urlObject;
            urls.add(document.getString("url"));
        }

        return urls;
    }

    public ArrayList<String> getUrlsByIp(String ip){
        try {
            connect();
        } catch (MongoTimeoutException e) {
            throw new MongoTimeoutException("start your server");
        }
        ArrayList<String> urls = new ArrayList<>();
        for (Object urlObject : logsCollection.find(eq("ip", ip)).projection(fields(include("url"), excludeId()))){
            Document urlDocument = (Document)urlObject;
            urls.add(urlDocument.getString("url"));
        }
        client.close();
        return urls;
    }

    public Map<String, String> getUrlSumTimeSpent(){
        try {
            connect();
        } catch (MongoTimeoutException e) {
            throw new MongoTimeoutException("start your server");
        }
        DocumentToTimePerUrl converter = new DocumentToTimePerUrl();
        Map<String, String> urlTimeSpent = new HashMap<>();
        String mapFunction = "function (){emit(this.url, this.time_spent);}";
        String reduceFunction = "function(keyUrl, valuesTime) {return Array.sum(valuesTime);}";

        logsCollection.mapReduce(mapFunction, reduceFunction).collectionName("time_per_url").toCollection();
        MongoCollection collection = database.getCollection("time_per_url");

        for (Object object : collection.find().sort(new Document("value", -1))){
            Document document = (Document) object;
            urlTimeSpent.putAll(converter.convert(document));
        }
        client.close();
        return urlTimeSpent;
    }

    public Map<String, Integer> getUrlCount(){
        try {
            connect();
        } catch (MongoTimeoutException e) {
            throw new MongoTimeoutException("start your server");
        }
        DocumentToUrlCount converter = new DocumentToUrlCount();
        Map<String, Integer> resultSet = new HashMap<>();
        String mapFunction = "function (){emit(this.url, 1);}";
        String reduceFunction = "function(keyUrl, valuesTime) {return Array.sum(valuesTime);}";

        logsCollection.mapReduce(mapFunction, reduceFunction).collectionName("url_count").toCollection();
        MongoCollection collection = database.getCollection("url_count");

        for (Object object : collection.find().sort(new Document("value", -1))){
            Document document = (Document) object;
            resultSet.putAll(converter.convert(document));
        }

        client.close();
        return resultSet;
    }

    public Map<String,Integer> getUrlCountInPeriod(Calendar start, Calendar end){
        try {
            connect();
        } catch (MongoTimeoutException e) {
            throw new MongoTimeoutException("start your server");
        }
        CalendarToTimestamp dateConverter = new CalendarToTimestamp();
        Timestamp startDate = dateConverter.convert(start);
        Timestamp endDate = dateConverter.convert(end);
        DocumentToUrlCount converter = new DocumentToUrlCount();

        Map<String, Integer> resultSet = new HashMap<>();
        String mapFunc = "function (){var ticks = this.timestamp.getTime(); if((ticks >= "
                + startDate.getTime() + ") && (ticks <= "
                + endDate.getTime() + "))emit(this.url, 1);}";
        String reduceFunc = "function(keyUrl, valuesCount) {return Array.sum(valuesCount);}";

        logsCollection.mapReduce(mapFunc, reduceFunc).collectionName("url_in_period_count").toCollection();
        MongoCollection collection = database.getCollection("url_in_period_count");

        for (Object object : collection.find().sort(new Document("value", -1))){
            Document document = (Document) object;
            resultSet.putAll(converter.convert(document));
        }

        client.close();
        return resultSet;
    }

    public Map<String, String> getIpTimeSpent(){
        try {
            connect();
        } catch (MongoTimeoutException e) {
            throw new MongoTimeoutException("start your server");
        }
        DocumentToIpCount converter = new DocumentToIpCount();

        Map<String, String> resultSet = new HashMap<>();
        String mapFunction = "function (){emit(this.ip, this.time_spent);}";
        String reduceFunction = new StringBuilder("function(keyIp, values) ")
                .append("{return {ip:keyIp, count:values.length, time:Array.sum(values)}; }")
                .toString();

        logsCollection.mapReduce(mapFunction, reduceFunction).collectionName("time_per_ip").toCollection();
        MongoCollection collection = database.getCollection("time_per_ip");

        for (Object object : collection.find().sort(new Document("_id", 1)).sort(new Document("value", -1))){
            Document document = (Document) object;
            resultSet.putAll(converter.convert(document));
        }

        client.close();
        return resultSet;
    }
}
