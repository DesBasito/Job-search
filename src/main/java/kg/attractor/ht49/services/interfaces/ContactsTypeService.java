package kg.attractor.ht49.services.interfaces;

import kg.attractor.ht49.dto.ContactType.ContactTypeDto;
import kg.attractor.ht49.dto.ContactType.ContactsTypeWithIdDto;
import kg.attractor.ht49.models.ContactType;

public interface ContactsTypeService {
    ContactsTypeWithIdDto getContactTypeByName(String contactsType);

    Long createNewContacts(String type);

    ContactsTypeWithIdDto getContactTypeById(Long typeId);

    ContactType getTypeWithId(Long contactTypeId);
}
