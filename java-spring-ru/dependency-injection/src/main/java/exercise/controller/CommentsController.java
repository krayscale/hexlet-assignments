package exercise.controller;

import exercise.model.Post;
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

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@AllArgsConstructor
@RequestMapping("/comments")
public class CommentsController {
    private final CommentRepository repository;

    @GetMapping
    public List<Comment> getPosts() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Comment getPost(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment with id "+id+" not found"));
    }

    @PutMapping("/{id}")
    public Comment updatePost(@PathVariable Long id, @RequestBody Comment comment) {
        Comment comment1 = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("" + id));
        comment1.setBody(comment.getBody());
        return repository.save(comment1);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createPost( @RequestBody Comment comment) {
        return repository.save(comment);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        repository.deleteById(id);
    }
}