package com.blog.app.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	
	private int id;
	@NotEmpty
	@Size(min=3,message="Name must be of minimum 3 character !! ")
	private String name;
	@Email(message="Please Enter valid Email !!")
	private String email;
	@NotNull
	@Size(min=3,max=10,message="Password must be greator that 3 charc and less tha 11 charc !!")
	//@Pattern(regexp = )
	private String password;
	@NotEmpty
	private String about;

}
