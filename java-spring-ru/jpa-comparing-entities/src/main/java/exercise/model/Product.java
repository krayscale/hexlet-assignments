package exercise.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.*;

@Entity
@Data
@EqualsAndHashCode(of={"title", "price"})
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;

    private Integer price;
}