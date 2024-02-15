package com.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.Model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByTelephone(Long telephone);
}
