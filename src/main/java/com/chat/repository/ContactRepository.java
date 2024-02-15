package com.chat.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chat.Model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{
	List<Contact> findByUserId(Long userId);
	void deleteByUserId(Long userId);
}

