package devrabbit.personalblog.validator;

import devrabbit.personalblog.dto.SignUpRequestDto;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class SignUpRequestValidator {

    public void validate(SignUpRequestDto signUpRequestDto) {
        if (Objects.isNull(signUpRequestDto)) {
            throw new IllegalArgumentException("Sign Up Request can't be null");
        }
        if (!(StringUtils.hasLength(signUpRequestDto.username()))) {
            throw new IllegalArgumentException("Username can't be null");
        }
        if (!(StringUtils.hasLength(signUpRequestDto.password()))) {
            throw new IllegalArgumentException("Password can't be null");
        }
        if(!(signUpRequestDto.email().contains("@"))){
            throw new IllegalArgumentException("Wrong email format");
        }
    }
}
