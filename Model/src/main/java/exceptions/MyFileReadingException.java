package exceptions;

public class MyFileReadingException extends Exception {

    public MyFileReadingException() {
        super();
    }

    public MyFileReadingException(String message) {
        super(message);
    }

    public MyFileReadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
