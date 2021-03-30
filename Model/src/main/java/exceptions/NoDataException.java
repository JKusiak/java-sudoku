package exceptions;

public class NoDataException extends MyFileReadingException {

    public NoDataException() {
        super();
    }

    public NoDataException(String message) {
        super(message);
    }
}
