/**
 * Created by Tal Taub on 19/02/2017.
 */
public class NotInBoardException extends Exception{

    public NotInBoardException() {
        super("NotInBoardException");
    }

    public NotInBoardException(String message) {
        super(message);
    }

    public NotInBoardException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotInBoardException(Throwable cause) {
        super(cause);
    }

    public NotInBoardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
