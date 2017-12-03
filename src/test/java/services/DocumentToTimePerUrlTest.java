package services;

import org.bson.Document;
import org.junit.Test;

import static org.junit.Assert.*;

public class DocumentToTimePerUrlTest {
    @Test
    public void convert() {
        DocumentToTimePerUrl target = new DocumentToTimePerUrl();
        try{
            target.convert(new Document());
        }
        catch (Exception e){
            assertEquals("Document is null or empty", e.getMessage());
        }
    }

}