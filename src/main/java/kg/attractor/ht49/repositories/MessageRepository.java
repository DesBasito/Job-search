package kg.attractor.ht49.repositories;

import kg.attractor.ht49.models.Message;
import kg.attractor.ht49.models.RespondedApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findMessageByRespApplId_Id(Long respApplId);

    @Query(value = """ 
            select * from MESSAGES
            where id > ? and RESPONDED_APPLICANT_ID = ?
            """, nativeQuery = true)
    List<Message> findMessageByIdAndRespApplId(Long id, Long respondedApplicant);

    @Modifying
    @Query(value = """
            insert into MESSAGES(responded_applicant_id, sender, content, timestamp)\s
            values ( :respApplId, :sender, :content,:timestamp )
            """, nativeQuery = true)
    void createMessage(String content, Long respApplId, LocalDateTime timestamp, String sender);
}
