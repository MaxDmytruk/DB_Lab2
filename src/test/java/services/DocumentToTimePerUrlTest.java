package services;

import org.bson.Document;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class DocumentToTimePerUrlTest {
    @Test
    void convert() {
        DocumentToTimePerUrl target = new DocumentToTimePerUrl();
        assertThrows(InvalidParameterException.class, () -> target.convert(new Document()));
    }

}