package kg.attractor.ht49.services.interfaces;

import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoCreateDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoEditDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExperienceInfoDto;
import kg.attractor.ht49.models.Resume;

import java.util.List;

public interface WorkExperienceInfoService {
    void editInfo(WorkExpInfoEditDto info);


    void createWorkExpInfo(WorkExpInfoCreateDto info, Resume resume);


    List<WorkExperienceInfoDto> getWorkExperiencesByResumeId(Resume resume);

    List<WorkExpInfoEditDto> getWorkExperiencesForEditByResumeId(Resume resume);
}
