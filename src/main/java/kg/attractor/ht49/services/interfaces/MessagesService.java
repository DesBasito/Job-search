package kg.attractor.ht49.services.interfaces;

import kg.attractor.ht49.dto.MessageDto;
import kg.attractor.ht49.models.Message;

import java.util.List;

public interface MessagesService {
    List<MessageDto> getMessages(Long id);
    MessageDto getNewMessages(Long id);

    Long addMessage(String message, String sender, String recipient);
}
