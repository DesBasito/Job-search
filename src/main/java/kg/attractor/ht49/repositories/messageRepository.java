package kg.attractor.ht49.repositories;

import kg.attractor.ht49.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface messageRepository extends JpaRepository<Message, Long> {
}
