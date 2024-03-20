package exercise;

import exercise.model.Post;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/posts")
    public List<Post> getPosts(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {
        return posts.stream().skip(page).limit(limit).toList();
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> getPost(@PathVariable String id) {
        return posts.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst();
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("/posts/{id}")
    public Post updatePost(@PathVariable String id, @RequestBody Post post) {
        Optional<Post> mPost = posts.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst();
        if (mPost.isPresent()) {
            Post data = mPost.get();
            data.setId(post.getId());
            data.setBody(post.getBody());
            data.setTitle(post.getTitle());
            return data;
        }
        return null;
    }

    @DeleteMapping("/posts/{id}")
    public void deletePort(@PathVariable String id) {
        posts.removeIf(p -> Objects.equals(p.getId(), id));
    }
}
