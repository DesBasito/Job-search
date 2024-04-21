package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.MessagesDao;
import kg.attractor.ht49.dto.MessageDto;
import kg.attractor.ht49.models.Message;
import kg.attractor.ht49.services.interfaces.MessagesService;
import kg.attractor.ht49.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessagesServiceImpl implements MessagesService {
    private final MessagesDao dao;
    private final UserService service;

    @Override
    public List<MessageDto> getMessages(Long id) {
        return dao.getMessagesByRespApplId(id).stream().map(this::getDto).collect(Collectors.toList());
    }


    @Override
    public List<MessageDto> getNewMessagesBylastMessage(Long lastMessageId,Long respId) {
        return dao.getNewMessages(lastMessageId,respId).stream().map(this::getDto).toList();
    }

    @Override
    public void addMessage(MessageDto messageDto) {
         dao.createMessage(Message.builder()
                        .content(messageDto.getContent())
                        .respApplId(messageDto.getRespApplId())
                        .timestamp(LocalDateTime.now())
                        .sender(service.getUserByEmail(messageDto.getSenderEmail()).getId())
                .build());
    }



    private MessageDto getDto(Message m){
        return MessageDto.builder()
                .respApplId(m.getRespApplId())
                .senderEmail(service.getUserById(m.getSender()).getEmail())
                .content(m.getContent())
                .timestamp(m.getTimestamp())
                .build();
    }
}
