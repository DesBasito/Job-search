package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.ContactInfo.ContactsInfoDto;
import kg.attractor.ht49.dto.ContactInfo.ContactsInfoWithIdDto;

import java.util.List;

public interface ContactsInfoService {

    void createNewContactsInfo(ContactsInfoDto contactsInfo, Long id);

    List<ContactsInfoWithIdDto> getContactsByResumeId(Long id);
}
