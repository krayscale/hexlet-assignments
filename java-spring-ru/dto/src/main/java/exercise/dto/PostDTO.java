package exercise.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private long id;

    private String title;

    private String body;

    @Builder.Default
    private List<CommentDTO> comments = new ArrayList<>();
}
