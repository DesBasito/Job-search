package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.educations.EducationInfoForFrontDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoForFrontDto;
import kg.attractor.ht49.services.interfaces.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("resume")
public class ResumeViewController {
    private final ResumeService service;

    @GetMapping("/{id}")
    public String getResumeById(@PathVariable Long id, Model model){
        ResumeDto resume = service.getResumeById(id);
        model.addAttribute("resume",resume);
        UserDto user = service.getUserByResumesAuthorEmail(resume.getUserEmail());
        model.addAttribute("user",user);
        List<WorkExpInfoForFrontDto> workExpInfos = service.getWorkExpInfoByResumeId(id);
        model.addAttribute("works",workExpInfos);
        List<EducationInfoForFrontDto> educationInfo = service.getEducationInfoByResumeId(id);
        model.addAttribute("educations",educationInfo);
        return "resume/resumesInfo";
    }
}
