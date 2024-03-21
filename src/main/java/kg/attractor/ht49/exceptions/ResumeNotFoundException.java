package kg.attractor.ht49.exceptions;

import java.util.NoSuchElementException;

public class ResumeNotFoundException extends NoSuchElementException {
    public ResumeNotFoundException() {
    }

    public ResumeNotFoundException(String message) {
        super(message);
    }
}
