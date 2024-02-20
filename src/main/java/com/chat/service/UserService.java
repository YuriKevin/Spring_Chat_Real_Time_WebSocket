package com.chat.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.chat.Model.User;
import com.chat.repository.ContactRepository;
import com.chat.repository.UserRepository;
import com.chat.requests.UserPostRequestBody;
import com.chat.requests.UserPutRequestBody;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final ContactRepository contactRepository;
	
	public User findByIdOrThrowBadRequestException(Long telephone) {
        return userRepository.findById(telephone)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado"));
 	}
	public User login(Long telephone, String password) {
		System.out.println("telephone: "+ telephone+ " senha: "+password);
        User user = userRepository.findById(telephone)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado"));
        
        if(password.equals(user.getPassword())) {
        	return user;
        }
        else {
        	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário ou senha incorretos.");
        }
 	}
	
	@Transactional
    public User save(UserPostRequestBody userPostRequestBody) {
		if(userRepository.findByTelephone(userPostRequestBody.getTelephone()) != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário com o telefone informado já existe.");
		}
		if (check11Digits(userPostRequestBody.getTelephone())) {
				return userRepository.save(User.builder()
		    			.telephone(userPostRequestBody.getTelephone())
		    			.name(userPostRequestBody.getName())
		    			.password(userPostRequestBody.getPassword())
		                .photo(userPostRequestBody.getPhoto())
		                .status(userPostRequestBody.getStatus())
		    			.build());
        }
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O telefone deve conter 11 dígitos.");
		}
        	
    }
	
	public static boolean check11Digits(Long numero) {
        String numeroString = String.valueOf(numero);
        return numeroString.length() == 11;
    }
	
	@Transactional
	public void delete(Long id) {
		userRepository.delete(findByIdOrThrowBadRequestException(id));
		contactRepository.deleteByUserId(id);
		//contactRepository delete user number of another phones
	}
	
	@Transactional
	public void replace(UserPutRequestBody userPutRequestBody) {
        User savedUser = findByIdOrThrowBadRequestException(userPutRequestBody.getTelephone());
        User user = User.builder()
                .telephone(savedUser.getTelephone())
                .name(userPutRequestBody.getName())
                .password(userPutRequestBody.getPassword())
                .photo(userPutRequestBody.getPhoto())
                .status(userPutRequestBody.getStatus())
                .build();
        userRepository.save(user);
    }
}
