package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.ContactType.ContactTypeDto;
import kg.attractor.ht49.dto.ContactType.ContactsTypeWithIdDto;

public interface ContactsTypeService {
    ContactsTypeWithIdDto getContactTypeByName(String contactsType);

    Long createNewContacts(String type);

    ContactsTypeWithIdDto getContactTypeById(Long typeId);
}
