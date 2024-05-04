package kg.attractor.ht49.repositories;

import kg.attractor.ht49.models.Category;
import kg.attractor.ht49.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume,Long> {
    List<Resume> findByCategory(Category categoryId);

    List<Resume> findByCategory_IdAndApplicant_Id(Long id, Long id1);

    List<Resume> findByApplicant_Id(Long id);

    List<Resume> findByName(String rName);

    @Modifying
    @Query(nativeQuery = true,value = """
            UPDATE RESUMES
            SET IS_ACTIVE = :isActive , UPDATE_DATE = now()
            WHERE id = :id;
            """)
    void changeResumeState(Long id, boolean isActive);

    @Modifying
    @Query(nativeQuery = true,value = """
            UPDATE RESUMES
            SET UPDATE_DATE = :updateDate
            WHERE id = :id;
            """)
    void updateResumeByIdAndUpdateDate(Long id, LocalDateTime updateDate);

}
