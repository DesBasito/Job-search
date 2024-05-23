package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoCreateDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoEditDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExperienceInfoDto;
import kg.attractor.ht49.models.Resume;
import kg.attractor.ht49.models.WorkExperienceInfo;
import kg.attractor.ht49.repositories.WorkExperienceInfoRepository;
import kg.attractor.ht49.services.interfaces.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkExpInfoImpl implements WorkExperienceInfoService {
    private final WorkExperienceInfoRepository workExperienceInfoRepository;
    @Override
    public void editInfo(WorkExpInfoEditDto info,Resume resume) {
        WorkExperienceInfo info2 = WorkExperienceInfo.builder()
                .id(info.getId())
                .companyName(info.getCompanyName())
                .position(info.getPosition())
                .responsibilities(info.getResponsibilities())
                .years(info.getYears())
                .resume(resume)
                .build();
        workExperienceInfoRepository.save(info2);
    }

    @Override
    public void createWorkExpInfo(WorkExpInfoCreateDto info, Resume resume) {
        if (info.getYears() == null &&
            info.getPosition() == null &&
            info.getResponsibilities() == null &&
            info.getCompanyName() == null
            ) {
            return;
        }
        WorkExperienceInfo info2 = WorkExperienceInfo.builder()
                .companyName(info.getCompanyName())
                .resume(resume)
                .position(info.getPosition())
                .responsibilities(info.getResponsibilities())
                .years(info.getYears())
                .build();
        workExperienceInfoRepository.save(info2);
    }

    @Override
    public List<WorkExperienceInfoDto> getWorkExperiencesByResumeId(Resume resume) {
        List<WorkExperienceInfo> infos = workExperienceInfoRepository.findByResume(resume);
        List<WorkExperienceInfoDto> dtos = new ArrayList<>();
        infos.forEach( e -> dtos.add(WorkExperienceInfoDto.builder()
                        .id(e.getId())
                        .companyName(e.getCompanyName())
                        .resumeId(e.getResume().getId())
                        .position(e.getPosition())
                        .responsibilities(e.getResponsibilities())
                        .years(e.getYears())
                .build()));
        return dtos;
    }

    @Override
    public List<WorkExpInfoEditDto> getWorkExperiencesForEditByResumeId(Resume resume) {
        List<WorkExperienceInfo> infos = workExperienceInfoRepository.findByResume(resume);
        List<WorkExpInfoEditDto> dtos = new ArrayList<>();
        infos.forEach( e -> dtos.add(WorkExpInfoEditDto.builder()
                .id(e.getId())
                .companyName(e.getCompanyName())
                .position(e.getPosition())
                .responsibilities(e.getResponsibilities())
                .years(e.getYears())
                .build()));
        return dtos;
    }
}
