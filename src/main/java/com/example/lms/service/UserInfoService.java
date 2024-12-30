package com.example.lms.service;

import java.util.List;

import com.example.lms.model.UserInfo;

public interface UserInfoService {
	public List<UserInfo> allUsers();
	String addUser(UserInfo userInfo);

}
