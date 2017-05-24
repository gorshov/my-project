package dao.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Александр Горшов on 16.05.2017.
 */
@Getter
@Setter
public class DaoException extends Exception {
    private Exception exception;
    private String message;

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
        this.message = message;
    }

    public DaoException(Exception exception, String message) {
        this.exception = exception;
        this.message = message;
    }

    public DaoException(Throwable throwable) {
        super(throwable);
    }
}
