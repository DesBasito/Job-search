package kg.attractor.ht49.controllers;


import kg.attractor.ht49.dto.MessageDto;
import kg.attractor.ht49.dto.RespondedApplicantDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.AuthAdapter;
import kg.attractor.ht49.services.interfaces.MessagesService;
import kg.attractor.ht49.services.interfaces.RespondedApplicantsService;
import kg.attractor.ht49.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final UserService service;
    private final MessagesService messageService;
    private final AuthAdapter adapter;
    private final RespondedApplicantsService respService;

    @GetMapping("/{respId}")
    public String chatPage( Model model, @PathVariable Long respId) {
        String email = adapter.getAuthUser().getEmail();
        UserDto sender = service.getUserByEmail(email);
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