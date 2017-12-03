package services;

import org.bson.Document;
import org.junit.Test;

import static org.junit.Assert.*;

public class DocumentToIpCountTest {
    @Test
    public void convert() {
        DocumentToIpCount target = new DocumentToIpCount();
        try{
            target.convert(new Document());
        }
        catch (Exception e){
            assertEquals("Document is null or empty", e.getMessage());
        }
    }

}