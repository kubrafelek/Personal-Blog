package devrabbit.personalblog.controller;

import devrabbit.personalblog.dto.PostRequestDto;
import devrabbit.personalblog.dto.PostResponseDto;
import devrabbit.personalblog.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @PostMapping("/save")
    public PostResponseDto create(@Validated @RequestBody PostRequestDto postDto) {
        return postService.create(postDto);
    }

    @GetMapping(value = "/posts")
    public ResponseEntity<Collection<PostResponseDto>> listAllPosts(){
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping(value = "/get/{postId}")
    public ResponseEntity<PostRequestDto> getPostById(@PathVariable Long postId){
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @DeleteMapping(value = "/delete/{postId}")
    public ResponseEntity<?> delete(@PathVariable Long postId){
        postService.delete(postId);
        return ResponseEntity.ok().build();
    }
}
