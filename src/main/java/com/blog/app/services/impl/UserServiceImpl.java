package com.blog.app.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.repository.*;
import com.blog.app.entities.User;
import com.blog.app.exception.ResourceNotFoundException;
import com.blog.app.payload.UserDto;
import com.blog.app.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto creatUser(UserDto userDto) {
		
		User user= this.dtoToUser(userDto);
		User saveduser =  this.userrepo.save(user);
		return this.userToDto(saveduser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user =this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		
		User updatedUser= this.userrepo.save(user);
		UserDto userDto1=this.userToDto(updatedUser);
		return userDto1;
		
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user= this.userrepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		 return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = this.userrepo.findAll();
		 List<UserDto> userDto =users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
	
		 return userDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		    
		User user= this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("USer","Id",userId));
                   this.userrepo.delete(user);
	}
	
	private User dtoToUser(UserDto userDto) {
		
		User user= this.modelMapper.map(userDto, User.class);
		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
		
	}
	
	private UserDto userToDto(User user) {
		
		UserDto userdto=this.modelMapper.map(user, UserDto.class);
		
//		userdto.setId(user.getId());
//		userdto.setName(user.getName());
//		userdto.setAbout(user.getAbout());
//		userdto.setEmail(user.getEmail());
//		userdto.setPassword(user.getPassword());
		
		return userdto;
		
		
	}

}
