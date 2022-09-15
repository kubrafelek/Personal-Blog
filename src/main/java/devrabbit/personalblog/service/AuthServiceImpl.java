package devrabbit.personalblog.service;

import devrabbit.personalblog.dto.UserRegisterDto;
import devrabbit.personalblog.exception.BaseException;
import devrabbit.personalblog.exception.BusinessServiceOperationException;
import devrabbit.personalblog.repository.RoleRepository;
import devrabbit.personalblog.repository.UserRepository;
import devrabbit.personalblog.util.AccessToken;
import devrabbit.personalblog.util.security.ITokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private ITokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AccessToken register(UserRegisterDto userRegisterDto) {
        checkUserExistsWithUserName(userRegisterDto.username());
        return null;
    }

    private void checkUserExistsWithUserName(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new BusinessServiceOperationException.UserAlreadyExists("User already exists", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public AccessToken login(UserRegisterDto userRegisterDto) {
        return null;
    }
}
