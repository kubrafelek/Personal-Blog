package devrabbit.personalblog.converter;

import devrabbit.personalblog.dto.PostRequestDto;
import devrabbit.personalblog.dto.PostResponseDto;
import devrabbit.personalblog.model.Post;

public interface PostConverter {

    Post toPost(PostRequestDto postRequestDto);

    PostRequestDto toPostRequestDto(Post post);

    PostResponseDto toPostResponseDto(Post post);
}
