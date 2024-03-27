package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.EducationInfoDao;
import kg.attractor.ht49.dto.educations.CreateEducationInfoDto;
import kg.attractor.ht49.dto.educations.EducationInfoDto;
import kg.attractor.ht49.dto.educations.EducationInfoEditDto;
import kg.attractor.ht49.models.EducationInfo;
import kg.attractor.ht49.services.interfaces.EducationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationInfoServiceImpl implements EducationInfoService {
    private final EducationInfoDao dao;


    @Override
    public void editInfo(EducationInfoEditDto info) {
        EducationInfo edu = EducationInfo.builder()
                .id(info.getId())
                .institution(info.getInstitution())
                .program(info.getProgram())
                .startDate(info.getStartDate().toLocalDate())
                .endDate(info.getEndDate().toLocalDate())
                .degree(info.getDegree())
                .build();
        dao.editInfo(edu);
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
           dao.createEducationInfo(info,id);
    }


    @Override
    public Long createAndReturnEduInfoId(CreateEducationInfoDto info) {
//        return dao.createAndReturnId(info);
        return null;
    }

    @Override
    public List<EducationInfoDto> getEducationsInfoByResumeId(Long id) {
        List<EducationInfoDto> dtos = new ArrayList<>();
        dao.getEducationByResume(id).forEach(e -> dtos.add(getEducationInfoDto(e)));
        return dtos;
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
