package kg.attractor.ht49.controllers.api;

import kg.attractor.ht49.dto.MessageDto;
import kg.attractor.ht49.services.interfaces.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("restMessages")
@RequiredArgsConstructor
@RequestMapping("api/message")
public class ChatMessageController {
    private final MessagesService service;

    @GetMapping("/{respondedId}")
    public ResponseEntity<List<MessageDto>> getNewMessage(@RequestParam Long lastMessageId, @PathVariable Long respondedId) {
        return ResponseEntity.ok(service.getNewMessagesBylastMessage(lastMessageId, respondedId));
    }

    @PostMapping
    public HttpStatus sendMessage(@RequestBody MessageDto messageDto, Authentication authentication) {
        service.addMessage(messageDto, authentication);
        return HttpStatus.OK;
    }
}
