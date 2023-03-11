package devrabbit.personalblog.dto;

import devrabbit.personalblog.model.Role;
import devrabbit.personalblog.model.enums.UserStatus;

import java.util.Set;

public record UserRequestDto(String fullname,
                             String username,
                             String email,
                             String password,
                             Set<Role> roles,
                             UserStatus status) {
}
