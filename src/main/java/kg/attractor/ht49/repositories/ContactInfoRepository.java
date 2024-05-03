package kg.attractor.ht49.repositories;

import kg.attractor.ht49.models.ContactsInfo;
import kg.attractor.ht49.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactInfoRepository extends JpaRepository<ContactsInfo,Long> {
    List<ContactsInfo> findByResume(Resume resume);
}
