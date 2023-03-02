package devrabbit.personalblog.dto;

import devrabbit.personalblog.model.Review;
import devrabbit.personalblog.model.User;

import java.util.Collection;
import java.util.Date;

public record PostResponseDto(
        Long id,
        String title,
        String body,
        Date creationDate,
        Collection<Review> reviews,
        Long authorId,
        boolean isDeleted
) {}