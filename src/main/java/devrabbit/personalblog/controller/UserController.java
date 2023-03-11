package devrabbit.personalblog.controller;

import devrabbit.personalblog.dto.UserResponseDto;
import devrabbit.personalblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

   @GetMapping(value = "/list")
   public ResponseEntity<Collection<UserResponseDto>> listAllUsers(){
       return ResponseEntity.ok(userService.getUsers());
   }
}
