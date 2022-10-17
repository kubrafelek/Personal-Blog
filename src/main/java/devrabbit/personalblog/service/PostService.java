package devrabbit.personalblog.service;

import devrabbit.personalblog.dto.PostRequestDto;
import devrabbit.personalblog.dto.PostResponseDto;

import java.util.Collection;

public interface PostService {

    PostResponseDto create(PostRequestDto postRequestDto);

    PostRequestDto getPost(Long id);

    Collection<PostResponseDto> getPosts();

    void delete(Long id);
}
