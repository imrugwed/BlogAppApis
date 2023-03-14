package com.rugwed.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rugwed.blog.payloads.UserDTO;

@Service
public interface UserService {

	UserDTO registerNewUser(UserDTO user);

	UserDTO createUser(UserDTO user);

	UserDTO updateUser(UserDTO user, Integer id);

	UserDTO getUserById(Integer useriId);

	List<UserDTO> getAllUsers();

	void deleteUser(Integer userId);
}