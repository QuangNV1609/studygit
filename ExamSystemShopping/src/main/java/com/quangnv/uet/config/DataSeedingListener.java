package com.quangnv.uet.config;

import java.util.HashSet;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.quangnv.uet.entities.orm.RoleEntity;
import com.quangnv.uet.entities.orm.UserEntity;
import com.quangnv.uet.repository.RoleRepository;
import com.quangnv.uet.repository.UserRepository;

//@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// Roles
		if (roleRepository.findByName("ADMIN").isPresent() == false) {
			roleRepository.save(new RoleEntity("1", "ADMIN"));
		}

		if (roleRepository.findByName("MEMBER").isPresent() == false) {
			roleRepository.save(new RoleEntity("2", "MEMBER"));
		}
		// Admin account
		if (userRepository.findOneByUsername("quangnv").isPresent() == false) {
			UserEntity admin = new UserEntity();
			admin.setUserId(UUID.randomUUID().toString());
			admin.setUsername("quangnv");
			admin.setPassword(passwordEncoder.encode("16092001"));
			HashSet<RoleEntity> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ADMIN").get());
			roles.add(roleRepository.findByName("MEMBER").get());
			admin.setRoles(roles);
			userRepository.save(admin);
		}
		// Admin account
		if (userRepository.findOneByUsername("phuong").isPresent() == false) {
			UserEntity user = new UserEntity();
			user.setUserId(UUID.randomUUID().toString());
			user.setUsername("phuong");
			user.setPassword(passwordEncoder.encode("26062001"));
			HashSet<RoleEntity> roles = new HashSet<>();
			roles.add(roleRepository.findByName("MEMBER").get());
			user.setRoles(roles);
			userRepository.save(user);
		}
	}

}
