package exercise.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Setter
@Getter
public class GuestDTO {
    private long id;

    @NotEmpty
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "^/+[0-9]{11,13}$")
    private String phoneNumber;

    @Size(min = 4, max=4)
    private String clubCard;

    @FutureOrPresent
    private LocalDate cardValidUntil;

    private LocalDate createdAt;
}
