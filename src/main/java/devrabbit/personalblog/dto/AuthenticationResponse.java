package devrabbit.personalblog.dto;

import java.util.Set;

public record AuthenticationResponse(String token, Set<String> roles) {
}
