package devrabbit.personalblog.service;

import devrabbit.personalblog.dto.UserRegisterRequest;
import devrabbit.personalblog.dto.UserRegisterResponse;
import devrabbit.personalblog.model.User;

public interface AuthService {
    UserRegisterResponse register(UserRegisterRequest userRegisterDto);

    //AccessToken login(UserLoginDto userLoginDto);
}
