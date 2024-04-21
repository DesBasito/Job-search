package kg.attractor.ht49.services.interfaces;

import kg.attractor.ht49.dto.MessageDto;
import kg.attractor.ht49.models.Message;

import java.util.List;
import java.util.Map;

public interface MessagesService {
    List<MessageDto> getMessages(Long id);

    List<MessageDto> getNewMessagesBylastMessage(Long lastMessageId,Long respId);

    void addMessage(MessageDto messageDto);
}
