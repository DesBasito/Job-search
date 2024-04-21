package kg.attractor.ht49.controllers;


import kg.attractor.ht49.dto.MessageDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.services.interfaces.MessagesService;
import kg.attractor.ht49.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final UserService service;
    private final MessagesService messageService;

    @GetMapping("/{respId}")
    public String chatPage(@RequestParam("email") String email, Authentication authentication, Model model, @PathVariable Long respId) {
        UserDto sender = service.getUserByEmail(authentication.getName());
        UserDto recipient = service.getUserByEmail(email);
        List<MessageDto> messages = messageService.getMessages(respId);

        model.addAttribute("messages",messages);
        model.addAttribute("sender", sender);
        model.addAttribute("recipient", recipient);
        model.addAttribute("respApplId",respId);
        return "chat";
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(MessageDto messageDto) {
        try {
            // Validate messageDto fields as needed
            messageService.addMessage(messageDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error processing message: " + e.getMessage());
        }
    }

}