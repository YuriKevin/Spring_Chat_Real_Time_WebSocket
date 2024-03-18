package com.chat.Model.dto;

import com.chat.Model.Contact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactDTO {
	private Long id;
	private Long telephone;
	private String nickname;
	private String name;
	private String photo;
	private String status;
}

