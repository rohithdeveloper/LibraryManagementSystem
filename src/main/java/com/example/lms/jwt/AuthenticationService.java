package com.example.lms.jwt;

import com.example.lms.dtos.LoginUserDto;
import com.example.lms.dtos.RegisterUserDto;
import com.example.lms.model.UserInfo;

public interface AuthenticationService{

	 String signup(RegisterUserDto input) ;
	 UserInfo authenticate(LoginUserDto input);
}
