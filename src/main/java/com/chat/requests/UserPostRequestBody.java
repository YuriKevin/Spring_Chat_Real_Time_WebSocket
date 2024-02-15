package com.chat.requests;

import java.util.List;

import com.chat.Model.Contact;

import lombok.Data;

@Data
public class UserPostRequestBody {
	Long telephone;
    String name;
    String password;
    String photo;
    String status;
}

