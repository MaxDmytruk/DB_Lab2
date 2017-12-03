package services;

import org.bson.Document;

import java.security.InvalidParameterException;
import java.util.HashMap;

public class DocumentToTimePerUrl {
    public HashMap<String, String> convert(Document document){
        if(document == null){
            throw new InvalidParameterException("Document is null or empty");
        }
        if(!document.containsKey("url")){
            throw new InvalidParameterException("Document is null or empty");
        }
        HashMap<String, String> result = new HashMap<>();
        int startIndex;
        int endIndex;
        String stringDocument = document.toString();
        for (int i = 0; i < 2; i++) {
            startIndex = stringDocument.indexOf('{') + 1;
            endIndex = stringDocument.lastIndexOf('}');
            stringDocument = stringDocument.substring(startIndex, endIndex);
        }
        String[] keyValuePair = stringDocument.split(",");
        result.put(keyValuePair[0].trim(), keyValuePair[1].trim());
        return result;
    }
}
