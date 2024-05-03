package kg.attractor.ht49.services.interfaces;

import kg.attractor.ht49.dto.ContactInfo.ContactsInfoDto;
import kg.attractor.ht49.dto.ContactInfo.ContactsInfoWithIdDto;
import kg.attractor.ht49.models.Resume;

import java.util.List;

public interface ContactsInfoService {

    void createNewContactsInfo(ContactsInfoDto contactsInfo, Resume r);

    List<ContactsInfoWithIdDto> getContactsByResumeId(Resume id);
}
