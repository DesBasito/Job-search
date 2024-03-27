package kg.attractor.ht49.services.interfaces;

import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoCreateDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoEditDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExperienceInfoDto;

import java.util.List;

public interface WorkExperienceInfoService {
    void editInfo(WorkExpInfoEditDto info);

    boolean deleteWorkExpInfoById(Long id);

    void createWorkExpInfo(WorkExpInfoCreateDto info, Long id);

    Long createAndReturnWorkExpInfoId(WorkExperienceInfoDto info);

    List<WorkExperienceInfoDto> getWorkExperiencesByResumeId(Long resumeId);

}
