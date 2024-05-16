package kg.attractor.ht49.repositories;

import kg.attractor.ht49.models.RespondedApplicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespondedApplicantRepository extends JpaRepository<RespondedApplicant, Long> {
//    @Query(value = """
//            select * from RESPONDED_APPLICANTS
//            where VACANCY_ID = ?
//            """, nativeQuery = true)
    List<RespondedApplicant> findRespondedApplicantsByVacancy_Author_Id(Long id);

//    @Query(value = """
//            select * from RESPONDED_APPLICANTS
//            where RESUME_ID = ?
//            """, nativeQuery = true)
    List<RespondedApplicant> findByResume_Applicant_Id(Long resume_id);

}
