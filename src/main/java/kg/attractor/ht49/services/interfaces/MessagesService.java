package kg.attractor.ht49.services.interfaces;

import kg.attractor.ht49.dto.MessageDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface MessagesService {
    List<MessageDto> getMessages(Long id);

    List<MessageDto> getNewMessagesBylastMessage(Long lastMessageId,Long respId);

    void addMessage(MessageDto messageDto, Authentication authentication);
}
