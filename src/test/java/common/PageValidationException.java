package common;

public class PageValidationException extends RuntimeException {
    public PageValidationException(String message) {
        super(message);
    }

    public PageValidationException(String message, Exception e) {
        super(message, e);
    }
}
