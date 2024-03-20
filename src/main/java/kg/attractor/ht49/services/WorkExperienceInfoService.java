package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoCreateDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoEditDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExperienceInfoDto;

public interface WorkExperienceInfoService {
    WorkExperienceInfoDto getEducationByResumeId(Long id);

    void editInfo(WorkExpInfoEditDto info);

    boolean deleteWorkExpInfoById(Long id);

    void createWorkExpInfo(WorkExpInfoCreateDto info, Long id);

    Long createAndReturnWorkExpInfoId(WorkExperienceInfoDto info);
}
