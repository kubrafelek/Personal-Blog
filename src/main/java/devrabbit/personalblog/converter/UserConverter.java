package devrabbit.personalblog.converter;

import devrabbit.personalblog.dto.UserRegisterRequest;
import devrabbit.personalblog.dto.UserRequestDto;
import devrabbit.personalblog.dto.UserResponseDto;
import devrabbit.personalblog.model.User;

public interface UserConverter {

    User toUser(UserRegisterRequest request);

    UserRequestDto toUserRequestDto(User user);

    UserResponseDto toUserResponseDto(User user);
}
