package com.rugwed.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import com.rugwed.blog.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDTO {
	
	private int id;

	@NotNull
	@Size(min=4, message="username must be min of 4 character")
	private String name;

	@Email(message ="email is not valid")
	private String email;

	@NotNull
	@Size(min =3,max =10,message = "")
	//@Pattern(regexp = "")
	private String password;

	@NotNull 
	private String about;
	
	private Set<RoleDTO> roles = new HashSet<>();

}
