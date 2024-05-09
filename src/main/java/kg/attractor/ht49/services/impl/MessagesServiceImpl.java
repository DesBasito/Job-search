package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dto.MessageDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.models.Message;
import kg.attractor.ht49.models.RespondedApplicant;
import kg.attractor.ht49.models.UserModel;
import kg.attractor.ht49.repositories.MessageRepository;
import kg.attractor.ht49.services.interfaces.MessagesService;
import kg.attractor.ht49.services.interfaces.RespondedApplicantsService;
import kg.attractor.ht49.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessagesServiceImpl implements MessagesService {
    private final MessageRepository messageRepository;
    private final UserService userService;
    private final RespondedApplicantsService respondedApplicantsService;

    @Override
    public List<MessageDto> getMessages(Long id) {
        return messageRepository.findMessageByRespApplicant_Id(id).stream().map(this::getDto).collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> getNewMessagesBylastMessage(Long lastMessageId,Long respId) {
        return messageRepository.findMessageByIdAndRespApplId(lastMessageId,respId).stream().map(this::getDto).collect(Collectors.toList());
    }

    @Override
    public void addMessage(MessageDto messageDto, String email) {
        UserModel user = userService.getUserModelByEmail(email);
        RespondedApplicant respondedApplicant = respondedApplicantsService.getRespondedApplicantModelById(messageDto.getRespApplId());
        Message message = Message.builder()
                .sender(user)
                .content(messageDto.getContent())
                .respApplicant(respondedApplicant)
                .timestamp(LocalDateTime.now())
                .build();
         messageRepository.save(message);
    }



    private MessageDto getDto(Message m){
        return MessageDto.builder()
                .respApplId(m.getRespApplicant().getId())
                .senderEmail(m.getSender().getEmail())
                .content(m.getContent())
                .timestamp(m.getTimestamp())
                .build();
    }
}
