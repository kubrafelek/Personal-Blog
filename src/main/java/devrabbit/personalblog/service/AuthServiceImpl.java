package devrabbit.personalblog.service;

import devrabbit.personalblog.dto.*;
import devrabbit.personalblog.exception.BaseException;
import devrabbit.personalblog.exception.BusinessServiceOperationException;
import devrabbit.personalblog.helper.JWTHelper;
import devrabbit.personalblog.model.Role;
import devrabbit.personalblog.model.User;
import devrabbit.personalblog.repository.RoleRepository;
import devrabbit.personalblog.repository.UserRepository;
import devrabbit.personalblog.security.UserDetail;
import devrabbit.personalblog.validator.AuthenticationRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Time;
import java.time.Instant;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Autowired
    private AuthenticationManager authenticationManager;

    private final AuthenticationRequestValidator authenticationRequestValidator
            = new AuthenticationRequestValidator();

    @PostMapping(path = "/sign-in")
    public ResponseEntity<?> signIn(@RequestBody AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.username(), authenticationRequest.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        String token = jwtHelper.generate(authenticationRequest.username());
        return ResponseEntity.ok(new AuthenticationResponse(token,
                userDetail
                        .getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toSet())));
    }

    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
        checkUserExists(request);

        User user = new User();
        user.setEmail(request.email());
        user.setUsername(request.username());
        user.setFullname(request.fullname());
        user.setLastSuccessfullyLoggedInTime(new Date(Date.from(Instant.now()).getTime()));
        user.setLastFailureLoggedInTime(new Date(Date.from(Instant.now()).getTime()));
        user.setPassword(passwordEncoder.encode(request.password()));

        Role role = new Role();
        role.setName("ROLE_USER");
        user.addRoles(Set.of(role));

        userRepository.save(user);

        String token = jwtHelper.generate(request.username());
        Set<Role> roles = user.getRoles();

        return new UserRegisterResponse(token, roles);
    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        authenticationRequestValidator.validate(request);

        String username = request.username();
        String password = request.password();

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
         //   UserDetail userDetail = (UserDetail) authentication.getPrincipal();
            Set<Role> roles = userRepository.findByUsername(username).get().getRoles();
            String token = jwtHelper.generate(username);
            return new UserLoginResponse(username, token, roles);
        } catch (AuthenticationException exception) {
            throw new BaseException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
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
