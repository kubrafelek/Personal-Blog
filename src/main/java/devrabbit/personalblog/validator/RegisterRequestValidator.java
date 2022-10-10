package devrabbit.personalblog.validator;

import devrabbit.personalblog.dto.UserRegisterRequest;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class RegisterRequestValidator {

    public void validate(UserRegisterRequest request){
        if (Objects.isNull(request)) {
            throw new IllegalArgumentException("Request can't be null");
        }
        if (!(StringUtils.hasLength(request.email()))) {
            throw new IllegalArgumentException("Email can't be null");
        }
        if (!(StringUtils.hasLength(request.username()))) {
            throw new IllegalArgumentException("Username can't be null");
        }
        if (!(StringUtils.hasLength(request.password()))) {
            throw new IllegalArgumentException("Password¬ can't be null");
        }
    }
}
