package com.rugwed.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rugwed.blog.config.AppConstants;
import com.rugwed.blog.entity.Role;
import com.rugwed.blog.entity.User;
import com.rugwed.blog.exceptions.ResourceNotFoundException;
import com.rugwed.blog.payloads.UserDTO;
import com.rugwed.blog.repository.RolesRepo;
import com.rugwed.blog.repository.UserRepo;
import com.rugwed.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private RolesRepo rolesRepo;

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = this.userDTO(userDTO);
		User savedUser = this.userRepo.save(user);

		return this.userToDto(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setAbout(userDTO.getAbout());
		User updatedUser = this.userRepo.save(user);
		UserDTO userDtol = this.userToDto(updatedUser);
		return userDtol;
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDTO> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id", userId));
		this.userRepo.delete(user);
	}

	private User userDTO(UserDTO userDTO) {

		// by modelMapper
		User user = this.modelMapper.map(userDTO, User.class);

		// normal
//		User user = new User();
//		user.setId(userDTO.getId());
//		user.setName(userDTO.getName());
//		user.setEmail(userDTO.getEmail());
//		user.setPassword(userDTO.getPassword());
//		user.setAbout(userDTO.getAbout());
		return user;
	}

	public UserDTO userToDto(User user) {

//		by modelMapper
		UserDTO userDto = this.modelMapper.map(user, UserDTO.class);

		// normal
//		UserDTO userDto = new UserDTO();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

	@Override
	public UserDTO registerNewUser(UserDTO userdto) {

		User user = this.modelMapper.map(userdto, User.class);

		user.setPassword(this.encoder.encode(user.getPassword()));
		Role role=this.rolesRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		User save = this.userRepo.save(user);
		return this.modelMapper.map(save, UserDTO.class);
	}

}
