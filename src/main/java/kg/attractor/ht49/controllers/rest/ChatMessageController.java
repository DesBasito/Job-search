package kg.attractor.ht49.controllers.rest;

import jakarta.validation.Valid;
import kg.attractor.ht49.dto.MessageDto;
import kg.attractor.ht49.services.Components.AuthAdapter;
import kg.attractor.ht49.services.interfaces.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("restMessages")
@RequiredArgsConstructor
@RequestMapping("api/message")
public class ChatMessageController {
    private final MessagesService service;
    private final AuthAdapter adapter;


    @GetMapping("/{respondedId}")
    public ResponseEntity<List<MessageDto>> getNewMessage(@RequestParam Long lastMessageId, @PathVariable Long respondedId) {
        return ResponseEntity.ok(service.getNewMessagesBylastMessage(lastMessageId, respondedId));
    }

    @PostMapping
    public HttpStatus sendMessage(@Valid @RequestBody MessageDto messageDto) {
        String email = adapter.getAuthUser().getEmail();
        service.addMessage(messageDto, email);
        return HttpStatus.OK;
    }
}
