package devrabbit.personalblog.converter;

import devrabbit.personalblog.dto.PostRequestDto;
import devrabbit.personalblog.dto.PostResponseDto;
import devrabbit.personalblog.model.Post;
import devrabbit.personalblog.model.Review;
import devrabbit.personalblog.model.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PostConverterImpl implements PostConverter {

    @Override
    public Post toPost(PostRequestDto postRequestDto) {

        Post post = new Post();
        post.setTitle(postRequestDto.title());
        post.setBody(postRequestDto.body());
        post.setUser(new User());
        post.setReviews(null);
        post.setCreationDate(new Date());
        post.setDeleted(false);

        return post;
    }

    @Override
    public PostRequestDto toPostRequestDto(Post post) {
        return new PostRequestDto(post.getTitle(),
                post.getBody());
    }

    @Override
    public PostResponseDto toPostResponseDto(Post post) {
        return new PostResponseDto(post.getId(),
                post.getTitle(),
                post.getBody(),
                post.getCreationDate(),
                post.getReviews(),
                post.getUser(),
                post.isDeleted());
    }
}
