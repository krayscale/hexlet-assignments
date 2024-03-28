package exercise.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuestCreateDTO {

    @NotEmpty
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "^.+[0-9]{10,13}$")
    private String phoneNumber;

    @Size(min = 4, max=4)
    private String clubCard;

    @FutureOrPresent
    private LocalDate cardValidUntil;
}
