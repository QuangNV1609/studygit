package com.quangnv.uet.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.User;

public interface JwtTokenService {
	public String generateTokenLogin(User user);

	public String getUsernameFormToken(String token);

	public Boolean validateTokenLogin(String token);

	public String getJwtFormRequest(HttpServletRequest httpServletRequest);
}
