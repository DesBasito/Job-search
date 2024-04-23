package kg.attractor.ht49.controllers;


import kg.attractor.ht49.dto.MessageDto;
import kg.attractor.ht49.dto.RespondedApplicantDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.models.RespondedApplicant;
import kg.attractor.ht49.services.interfaces.*;
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
    private final RespondedApplicantsService respService;

    @GetMapping("/{respId}")
    public String chatPage(Authentication authentication, Model model, @PathVariable Long respId) {
        UserDto sender = service.getUserByEmail(authentication.getName());
        RespondedApplicantDto dto = respService.getRespondedApplicantById(respId);
        UserDto recipient = service.getUserByEmail(dto.getVacancy().getAuthorEmail());
        List<MessageDto> messages = messageService.getMessages(respId);

        model.addAttribute("messages",messages);
        model.addAttribute("sender", sender);
        model.addAttribute("recipient", recipient);
        model.addAttribute("respApplId",respId);
        return "chat";
    }

}