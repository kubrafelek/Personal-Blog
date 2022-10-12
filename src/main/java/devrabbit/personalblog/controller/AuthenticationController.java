package devrabbit.personalblog.controller;

import devrabbit.personalblog.dto.UserLoginRequest;
import devrabbit.personalblog.dto.UserRegisterRequest;
import devrabbit.personalblog.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
}
