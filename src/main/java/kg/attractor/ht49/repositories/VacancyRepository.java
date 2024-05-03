package kg.attractor.ht49.repositories;

import kg.attractor.ht49.models.Category;
import kg.attractor.ht49.models.UserModel;
import kg.attractor.ht49.models.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VacancyRepository extends JpaRepository<Vacancy,Long> {
    List<Vacancy> findByAuthor_Email(String author_email);

    @Query(nativeQuery = true,value = """
            SELECT v.*
            FROM vacancies v
            INNER JOIN responded_applicants ra ON v.id = ra.resume_id
            WHERE ra.vacancy_id = ?
            """)
    List<Vacancy> findVacanciesByRespondedApplicantsId(Long id);

    List<Vacancy> findByCategory(Category category);

    List<Vacancy> findByAuthor_EmailAndIsActive(String author_email, Boolean isActive);

    List<Vacancy> findByIsActive(Boolean isActive);

    Optional<Vacancy> findByName(String name);

    @Modifying
    @Query(nativeQuery = true,value = """
            UPDATE VACANCIES
            SET IS_ACTIVE = :isActive , UPDATE_DATE = now()
            WHERE id = :id;
            """)
    void changeVacancyState(Long id, boolean isActive);

    @Modifying
    @Query(nativeQuery = true,value = """
            UPDATE VACANCIES
            SET UPDATE_DATE = now()
            WHERE id = :id;
            """)
    void updateVacancy(Long id);
}