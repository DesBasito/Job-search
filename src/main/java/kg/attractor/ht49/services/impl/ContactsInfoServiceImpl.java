package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.ContactsDao;
import kg.attractor.ht49.dto.ContactsInfoDto;
import kg.attractor.ht49.models.ContactsInfo;
import kg.attractor.ht49.services.ContactsInfoService;
import kg.attractor.ht49.services.ContactsTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
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
}
