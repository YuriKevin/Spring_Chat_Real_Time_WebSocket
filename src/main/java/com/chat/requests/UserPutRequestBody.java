package com.chat.requests;

import lombok.Data;

@Data
public class UserPutRequestBody {
	Long telephone;
    String name;
    String password;
    String photo;
    String status;
}

