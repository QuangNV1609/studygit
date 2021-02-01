package com.quangnv.uet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quangnv.uet.entities.orm.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String>{

}
