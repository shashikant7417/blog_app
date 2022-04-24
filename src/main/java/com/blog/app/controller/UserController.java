package com.blog.app.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.payload.ApiResponse;
import com.blog.app.payload.UserDto;
import com.blog.app.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	// POST Create User
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid@RequestBody UserDto userDto){
		
		UserDto createdUserDto = this.userService.creatUser(userDto);
		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
		
		
	}
	
	// PUT Update User
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid@RequestBody UserDto userDto,@PathVariable Integer userId){
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
		
	}
	
	// DELETE Delete user
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		this.userService.deleteUser(userId);
		return new ResponseEntity(new ApiResponse("User deleted Seccessfully", true),HttpStatus.OK);
		
	}
	
	
	// GET Get All User
	@GetMapping("/")
	public ResponseEntity <List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUser());
		
	}
	
	//GET Get User By Id
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));
		
	}
	

}
