package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.ContactsDao;
import kg.attractor.ht49.dto.ContactInfo.ContactsInfoDto;
import kg.attractor.ht49.dto.ContactInfo.ContactsInfoWithIdDto;
import kg.attractor.ht49.models.ContactType;
import kg.attractor.ht49.models.ContactsInfo;
import kg.attractor.ht49.services.interfaces.ContactsInfoService;
import kg.attractor.ht49.services.interfaces.ContactsTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
//                .typeId(contactTypeId)
//                .resumeId(id)
                .infoValue(contactsInfo.getInfoValue())
                .build();
        dao.createNewContactsInfo(contactsInfo1);
    }

    private String getContactType(ContactsInfo info){
//        return contacts.getContactTypeById(info.getTypeId()).getType();
        return null;
    }

    @Override
    public List<ContactsInfoWithIdDto> getContactsByResumeId(Long id) {
        List<ContactsInfoWithIdDto> contacts = new ArrayList<>();
        dao.getContactsInfoByResumeId(id).forEach(c -> contacts.add(ContactsInfoWithIdDto.builder()
                        .id(id)
                        .type(getContactType(c))
                        .infoValue(c.getInfoValue())
                .build()));
        return contacts;
    }

}
