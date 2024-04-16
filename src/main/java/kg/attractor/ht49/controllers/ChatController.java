package kg.attractor.ht49.controllers;


import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.models.ChatMessage;
import kg.attractor.ht49.services.interfaces.MessagesService;
import kg.attractor.ht49.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final UserService service;
    private final MessagesService messageService;

    @GetMapping()
    public String chatPage(
            @RequestParam String email,
            Authentication authentication,
            Model model) {
        UserDto sender = service.getUserByEmail(authentication.getName());
        UserDto recipient = service.getUserByEmail(email);

        model.addAttribute("sender", sender);
        model.addAttribute("recipient", recipient);

        return "chat";
    }
    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", chatMessage.getSender());
        System.out.println("headerAccessor = " + headerAccessor);
        System.out.println("chatMessage = " + chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        messageService.addMessage(chatMessage.getContent(),chatMessage.getSender(),chatMessage.getRecipient());
        System.out.println("sender = " + chatMessage.getSender());
        System.out.println("recipient = " + chatMessage.getRecipient());
        return chatMessage;
    }
}