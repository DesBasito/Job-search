package kg.attractor.ht49.controllers.api;

import kg.attractor.ht49.dto.MessageDto;
import kg.attractor.ht49.services.interfaces.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController("restMessages")
@RequiredArgsConstructor
@RequestMapping("api/messages")
public class MessagesController {
    private final MessagesService service;

    @GetMapping("/getMessages/{id}")
    public ResponseEntity<List<MessageDto>> messages(@PathVariable Long id) {
        return ResponseEntity.ok(service.getMessages(id));
    }

    @GetMapping("/getNewMessage/{id}")
    public ResponseEntity<MessageDto> getNewMessage(@PathVariable Long id) {
        return ResponseEntity.ok(service.getNewMessages(id));
    }


    @PostMapping("/sendMessage/{recipientEmail}")
    public ResponseEntity<Long> sendMessage(@RequestParam String message, @PathVariable String recipientEmail, Authentication authentication) {
        return ResponseEntity.ok(service.addMessage(message, authentication.getName(), recipientEmail));
    }
}
