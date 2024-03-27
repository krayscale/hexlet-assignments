package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.stream.Collectors;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

@RestController
@AllArgsConstructor
@RequestMapping("/posts")
public class PostsController {

    private final PostRepository repository;
    private final CommentRepository commentRepository;

    @GetMapping()
    public List<PostDTO> posts() {

        var posts = repository.findAll();
        List<PostDTO> collect = posts.stream().map(x -> mapper(x,
                commentRepository
                        .findByPostId(x.getId())
                        .stream()
                        .map(this::mapper)
                        .collect(Collectors.toList())
        )).collect(Collectors.toList());
        return collect;
    }

    @GetMapping("/{id}")
    public PostDTO post(@PathVariable Long id) {

        var post = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        var comments = commentRepository.findByPostId(post.getId())
                .stream().map(this::mapper).collect(Collectors.toList());

        return mapper(post, comments);
    }


    private PostDTO mapper(Post post, List<CommentDTO> comments) {
        return PostDTO.builder()
                .body(post.getBody())
                .title(post.getTitle())
                .id(post.getId())
                .comments(comments).build();
    }

    private CommentDTO mapper(Comment comment) {
        return CommentDTO.builder()
                .id(comment.getId())
                .body(comment.getBody())
                .build();
    }
}
