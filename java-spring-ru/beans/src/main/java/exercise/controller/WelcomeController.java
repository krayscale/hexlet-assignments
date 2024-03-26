package exercise.controller;

import exercise.daytime.Daytime;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@RestController
public class WelcomeController {

    private final Daytime daytime;

    @GetMapping("/welcome")
    public String welcome() {
        return daytime.getName();
    }
}
