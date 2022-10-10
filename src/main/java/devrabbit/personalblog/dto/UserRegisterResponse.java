package devrabbit.personalblog.dto;

import devrabbit.personalblog.model.Role;

import java.util.Set;

public record UserRegisterResponse(String token, Set<Role> roles) {
}

