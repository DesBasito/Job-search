package kg.attractor.ht49.repositories;

import kg.attractor.ht49.models.RespondedApplicant;
import kg.attractor.ht49.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RespondedApplicantRepository extends JpaRepository<RespondedApplicant,Long> {
    List<RespondedApplicant> findByVacancyId(Long id);

}
