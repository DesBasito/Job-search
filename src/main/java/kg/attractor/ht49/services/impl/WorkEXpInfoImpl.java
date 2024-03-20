package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.WorkExpInfoDao;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoCreateDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoEditDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExperienceInfoDto;
import kg.attractor.ht49.services.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkEXpInfoImpl implements WorkExperienceInfoService {
    private final WorkExpInfoDao dao;

    @Override
    public WorkExperienceInfoDto getEducationByResumeId(Long id) {
        return null;
    }

    @Override
    public void editInfo(WorkExpInfoEditDto info) {

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
}
