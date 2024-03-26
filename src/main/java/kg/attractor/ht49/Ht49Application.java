package kg.attractor.ht49;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
public class Ht49Application {
    public static void main(String[] args) {
        SpringApplication.run(Ht49Application.class, args);
    }
}
