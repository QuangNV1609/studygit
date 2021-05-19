package com.quangnv.uet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quangnv.uet.entities.orm.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {
	
	public Optional<RoleEntity> findByName(String name);
	
}
