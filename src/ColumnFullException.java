/**
 * Created by Tal Taub on 19/02/2017.
 */
public class ColumnFullException extends Exception {

    public ColumnFullException() {
    }

    public ColumnFullException(String message) {
        super(message);
    }

    public ColumnFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public ColumnFullException(Throwable cause) {
        super(cause);
    }

    public ColumnFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
