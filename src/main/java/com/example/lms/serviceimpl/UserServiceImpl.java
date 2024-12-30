package com.example.lms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.lms.model.UserInfo;
import com.example.lms.repository.UserRepository;
import com.example.lms.service.UserInfoService;

@Service
public class UserServiceImpl implements UserInfoService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	boolean flag;
	
	 public List<UserInfo> allUsers() {
	        List<UserInfo> users = new ArrayList<>();

	        userRepo.findAll().forEach(users::add);

	        return users;
	    }

	 @Override
		public String addUser(UserInfo userInfo) {
			
			List<UserInfo> userList = userRepo.findAll();
			
			if(null != userList) {
				for(UserInfo user : userList) {
					if(user.getFullName().equals(userInfo.getFullName()))
						flag = true;
					else
						flag = false;
				}
			}
			
			if(flag) {
				return "User already exists";
			} else {
				userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
				userRepo.save(userInfo);
				return "User saved";
			}
		}

}
