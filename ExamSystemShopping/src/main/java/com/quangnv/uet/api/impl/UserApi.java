package com.quangnv.uet.api.impl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quangnv.uet.dto.extend.UserDto;
import com.quangnv.uet.service.JwtTokenService;
import com.quangnv.uet.service.UserService;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserApi {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userDetailService;

	@Autowired
	private JwtTokenService jwtTokenService;

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") String userId) {
		UserDto userDto = userDetailService.getUserByUserId(userId);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<String> login(@RequestBody @Valid UserDto userDto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		User user = (User) authentication.getPrincipal();
		String jwt = jwtTokenService.generateTokenLogin(user);
		return new ResponseEntity<String>(jwt, HttpStatus.OK);
	}

	@PostMapping(value = "/add-user")
	public ResponseEntity<UserDto> saveUser(@RequestBody @Valid UserDto userDto) {
		userDto = userDetailService.saveUser(userDto);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);

	}

	@GetMapping
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public ResponseEntity<String> getuserId() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = userDetailService.getUserId(user.getUsername());
		return new ResponseEntity<String>(userId, HttpStatus.OK);
	}

	@GetMapping(value = "/test")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String test() {
		return "test";
	}

}
