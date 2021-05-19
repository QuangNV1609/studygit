package com.quangnv.uet.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.quangnv.uet.dto.extend.UserDto;

public interface UserService extends UserDetailsService{
	
	public UserDto getUserByUserId(String userId);
	
	public UserDto saveUser(UserDto userDto);
	
	public String getUserId(String userName);
	
}
