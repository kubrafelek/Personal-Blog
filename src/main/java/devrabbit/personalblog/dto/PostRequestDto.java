package devrabbit.personalblog.dto;

public record PostRequestDto(
        Long authorId,
        String title,
        String body
) {
}
