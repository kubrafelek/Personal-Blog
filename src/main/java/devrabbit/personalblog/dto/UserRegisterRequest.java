package devrabbit.personalblog.dto;

public record UserRegisterRequest(String fullname, String username, String email, String password) {
}
