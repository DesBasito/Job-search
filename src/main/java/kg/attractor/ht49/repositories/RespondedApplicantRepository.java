package kg.attractor.ht49.repositories;

import kg.attractor.ht49.models.RespondedApplicant;
import kg.attractor.ht49.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RespondedApplicantRepository extends JpaRepository<RespondedApplicant,Long> {
    List<RespondedApplicant> findByVacancyId(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO RESPONDED_APPLICANTS(vacancy_id, resume_id, CONFIRMATION) values ( :resumeId, :vacancyId, false ) ")
    void createRespond(Long resumeId, Long vacancyId);
}
