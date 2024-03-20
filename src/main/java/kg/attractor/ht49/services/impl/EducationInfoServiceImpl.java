package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.EducationInfoDao;
import kg.attractor.ht49.dto.educations.CreateEducationInfoDto;
import kg.attractor.ht49.dto.educations.EducationInfoDto;
import kg.attractor.ht49.dto.educations.EducationInfoEditDto;
import kg.attractor.ht49.exceptions.EducationInfoNotFoundException;
import kg.attractor.ht49.models.EducationInfo;
import kg.attractor.ht49.services.EducationInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class EducationInfoServiceImpl implements EducationInfoService {
    private final EducationInfoDao dao;


    @Override
    public EducationInfoDto getEducationByResumeId(Long id) {
        try {
            EducationInfo info = dao.getEducationByResume(id).orElseThrow(() -> new EducationInfoNotFoundException("cannot find Education info by resume id: " + id));
            return getEducationInfoDto(info);
        } catch (EducationInfoNotFoundException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void editInfo(EducationInfoEditDto info) {
        dao.editInfo(info);
    }

    @Override
    public boolean deleteEducationInfoById(Long id) {
        if (dao.getEducationById(id).isPresent()) {
            dao.deleteEducationInfoById(id);
            return true;
        }
        return false;
    }

    @Override
    public void createEducationInfo(CreateEducationInfoDto info, Long id) {
           CreateEducationInfoDto dto = info;
           dao.createEducationInfo(dto,id);
    }


    @Override
    public Long createAndReturnEduInfoId(CreateEducationInfoDto info) {
//        return dao.createAndReturnId(info);
        return null;
    }

    private EducationInfoDto getEducationInfoDto(EducationInfo info) {
        return EducationInfoDto.builder()
                .id(info.getId())
                .degree(info.getDegree())
                .program(info.getProgram())
                .institution(info.getInstitution())
                .resumeId(info.getResumeId())
                .startDate(Date.valueOf(info.getStartDate()))
                .endDate(Date.valueOf(info.getEndDate()))
                .build();
    }
}
