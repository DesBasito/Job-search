package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.WorkExpInfoDao;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoCreateDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoEditDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExperienceInfoDto;
import kg.attractor.ht49.models.WorkExperienceInfo;
import kg.attractor.ht49.services.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkExpInfoImpl implements WorkExperienceInfoService {
    private final WorkExpInfoDao dao;

    @Override
    public void editInfo(WorkExpInfoEditDto info) {
        WorkExperienceInfo info2 = WorkExperienceInfo.builder()
                .id(info.getId())
                .companyName(info.getCompanyName())
                .position(info.getPosition())
                .responsibilities(info.getResponsibilities())
                .years(info.getYears())
                .build();
        dao.editInfo(info2);
    }

    @Override
    public boolean deleteWorkExpInfoById(Long id) {
        return false;
    }

    @Override
    public void createWorkExpInfo(WorkExpInfoCreateDto info, Long id) {
        dao.createWorkInfo(info,id);
    }

    @Override
    public Long createAndReturnWorkExpInfoId(WorkExperienceInfoDto info) {
        return null;
    }

    @Override
    public List<WorkExperienceInfoDto> getWorkExperiencesByResumeId(Long resumeId) {
        List<WorkExperienceInfo> infos = dao.getListWorkExpByResumeId(resumeId);
        List<WorkExperienceInfoDto> dtos = new ArrayList<>();
        infos.forEach( e -> dtos.add(WorkExperienceInfoDto.builder()
                        .id(e.getId())
                        .companyName(e.getCompanyName())
                        .resumeId(e.getResumeId())
                        .position(e.getPosition())
                        .responsibilities(e.getResponsibilities())
                        .years(e.getYears())
                .build()));
        return dtos;
    }
}
