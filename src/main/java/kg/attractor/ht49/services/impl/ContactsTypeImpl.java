package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dto.ContactType.ContactsTypeWithIdDto;
import kg.attractor.ht49.models.ContactType;
import kg.attractor.ht49.repositories.ContactTypeRepository;
import kg.attractor.ht49.services.interfaces.ContactsTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class ContactsTypeImpl implements ContactsTypeService {
    private final ContactTypeRepository contactTypeRepository;
    @Override
    public ContactsTypeWithIdDto getContactTypeByName(String contactsType) {
        ContactType contactType = contactTypeRepository.findByType(contactsType).orElseThrow(NoSuchElementException::new);
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
        return contactTypeRepository.save(contactType).getId();
    }

    @Override
    public ContactsTypeWithIdDto getContactTypeById(Long typeId) {
        ContactType types = contactTypeRepository.findById(typeId).orElse(null);
        if (types == null){
            return null;
        }
        return ContactsTypeWithIdDto.builder().id(types.getId()).type(types.getType()).build();
    }

    @Override
    public ContactType getTypeWithId(Long contactTypeId) {
        return contactTypeRepository.findById(contactTypeId).orElseThrow(NoSuchElementException::new);
    }

}
