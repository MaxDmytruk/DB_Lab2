package services;

import org.bson.Document;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class DocumentToUrlCountTest {
    @Test
    public void convert() {
        DocumentToUrlCount target = new DocumentToUrlCount();
        assertThrows(InvalidParameterException.class, () -> target.convert(new Document()));
    }

}