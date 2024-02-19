package com.chat.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.chat.service.ContactService;
import com.chat.service.UserService;

import lombok.RequiredArgsConstructor;
import com.chat.Model.dto.ContactDTO;
import com.chat.Model.Contact;
import com.chat.requests.ContactPostRequestBody;
import com.chat.requests.ContactPutRequestBody;

@Component
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/contact")
public class ContactController {
	private final ContactService contactService;
	
	@GetMapping(path = "/{id}")
    public ResponseEntity<List<ContactDTO>> list(@PathVariable Long id){
        return ResponseEntity.ok(contactService.findUserContacts(id));
    }
	
	@GetMapping(path = "anonymous/{id}")
    public ResponseEntity <ContactDTO> getAnonymousContact(@PathVariable Long id){
        return ResponseEntity.ok(contactService.anonymousContact(id));
    }
	
	@PostMapping
    public ResponseEntity<ContactDTO> save(@RequestBody @Valid ContactPostRequestBody contactPostRequestBody){
        return new ResponseEntity<>(contactService.save(contactPostRequestBody), HttpStatus.CREATED);
    }
	
	@DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
		contactService.delete(id);
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
	@PutMapping
    public ResponseEntity<Void> replace(@RequestBody ContactDTO contactPutRequestBody){
		contactService.replace(contactPutRequestBody);
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}