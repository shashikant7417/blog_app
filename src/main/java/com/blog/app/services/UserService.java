package com.blog.app.services;

import java.util.List;

import com.blog.app.entities.User;

import com.blog.app.payload.UserDto;

public interface UserService {
	
	
	UserDto creatUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUser();
	void deleteUser(Integer userId);
	

}
