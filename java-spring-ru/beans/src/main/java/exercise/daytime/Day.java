package exercise.daytime;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Day implements Daytime {
    private String name = "day";

    public String getName() {
        return name;
    }

    @PostConstruct
    public void init() {
        System.out.println("Bean is initialized!");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Bean is destroyed!");
    }
}
