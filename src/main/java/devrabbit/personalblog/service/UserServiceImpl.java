package devrabbit.personalblog.service;

import devrabbit.personalblog.converter.UserConverter;
import devrabbit.personalblog.dto.UserResponseDto;
import devrabbit.personalblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public Collection<UserResponseDto> getUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userConverter::toUserResponseDto)
                .toList();
    }
}
