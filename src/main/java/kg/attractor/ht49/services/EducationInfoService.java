package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.EducationInfoDto;
import kg.attractor.ht49.models.EducationInfo;

public interface EducationInfoService {
    EducationInfoDto getEducationByResumeId(Long id);

    void editInfo(EducationInfoDto info);

    void deleteEducationInfoById(Long id);

    void createEducationInfo(EducationInfo info);
}
