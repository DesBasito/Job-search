package kg.attractor.ht49.repositories;

import kg.attractor.ht49.models.Message;
import kg.attractor.ht49.models.RespondedApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findMessageByRespApplicant_Id(Long respApplId);

    @Query(value = """ 
            select * from MESSAGES
            where id > ? and RESPONDED_APPLICANT_ID = ?
            """, nativeQuery = true)
    List<Message> findMessageByIdAndRespApplId(Long id, Long respondedApplicant);
}
