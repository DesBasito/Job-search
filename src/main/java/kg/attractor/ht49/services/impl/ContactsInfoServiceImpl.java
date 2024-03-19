package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.ContactsDao;
import kg.attractor.ht49.models.ContactsInfo;
import kg.attractor.ht49.services.ContactsInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactsInfoServiceImpl implements ContactsInfoService {
    private final ContactsDao dao;

    @Override
    public void createNewContacts(ContactsInfo contactsInfo) {
        dao.createNewContactsInfo(contactsInfo);
    }
}
