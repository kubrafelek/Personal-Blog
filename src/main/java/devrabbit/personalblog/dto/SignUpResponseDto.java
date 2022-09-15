package devrabbit.personalblog.dto;

import java.util.Set;

public record SignUpResponseDto(String token, Set<String> roles) {

}
