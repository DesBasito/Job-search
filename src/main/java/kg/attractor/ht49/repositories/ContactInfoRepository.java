package kg.attractor.ht49.repositories;

import kg.attractor.ht49.models.ContactsInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactInfoRepository extends JpaRepository<ContactsInfo,Long> {
}
