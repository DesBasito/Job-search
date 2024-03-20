package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.educations.CreateEducationInfoDto;
import kg.attractor.ht49.dto.educations.EducationInfoDto;
import kg.attractor.ht49.dto.educations.EducationInfoEditDto;

public interface EducationInfoService {
    EducationInfoDto getEducationByResumeId(Long id);

    void editInfo(EducationInfoEditDto info);

    boolean deleteEducationInfoById(Long id);

    void createEducationInfo(CreateEducationInfoDto info, Long id);

    Long createAndReturnEduInfoId(CreateEducationInfoDto info);
}
