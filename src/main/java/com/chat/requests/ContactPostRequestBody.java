package com.chat.requests;

import com.chat.Model.User;

import lombok.Data;

@Data
public class ContactPostRequestBody {
	Long userId;
	Long userAddedId;
	String nickname;
}

