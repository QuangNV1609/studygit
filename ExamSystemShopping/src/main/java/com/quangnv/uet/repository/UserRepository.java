package com.quangnv.uet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quangnv.uet.entities.orm.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
	public Optional<UserEntity> findOneByUsername(String username);
	
}
