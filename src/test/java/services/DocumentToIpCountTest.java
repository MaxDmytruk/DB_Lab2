package services;

import org.bson.Document;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;
import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class DocumentToIpCountTest {
    @Test
    void convert() {
        DocumentToIpCount target = new DocumentToIpCount();
        assertThrows(InvalidParameterException.class, () -> target.convert(new Document()));
    }

}