package com.chat.requests;

import com.chat.Model.User;
import lombok.Data;

@Data
public class ContactPutRequestBody {
	Long id;
	Long userId;
	Long userAddedId;
	String nickname;
}
