package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dto.MessageDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.models.Message;
import kg.attractor.ht49.repositories.MessageRepository;
import kg.attractor.ht49.services.interfaces.MessagesService;
import kg.attractor.ht49.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessagesServiceImpl implements MessagesService {
    private final MessageRepository messageRepository;
    private final UserService userService;

    @Override
    public List<MessageDto> getMessages(Long id) {
        return messageRepository.findMessageByRespApplId_Id(id).stream().map(this::getDto).collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> getNewMessagesBylastMessage(Long lastMessageId,Long respId) {
        return messageRepository.findMessageByIdAndRespApplId(lastMessageId,respId).stream().map(this::getDto).collect(Collectors.toList());
    }

    @Override
    public void addMessage(MessageDto messageDto, Authentication authentication) {
        UserDto user = userService.getUserByEmail(messageDto.getSenderEmail());
         messageRepository.createMessage(messageDto.getContent(),messageDto.getRespApplId(),messageDto.getTimestamp(),user.getEmail());
    }



    private MessageDto getDto(Message m){
        return MessageDto.builder()
                .respApplId(m.getRespApplId().getId())
                .senderEmail(m.getSender().getEmail())
                .content(m.getContent())
                .timestamp(m.getTimestamp())
                .build();
    }
}
