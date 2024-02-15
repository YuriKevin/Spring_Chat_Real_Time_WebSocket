package com.chat.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.server.ResponseStatusException;

import com.chat.Model.Contact;
import com.chat.Model.User;
import com.chat.Model.dto.ContactDTO;
import com.chat.requests.ContactPostRequestBody;
import com.chat.requests.ContactPutRequestBody;
import com.chat.requests.UserPostRequestBody;
import com.chat.requests.UserPutRequestBody;
import com.chat.service.ContactService;
import com.chat.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	private final UserService userService;

	@GetMapping(path = "/{id}")
    public ResponseEntity<User> find(@PathVariable Long id){
		System.out.println(id);
        return ResponseEntity.ok(userService.findByIdOrThrowBadRequestException(id));
    }
	
	@PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid UserPostRequestBody userPostRequestBody){
        return new ResponseEntity<>(userService.save(userPostRequestBody), HttpStatus.CREATED);
    }
	
	@DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
		userService.delete(id);
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
	@PutMapping
    public ResponseEntity<Void> replace(@RequestBody UserPutRequestBody userPutRequestBody){
		userService.replace(userPutRequestBody);
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

//throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID não pode ser nulo");