package kg.attractor.ht49.repositories;

import kg.attractor.ht49.models.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactTypeRepository extends JpaRepository<ContactType,Long> {
    Optional<ContactType> findContactTypeByType(String type);

    Optional<ContactType> findByType(String type);
}
