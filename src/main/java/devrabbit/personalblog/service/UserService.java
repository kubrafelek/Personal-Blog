package devrabbit.personalblog.service;

import devrabbit.personalblog.dto.UserResponseDto;

import java.util.Collection;

public interface UserService {

    Collection<UserResponseDto> getUsers();
}
