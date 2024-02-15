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
	Long id;
	Long telephone;
	String nickname;
    String name;
    String photo;
    String status;
}
