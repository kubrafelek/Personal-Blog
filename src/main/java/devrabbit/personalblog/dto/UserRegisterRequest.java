package devrabbit.personalblog.dto;

public record UserRegisterRequest(String fullName, String username, String email, String password) {
}
