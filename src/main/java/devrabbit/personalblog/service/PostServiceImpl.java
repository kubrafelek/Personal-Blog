package devrabbit.personalblog.service;

import devrabbit.personalblog.converter.PostConverter;
import devrabbit.personalblog.dto.PostRequestDto;
import devrabbit.personalblog.dto.PostResponseDto;
import devrabbit.personalblog.exception.BaseException;
import devrabbit.personalblog.exception.BusinessServiceOperationException;
import devrabbit.personalblog.model.Post;
import devrabbit.personalblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostConverter postConverter;

    @Override
    public PostResponseDto create(PostRequestDto postRequestDto) {
        Post post = postConverter.toPost(postRequestDto);
        postRepository.save(post);
        return new PostResponseDto(post.getId(), post.getTitle(), post.getBody(), post.getCreationDate(),
                post.getReviews(), post.getUser().getId(), post.isDeleted());
    }

    @Override
    public PostRequestDto getPost(Long id) throws BaseException {
        Post post = postRepository.findById(id).orElseThrow(() -> new BusinessServiceOperationException.PostNotFoundException("Post not found"));
        return postConverter.toPostRequestDto(post);
    }

    @Override
    public Collection<PostResponseDto> getPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postConverter::toPostResponseDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.PostNotFoundException("Post not found"));

        if (post.isDeleted()) {
            throw new BusinessServiceOperationException.PostAlreadyDeletedException("Post already deleted");
        }

        postRepository.delete(post);
        post.setDeleted(true);
        postRepository.save(post);
    }
}
