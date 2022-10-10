package devrabbit.personalblog.service;

import devrabbit.personalblog.dto.UserRegisterRequest;
import devrabbit.personalblog.dto.UserRegisterResponse;
import devrabbit.personalblog.exception.BusinessServiceOperationException;
import devrabbit.personalblog.helper.JWTHelper;
import devrabbit.personalblog.model.Role;
import devrabbit.personalblog.model.User;
import devrabbit.personalblog.repository.RoleRepository;
import devrabbit.personalblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTHelper jwtHelper;

    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
        checkUserExists(request);

        User user = new User();
        user.setEmail(request.email());
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRoles(getRoles(new String[]{"ROLE_USER"}));

        userRepository.save(user);

        String token = jwtHelper.generate(request.username());
        Set<Role> roles = user.getRoles();

        return new UserRegisterResponse(token, roles);
    }

    private Set<Role> getRoles(String[] roles) {
        Set<Role> userRoles = new HashSet<>();
        for (String role : roles) {
            userRoles.add(roleRepository.findByName(role));
        }
        return userRoles;
    }

    private void checkUserExists(UserRegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new BusinessServiceOperationException.UserAlreadyExists("User exists with username.", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new BusinessServiceOperationException.UserAlreadyExists("User exists with email.", HttpStatus.BAD_REQUEST);
        }
    }
}
