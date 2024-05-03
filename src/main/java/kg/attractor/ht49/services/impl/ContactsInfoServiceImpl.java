package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dto.ContactInfo.ContactsInfoDto;
import kg.attractor.ht49.dto.ContactInfo.ContactsInfoWithIdDto;
import kg.attractor.ht49.dto.ContactType.ContactsTypeWithIdDto;
import kg.attractor.ht49.models.ContactsInfo;
import kg.attractor.ht49.models.Resume;
import kg.attractor.ht49.repositories.ContactInfoRepository;
import kg.attractor.ht49.services.interfaces.ContactsInfoService;
import kg.attractor.ht49.services.interfaces.ContactsTypeService;
import kg.attractor.ht49.services.interfaces.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ContactsInfoServiceImpl implements ContactsInfoService {
    private final ContactInfoRepository contactInfoRepository;
    private final ContactsTypeService contactsTypeService;

    @Override
    public void createNewContactsInfo(ContactsInfoDto contactsInfo, Resume resume) {
        Long contactTypeId;
        if (contactsTypeService.getContactTypeByName(contactsInfo.getType()) == null) {
            contactTypeId = contactsTypeService.createNewContacts(contactsInfo.getType());
        } else {
            ContactsTypeWithIdDto type = contactsTypeService.getContactTypeByName(contactsInfo.getType());
            contactTypeId = type.getId();
        }
        ContactsInfo contactsInfo1 = ContactsInfo.builder()
                .type(contactsTypeService.getTypeWithId(contactTypeId))
                .resume(resume)
                .infoValue(contactsInfo.getInfoValue())
                .build();
        contactInfoRepository.save(contactsInfo1);
    }

    private String getContactType(ContactsInfo info) {
        return contactInfoRepository.findById(info.getType().getId()).orElseThrow(NoSuchElementException::new).getType().getType();
    }

    @Override
    public List<ContactsInfoWithIdDto> getContactsByResumeId(Resume resume) {
        List<ContactsInfo> contacts;
        List<ContactsInfoWithIdDto> contactsList = new ArrayList<>();
        contacts = contactInfoRepository.findByResume(resume);

        contacts.forEach(e -> contactsList.add(ContactsInfoWithIdDto.builder()
                .id(resume.getId())
                .infoValue(e.getInfoValue())
                .type(e.getType().getType())
                .build()));
        return contactsList;

    }

}
