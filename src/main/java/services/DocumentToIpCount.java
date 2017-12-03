package services;

import org.bson.Document;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class DocumentToIpCount {
    public Map<String, String> convert(Document document) throws InvalidParameterException{
        if(document == null){
            throw new InvalidParameterException("Document is null or empty");
        }
        if(!document.containsKey("url")){
            throw new InvalidParameterException("Document is null or empty");
        }
        Map<String,String> resultSet = new HashMap<>();
        int startIndex;
        int endIndex;
        String stringDocument = document.toString();
        for (int i = 0; i < 4; i++) {
            startIndex = stringDocument.indexOf('{') + 1;
            endIndex = stringDocument.lastIndexOf('}');
            if(startIndex > -1 && endIndex > -1) {
                stringDocument = stringDocument.substring(startIndex, endIndex);
            }
        }
        String[] fields = stringDocument.split(",");
        HashMap<String, String> dictionary = new HashMap<>();
        for (String field : fields) {
            String[] keyValuePair = field.split("=");
            dictionary.put(keyValuePair[0].trim(), keyValuePair[1].trim());
        }
        if(dictionary.size() == 3) {
            String valueString = new StringBuilder("Count: ")
                    .append(dictionary.get("count"))
                    .append("; Time:")
                    .append(dictionary.get("time"))
                    .append(";")
                    .toString();
            resultSet.put(dictionary.get("ip"), valueString);
        }
        else{
            resultSet.put(dictionary.get("_id"), dictionary.get("value"));
        }
        return resultSet;
    }
}
