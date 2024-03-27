package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.ContactsDao;
import kg.attractor.ht49.dto.ContactInfo.ContactsInfoDto;
import kg.attractor.ht49.dto.ContactInfo.ContactsInfoWithIdDto;
import kg.attractor.ht49.models.ContactsInfo;
import kg.attractor.ht49.services.interfaces.ContactsInfoService;
import kg.attractor.ht49.services.interfaces.ContactsTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactsInfoServiceImpl implements ContactsInfoService {
    private final ContactsDao dao;
    private final ContactsTypeService contacts;

    @Override
    public void createNewContactsInfo(ContactsInfoDto contactsInfo, Long id) {
        Long contactTypeId;
        if (contacts.getContactTypeByName(contactsInfo.getType()) == null) {
            contactTypeId = contacts.createNewContacts(contactsInfo.getType());
        }else {
            contactTypeId = contacts.getContactTypeByName(contactsInfo.getType()).getId();
        }
        ContactsInfo contactsInfo1 = ContactsInfo.builder()
                .typeId(contactTypeId)
                .resumeId(id)
                .infoValue(contactsInfo.getInfoValue())
                .build();
        dao.createNewContactsInfo(contactsInfo1);
    }

    @Override
    public List<ContactsInfoWithIdDto> getContactsByResumeId(Long id) {
        List<ContactsInfoWithIdDto> dtos = new ArrayList<>();
        dao.getContactsInfoByResumeId(id).forEach(e -> dtos.add(ContactsInfoWithIdDto.builder()
                        .id(e.getId())
                        .type(contacts.getContactTypeById(e.getTypeId()).getType())
                        .infoValue(e.getInfoValue())
                .build()));
        return dtos;
    }
}
