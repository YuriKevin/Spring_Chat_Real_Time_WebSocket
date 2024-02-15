package com.chat.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.chat.Model.Contact;
import com.chat.Model.User;
import com.chat.Model.dto.ContactDTO;
import com.chat.repository.ContactRepository;
import com.chat.requests.ContactPostRequestBody;
import com.chat.requests.ContactPutRequestBody;
import com.chat.requests.UserPostRequestBody;
import com.chat.requests.UserPutRequestBody;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactService {
	private final ContactRepository contactRepository;
	private final UserService userService;
	
	public Contact findByIdOrThrowBadRequestException(Long id) {
        return contactRepository.findById(id)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contato Não encontrado"));
 	}
	
	@Transactional
    public ContactDTO save(ContactPostRequestBody contactPostRequestBody) {

			 Contact savedContact = contactRepository.save(Contact.builder()
	    			.userId(userService.findByIdOrThrowBadRequestException(contactPostRequestBody.getUserId()).getTelephone())
	    			.userAdded(userService.findByIdOrThrowBadRequestException(contactPostRequestBody.getUserAddedId()))
	    			.nickname(contactPostRequestBody.getNickname())
	    			.build());
			 
			 ContactDTO contactDTO = ContactDTO.builder()
		                .id(savedContact.getId())
		                .telephone(savedContact.getUserAdded().getTelephone())
		                .nickname(savedContact.getNickname())
		                .name(savedContact.getUserAdded().getName())
		                .photo(savedContact.getUserAdded().getPhoto())
		                .status(savedContact.getUserAdded().getStatus())
		                .build();
			 
			 return contactDTO;
    }
	
	public void delete(Long id) {
		contactRepository.delete(findByIdOrThrowBadRequestException(id));
	}
	
	@Transactional
	public void replace(ContactPutRequestBody contactPutRequestBody) {
		Contact savedContact = findByIdOrThrowBadRequestException(contactPutRequestBody.getId());
		User savedUser = userService.findByIdOrThrowBadRequestException(contactPutRequestBody.getUserId());
		Contact contact = Contact.builder()
                .id(savedContact.getId())
                .userId(contactPutRequestBody.getUserId())
                .userAdded(userService.findByIdOrThrowBadRequestException(contactPutRequestBody.getUserAddedId()))
                .nickname(contactPutRequestBody.getNickname())
                .build();
        contactRepository.save(contact);
    }
	
	public List<ContactDTO> findUserContacts(Long id){
		
		List<Contact> contactList = contactRepository.findByUserId(id);
		if(contactList.isEmpty()) {
			return null;
		}
		
		List<ContactDTO> contactDTOList = new ArrayList<>();
		
		for(Contact contact:contactList) {
			ContactDTO contactDTO = ContactDTO.builder()
					.id(contact.getId())
					.telephone(contact.getUserAdded().getTelephone())
					.nickname(contact.getNickname())
					.name(contact.getUserAdded().getName())
					.photo(contact.getUserAdded().getPhoto())
					.status(contact.getUserAdded().getStatus())
					.build();
			
			contactDTOList.add(contactDTO);
		}
		
		return contactDTOList;
		
	}
	
	public void deleteContactsUser(Long id) {
		contactRepository.deleteByUserId(id);
	}
	
	
}
