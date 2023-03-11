package devrabbit.personalblog.converter;

import devrabbit.personalblog.dto.UserRegisterRequest;
import devrabbit.personalblog.dto.UserRequestDto;
import devrabbit.personalblog.dto.UserResponseDto;
import devrabbit.personalblog.model.User;
import devrabbit.personalblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements UserConverter {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User toUser(UserRegisterRequest request) {

        User user = new User();
        user.setEmail(request.email());
        user.setFullname(request.fullname());
        user.setUsername(request.username());
        user.setPassword(request.password());
        user.setRoles(userRepository.findByUsername(request.username()).get().getRoles());
        return user;
    }

    @Override
    public UserRequestDto toUserRequestDto(User user) {
        return new UserRequestDto(user.getFullname(), user.getUsername(),
                user.getEmail(), user.getPassword(), user.getRoles(), user.getUserStatus());
    }

    @Override
    public UserResponseDto toUserResponseDto(User user) {
        return new UserResponseDto(user.getFullname(), user.getUsername(),
                user.getEmail(), user.getPassword(), user.getRoles(), user.getUserStatus());
    }
}
