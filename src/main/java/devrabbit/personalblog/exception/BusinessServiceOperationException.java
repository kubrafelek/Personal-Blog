package devrabbit.personalblog.exception;

import org.springframework.http.HttpStatus;

public class BusinessServiceOperationException {

    public BusinessServiceOperationException() {
    }

    public static class PostNotFoundException extends BaseException {
        public PostNotFoundException(String message) {
            super(message);
        }
    }

    public static class PostAlreadyDeletedException extends BaseException {
        public PostAlreadyDeletedException(String message) {
            super(message);
        }
    }

    public static class UserAlreadyExists extends BaseException {
        public UserAlreadyExists(String message) {
            super(message);
        }

        public UserAlreadyExists(String message, HttpStatus status) {
            super(message);
        }


    }
}
