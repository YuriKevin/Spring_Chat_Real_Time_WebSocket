package com.chat.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Contact {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 	Long id;
	 	Long userId;
		@ManyToOne
	    @JoinColumn(name = "user_added_id")
	    User userAdded;
    	String nickname;
}

