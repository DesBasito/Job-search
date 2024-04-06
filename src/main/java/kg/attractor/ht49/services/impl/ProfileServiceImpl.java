package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.UserDao;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.models.UserModel;
import kg.attractor.ht49.services.interfaces.ProfileService;
import kg.attractor.ht49.services.interfaces.ResumeService;
import kg.attractor.ht49.services.interfaces.UserService;
import kg.attractor.ht49.services.interfaces.VacancyService;
import kg.attractor.ht49.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserDao dao;
    private final PasswordEncoder encoder;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;
    private final FileUtil util;

    @Override
    public void editProfile(EditUserDto user) {
        String fileName = null;
        if (user.getAvatar()!=null ) {
            if (Objects.requireNonNull(user.getAvatar().getContentType()).matches("png|jpeg|jpg")) {
                throw new IllegalArgumentException("Unsupported img types (should be: \"png|jpeg|jpg\")");
            }
            fileName = util.saveUploadedFile(user.getAvatar(), "/images");
        }        UserModel userModel1 = UserModel.builder()
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .password(encoder.encode(user.getPassword()))
                .phoneNumber(user.getPhoneNumber())
                .avatar(fileName)
                .build();
        dao.editUser(userModel1);
    }

    @Override
    public UserDto getUserInfoByEmail(String email) {
        return null;
    }

    @Override
    public List<ResumeDto> getApplicantResumes(String email) {
        return resumeService.getResumesByUserEmail(email);
    }

    @Override
    public List<VacancyDto> getEmployerVacancies(String email) {
        UserDto user = null;
        return vacancyService.getAllVacanciesByCompany(user.getId());
    }

}
