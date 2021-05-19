package com.quangnv.uet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quangnv.uet.entities.orm.ImageEntity;
import com.quangnv.uet.entities.orm.ProductEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, String> {
	@Query("SELECT i FROM ImageEntity i WHERE i.product = :product")
	public List<ImageEntity> findAllByProduct(@Param("product") ProductEntity productEntity);
	
	@Query("SELECT i FROM ImageEntity i WHERE i.customer.customerId = :customerId")
	public ImageEntity findOneByCustomer(@Param("customerId") String customerId);
}
