package kg.attractor.ht49.exceptions;

public class AlreadyExistsException extends IllegalArgumentException{
    public AlreadyExistsException() {
    }

    public AlreadyExistsException(String s) {
        super(s);
    }
}
