package kg.attractor.ht49.repositories;

import kg.attractor.ht49.models.Resume;
import kg.attractor.ht49.models.WorkExperienceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkExperienceInfoRepository extends JpaRepository<WorkExperienceInfo,Long> {
    List<WorkExperienceInfo> findByResume(Resume resume);
}
