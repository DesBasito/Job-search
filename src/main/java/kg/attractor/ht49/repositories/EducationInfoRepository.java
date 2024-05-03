package kg.attractor.ht49.repositories;

import kg.attractor.ht49.models.EducationInfo;
import kg.attractor.ht49.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationInfoRepository extends JpaRepository<EducationInfo,Long> {
    List<EducationInfo> findByResume(Resume resume);
}

