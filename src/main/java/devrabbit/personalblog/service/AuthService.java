package devrabbit.personalblog.service;

import devrabbit.personalblog.dto.UserRegisterDto;
import devrabbit.personalblog.util.AccessToken;

public interface AuthService {

    AccessToken register(UserRegisterDto userRegisterDto);
    AccessToken login(UserRegisterDto userRegisterDto);
}
