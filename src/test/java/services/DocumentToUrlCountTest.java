package services;

import org.bson.Document;
import org.junit.Test;

import static org.junit.Assert.*;

public class DocumentToUrlCountTest {
    @Test
    public void convert() {
        DocumentToUrlCount target = new DocumentToUrlCount();
        try{
            target.convert(new Document());
        }
        catch (Exception e){
            assertEquals("Document is null or empty", e.getMessage());
        }
    }

}