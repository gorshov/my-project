package service.exception;

/**
 * Created by Admin
 * Date: 15.05.2017.
 * Time: 13:05
 */
public class ServiceException extends Exception {

    public ServiceException() {    }

    public ServiceException(String message, Throwable exception) {
        super(message, exception);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable exception) {
        super(exception);
    }
}
