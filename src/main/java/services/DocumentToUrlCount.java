package services;

import org.bson.Document;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class DocumentToUrlCount {
    public Map<String, Integer> convert(Document document){
        if(document == null){
            throw new InvalidParameterException();
        }
        if(!document.containsKey("url")){
            throw new InvalidParameterException();
        }
        Map<String,Integer> resultSet = new HashMap<>();
        int startIndex;
        int endIndex;
        String stringDocument = document.toString();
        for (int i = 0; i < 2; i++) {
            startIndex = stringDocument.indexOf('{') + 1;
            endIndex = stringDocument.lastIndexOf('}');
            stringDocument = stringDocument.substring(startIndex, endIndex);
        }
        String[] fields = stringDocument.split(",");
        HashMap<String, String> dictionary = new HashMap<>();
        for (String field : fields) {
            String[] keyValuePair = field.split("=");
            dictionary.put(keyValuePair[0].trim(), keyValuePair[1].trim());
        }
        resultSet.put(dictionary.get("_id"), Double.valueOf(dictionary.get("value")).intValue());
        return resultSet;
    }

}
