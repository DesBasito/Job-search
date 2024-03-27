package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.ContactsTypeDao;
import kg.attractor.ht49.dto.ContactType.ContactsTypeWithIdDto;
import kg.attractor.ht49.models.ContactType;
import kg.attractor.ht49.services.interfaces.ContactsTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ContactsTypeImpl implements ContactsTypeService {
    private final ContactsTypeDao dao;
    @Override
    public ContactsTypeWithIdDto getContactTypeByName(String contactsType) {
        ContactType contactType = dao.getContactTypeByName(contactsType).orElse(null);
        if (contactType==null){
            return null;
        }
        return ContactsTypeWithIdDto.builder()
                .id(contactType.getId())
                .type(contactType.getType())
                .build();
    }

    @Override
    public Long createNewContacts(String type) {
        ContactType contactType = ContactType.builder()
                .type(type)
                .build();
        return dao.createAndReturnId(contactType);
    }

    @Override
    public ContactsTypeWithIdDto getContactTypeById(Long typeId) {
        ContactType types = dao.getContactTypeById(typeId).orElse(null);
        if (types == null){
            return null;
        }
        return ContactsTypeWithIdDto.builder().id(types.getId()).type(types.getType()).build();
    }

}
