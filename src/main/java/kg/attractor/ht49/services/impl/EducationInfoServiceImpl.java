package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.EducationInfoDao;
import kg.attractor.ht49.dto.educations.CreateEducationInfoDto;
import kg.attractor.ht49.dto.educations.EducationInfoDto;
import kg.attractor.ht49.exceptions.EducationInfoNotFoundException;
import kg.attractor.ht49.exceptions.ResumeNotFoundException;
import kg.attractor.ht49.models.EducationInfo;
import kg.attractor.ht49.services.EducationInfoService;
import kg.attractor.ht49.services.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EducationInfoServiceImpl implements EducationInfoService {
    private final EducationInfoDao dao;
    private final ResumeService rService;


    @Override
    public EducationInfoDto getEducationByResumeId(Long id) {
        try {
            if (rService.getResumeById(id) == null) {
                throw new ResumeNotFoundException("cannot find Resume by id: " + id);
            }
            EducationInfo info = dao.getEducationByResume(id).orElseThrow(() -> new EducationInfoNotFoundException("cannot find Education info by resume id: " + id));
            return getEducationInfoDto(info);
        } catch (ResumeNotFoundException | EducationInfoNotFoundException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void editInfo(EducationInfoDto info) {
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
    public void createEducationInfo(CreateEducationInfoDto info) {
        CreateEducationInfoDto dto = info;
        dao.createEducationInfo(dto);
    }

    @Override
    public List<EducationInfoDto> getEducationsInfo() {
        List<EducationInfo> info = dao.getAllEduCationInfos();
        List<EducationInfoDto> dtos = new ArrayList<>();
        info.forEach(e -> dtos.add(getEducationInfoDto(e)));
        return dtos;
    }

    @Override
    public Long createAndReturnEduInfoId(CreateEducationInfoDto info) {
        return dao.createAndReturnId(info);
    }

    private EducationInfoDto getEducationInfoDto(EducationInfo info) {
        return EducationInfoDto.builder()
                .id(info.getId())
                .degree(info.getDegree())
                .program(info.getProgram())
                .institution(info.getInstitution())
                .resume(rService.getResumeById(info.getResumeId()))
                .startDate(Date.valueOf(info.getStartDate()))
                .endDate(Date.valueOf(info.getEndDate()))
                .build();
    }
}
