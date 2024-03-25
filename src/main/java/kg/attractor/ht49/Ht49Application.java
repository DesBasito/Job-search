package kg.attractor.ht49;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
public class Ht49Application {
    public static void main(String[] args) {
        String password = "password456";

        // Create BCryptPasswordEncoder instance
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Encode the password
        String encodedPassword = passwordEncoder.encode(password);

        // Print the encoded password
        System.out.println("Encoded Password: " + encodedPassword);
        SpringApplication.run(Ht49Application.class, args);
    }
}
