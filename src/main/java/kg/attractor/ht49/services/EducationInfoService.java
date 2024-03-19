package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.educations.CreateEducationInfoDto;
import kg.attractor.ht49.dto.educations.EducationInfoDto;

import java.util.List;

public interface EducationInfoService {
    EducationInfoDto getEducationByResumeId(Long id);

    void editInfo(EducationInfoDto info);

    boolean deleteEducationInfoById(Long id);

    void createEducationInfo(CreateEducationInfoDto info);

    List<EducationInfoDto> getEducationsInfo();

    Long createAndReturnEduInfoId(CreateEducationInfoDto info);
}
