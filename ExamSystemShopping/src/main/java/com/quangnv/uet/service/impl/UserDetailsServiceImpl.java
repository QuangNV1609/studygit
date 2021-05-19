package com.quangnv.uet.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quangnv.uet.dto.extend.ImageDto;
import com.quangnv.uet.dto.extend.UserDto;
import com.quangnv.uet.entities.orm.RoleEntity;
import com.quangnv.uet.entities.orm.UserEntity;
import com.quangnv.uet.exception.ResourceNotFoundException;
import com.quangnv.uet.repository.RoleRepository;
import com.quangnv.uet.repository.UserRepository;
import com.quangnv.uet.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = null;
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		if (redisTemplate.hasKey(username)) {
			userEntity = (UserEntity) operations.get(username);
			log.info("get user form redis!");
		} else {
			Optional<UserEntity> optional = Optional
					.ofNullable(userRepository.findOneByUsername(username).orElse(null));
			if (optional.isPresent()) {
				userEntity = optional.get();
				operations.set(username, userEntity, 60, TimeUnit.SECONDS);
			}
		}
		if (userEntity != null) {
			Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
			for (RoleEntity role : userEntity.getRoles()) {
				grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
			}
			return new User(userEntity.getUsername(), userEntity.getPassword(), grantedAuthorities);
		} else {
			log.info("UserDetailsServiceImpl.loadUserByUsername() throw  UsernameNotFoundException!");
			throw new UsernameNotFoundException(username + " don't exist!");
		}

	}

	@Override
	public UserDto getUserByUserId(String userId) {
		Optional<UserEntity> optional = Optional.ofNullable(userRepository.findById(userId).orElse(null));
		if (optional.isPresent()) {
			UserDto userDto = modelMapper.map(optional.get(), UserDto.class);
			userDto.setPassword(null);
			ImageDto imageDto = userDto.getCustomer().getImage();
			imageDto.setDownloadUri("http://localhost:8081/image/download/" + imageDto.getImageId());
			return userDto;
		} else {
			throw new ResourceNotFoundException(userId + " doesn't exist!");
		}
	}

	@Override
	public UserDto saveUser(UserDto userDto) {
		String userId = UUID.randomUUID().toString();
		RoleEntity roleEntity = roleRepository.findByName("MEMBER").get();
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		Set<RoleEntity> roles = new HashSet<RoleEntity>();
		roles.add(roleEntity);

		userEntity.setUserId(userId);
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		userEntity.setRoles(roles);

		userRepository.save(userEntity);
		return modelMapper.map(userEntity, UserDto.class);
	}

	@Override
	public String getUserId(String userName) {
		Optional<UserEntity> optional = Optional.ofNullable(userRepository.findOneByUsername(userName).orElse(null));
		if (optional.isPresent()) {
			return optional.get().getUserId();
		} else {
			return null;
		}
	}
	
	

}
