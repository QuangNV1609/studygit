package com.quangnv.uet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quangnv.uet.entities.orm.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String>{
	public Optional<CategoryEntity> findOneByName(String categoryName);
}
