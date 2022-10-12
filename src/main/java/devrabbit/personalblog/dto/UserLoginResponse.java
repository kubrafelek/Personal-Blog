package devrabbit.personalblog.dto;

import devrabbit.personalblog.model.Role;

import java.util.Set;

public record UserLoginResponse(String username, String token, Set<Role> roles) {
}
