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
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @PostMapping("/save")
    public ResponseEntity<?> create(@Validated @RequestBody PostRequestDto postDto){
        postService.create(postDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<Collection<PostResponseDto>> listAllPosts(){
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping("/get/{post_id}")
    public ResponseEntity<PostRequestDto> getPostById(@PathVariable Long postId){
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        postService.delete(id);
        return ResponseEntity.ok().build();
    }
}
