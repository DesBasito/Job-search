package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dto.educations.CreateEducationInfoDto;
import kg.attractor.ht49.dto.educations.EducationInfoDto;
import kg.attractor.ht49.dto.educations.EducationInfoEditDto;
import kg.attractor.ht49.models.EducationInfo;
import kg.attractor.ht49.models.Resume;
import kg.attractor.ht49.repositories.EducationInfoRepository;
import kg.attractor.ht49.services.interfaces.EducationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationInfoServiceImpl implements EducationInfoService {
    private final EducationInfoRepository educationInfoRepository;


    @Override
    public void editInfo(EducationInfoEditDto info,Resume resume) {
        EducationInfo edu = EducationInfo.builder()
                .id(info.getId())
                .institution(info.getInstitution())
                .program(info.getProgram())
                .startDate(info.getStartDate())
                .endDate(info.getEndDate())
                .degree(info.getDegree())
                .resume(resume)
                .build();
        educationInfoRepository.save(edu);
    }

    @Override
    public boolean deleteEducationInfoById(Long id) {
        if (educationInfoRepository.findById(id).isPresent()) {
            educationInfoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void createEducationInfo(CreateEducationInfoDto info, Resume resume) {
        EducationInfo educationInfo = EducationInfo.builder()
                .institution(info.getInstitution())
                .degree(info.getDegree())
                .resume(resume)
                .program(info.getProgram())
                .startDate(info.getStartDate())
                .endDate(info.getEndDate())
                .build();
           educationInfoRepository.save(educationInfo);
    }


    @Override
    public List<EducationInfoDto> getEducationsInfoByResumeId(Resume resume) {
        List<EducationInfoDto> dtos = new ArrayList<>();
        educationInfoRepository.findByResume(resume).forEach(e -> dtos.add(getEducationInfoDto(e)));
        return dtos;
    }

    @Override
    public List<EducationInfoEditDto> getEducationsInfoForEditByResumeId(Resume resume) {
        List<EducationInfoEditDto> dtos = new ArrayList<>();
        educationInfoRepository.findByResume(resume).forEach(e -> dtos.add(EducationInfoEditDto.builder()
                        .id(e.getId())
                        .degree(e.getDegree())
                        .institution(e.getInstitution())
                        .program(e.getProgram())
                        .startDate(e.getStartDate())
                        .endDate(e.getEndDate())
                .build()));
        return dtos;
    }

    private EducationInfoDto getEducationInfoDto(EducationInfo info) {
        return EducationInfoDto.builder()
                .id(info.getId())
                .degree(info.getDegree())
                .program(info.getProgram())
                .institution(info.getInstitution())
                .resumeId(info.getResume().getId())
                .startDate(info.getStartDate())
                .endDate(info.getEndDate())
                .build();
    }
}
