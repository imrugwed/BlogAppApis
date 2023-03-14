package com.rugwed.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rugwed.blog.entity.User;
import com.rugwed.blog.exceptions.ResourceNotFoundException;
import com.rugwed.blog.repository.UserRepo;

@Service
//("userDetailsService")
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//loading user from DB by userName
		User user = this.repo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("user", "email : "+username, 0));
		return user;
	}
}
