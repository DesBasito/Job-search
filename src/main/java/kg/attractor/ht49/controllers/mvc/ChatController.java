package kg.attractor.ht49.controllers.mvc;


import kg.attractor.ht49.dto.RespondedApplicantDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.services.Components.AuthAdapter;
import kg.attractor.ht49.services.interfaces.MessagesService;
import kg.attractor.ht49.services.interfaces.RespondedApplicantsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final MessagesService messageService;
    private final AuthAdapter adapter;
    private final RespondedApplicantsService respService;

    @GetMapping("/{respId}")
    public String chatPage( Model model, @PathVariable Long respId) {
        UserDto sender = adapter.getAuthUser();
        RespondedApplicantDto respondedApplicant = respService.getRespondedApplicantById(respId);
        respService.checkUserByResume(respondedApplicant,sender);

        model.addAttribute("sender", sender);
        model.addAttribute("respApplId",respId);
        return "chat";
    }

}