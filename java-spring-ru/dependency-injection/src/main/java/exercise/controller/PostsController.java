package exercise.controller;

import exercise.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

@AllArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostsController {

    private final PostRepository repository;
    private final CommentRepository commentRepository;

    @GetMapping
    public List<Post> getPosts() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id "+id+" not found"));
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        Post post1 = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("" + id));
        post1.setTitle(post.getTitle());
        post1.setBody(post.getBody());
        return repository.save(post1);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost( @RequestBody Post post) {
        return repository.save(post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        commentRepository.deleteByPostId(id);
        repository.deleteById(id);
    }
}
