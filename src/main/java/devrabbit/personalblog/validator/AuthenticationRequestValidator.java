package devrabbit.personalblog.validator;

import devrabbit.personalblog.dto.AuthenticationRequest;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class AuthenticationRequestValidator {

    public void validate(AuthenticationRequest authenticationRequest) {
        if (Objects.isNull(authenticationRequest)) {
            throw new IllegalArgumentException("AuthenticationRequest can't be null");
        }
        if (!(StringUtils.hasLength(authenticationRequest.username()))) {
            throw new IllegalArgumentException("Username can't be null");
        }
        if (!(StringUtils.hasLength(authenticationRequest.password()))) {
            throw new IllegalArgumentException("Password¬ can't be null");
        }
    }
}
