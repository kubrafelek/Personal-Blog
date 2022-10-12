package devrabbit.personalblog.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {

    private String message;
    private HttpStatus status;

    public BaseException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public BaseException(String message) {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
