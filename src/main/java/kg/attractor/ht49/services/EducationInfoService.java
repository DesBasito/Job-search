package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.educations.CreateEducationInfoDto;
import kg.attractor.ht49.dto.educations.EducationInfoDto;
import kg.attractor.ht49.dto.educations.EducationInfoEditDto;

import java.util.List;

public interface EducationInfoService {
    void editInfo(EducationInfoEditDto info);

    boolean deleteEducationInfoById(Long id);

    void createEducationInfo(CreateEducationInfoDto info, Long id);

    Long createAndReturnEduInfoId(CreateEducationInfoDto info);

    List<EducationInfoDto> getEducationsInfoByResumeId(Long id);
}
