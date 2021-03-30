package exceptions;

public class NoSuchFileException extends MyFileReadingException {
    public NoSuchFileException() {
        super();
    }

    public NoSuchFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
