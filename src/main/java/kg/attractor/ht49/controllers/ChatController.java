//package kg.attractor.ht49.controllers;
//
//
//import kg.attractor.ht49.models.ChatMessage;
//import lombok.RequiredArgsConstructor;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.Objects;
//
//@Controller
//@RequiredArgsConstructor
//public class ChatController {
//
//    @GetMapping("/testChat")
//    public String chatPage() {
//        return "main/chat";
//    }
//    @MessageMapping("/chat.register")
//    @SendTo("/topic/public")
//    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
//        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", chatMessage.getSender());
//        System.out.println("headerAccessor = " + headerAccessor);
//        System.out.println("chatMessage = " + chatMessage);
//        return chatMessage;
//    }
//
//    @MessageMapping("/chat.send")
//    @SendTo("/topic/public")
//    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
//        return chatMessage;
//    }
//}