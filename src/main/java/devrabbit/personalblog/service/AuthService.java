package devrabbit.personalblog.service;

import devrabbit.personalblog.dto.UserLoginRequest;
import devrabbit.personalblog.dto.UserLoginResponse;
import devrabbit.personalblog.dto.UserRegisterRequest;
import devrabbit.personalblog.dto.UserRegisterResponse;

public interface AuthService {
    UserRegisterResponse register(UserRegisterRequest userRegisterDto);

    UserLoginResponse login(UserLoginRequest request);

}
