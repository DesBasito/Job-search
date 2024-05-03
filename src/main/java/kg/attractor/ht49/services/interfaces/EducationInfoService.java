package kg.attractor.ht49.services.interfaces;

import kg.attractor.ht49.dto.educations.CreateEducationInfoDto;
import kg.attractor.ht49.dto.educations.EducationInfoDto;
import kg.attractor.ht49.dto.educations.EducationInfoEditDto;
import kg.attractor.ht49.models.Resume;

import java.util.List;

public interface EducationInfoService {
    void editInfo(EducationInfoEditDto info);

    boolean deleteEducationInfoById(Long id);

    void createEducationInfo(CreateEducationInfoDto info, Resume resume);


    List<EducationInfoDto> getEducationsInfoByResumeId(Resume resume);

    List<EducationInfoEditDto> getEducationsInfoForEditByResumeId(Resume resume);
}
