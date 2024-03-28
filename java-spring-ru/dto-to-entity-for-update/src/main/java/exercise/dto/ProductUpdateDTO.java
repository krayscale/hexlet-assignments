package exercise.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDTO {
    private String title;
    private int price;
}
