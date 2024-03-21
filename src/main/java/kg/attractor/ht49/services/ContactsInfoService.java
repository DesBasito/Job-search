package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.ContactsInfoDto;

public interface ContactsInfoService {

    void createNewContactsInfo(ContactsInfoDto contactsInfo, Long id);
}
