package kg.attractor.ht49;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Ht49Application {

    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String storedHash = "$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2";
        String candidatePassword = "qwer";

        if (encoder.matches(storedHash,candidatePassword)) {
            System.out.println("Password match!");
        } else {
            System.out.println("Password does not match.");
        }
        SpringApplication.run(Ht49Application.class, args);
    }
}

