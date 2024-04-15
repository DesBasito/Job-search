package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.MessagesDao;
import kg.attractor.ht49.dto.MessageDto;
import kg.attractor.ht49.models.Message;
import kg.attractor.ht49.services.interfaces.MessagesService;
import kg.attractor.ht49.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessagesServiceImpl implements MessagesService {
    private final MessagesDao dao;
    private final UserService service;

    @Override
    public List<MessageDto> getMessages(Long id) {
        return dao.getMessagesByUserId(id).stream().map(this::getDto).collect(Collectors.toList());
    }

    @Override
    public MessageDto getNewMessages(Long id) {
        Message message = dao.getNewMessageBySenderId(id).orElseThrow(()->new NoSuchElementException("message not found"));
        return getDto(message);
    }

    @Override
    public Long addMessage(String message, String sender,String recipient) {
        return dao.createAndReturnId(Message.builder()
                        .content(message)
                        .timestamp(LocalDateTime.now())
                        .sender(service.getUserByEmail(sender).getId())
                        .recipient(service.getUserByEmail(recipient).getId())
                .build());
    }



    private MessageDto getDto(Message m){
        return MessageDto.builder()
                .id(m.getId())
                .senderEmail(service.getUserById(m.getSender()).getEmail())
                .recipientEmail(service.getUserById(m.getRecipient()).getEmail())
                .content(m.getContent())
                .timestamp(m.getTimestamp())
                .build();
    }
}
