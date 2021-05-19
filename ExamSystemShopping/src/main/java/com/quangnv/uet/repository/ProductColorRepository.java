package com.quangnv.uet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quangnv.uet.entities.orm.ProductColorEntity;
import com.quangnv.uet.entities.pk.ProductColorPK;

public interface ProductColorRepository extends JpaRepository<ProductColorEntity, ProductColorPK> {
	@Query("SELECT p FROM ProductColorEntity p WHERE p.product.productId = :productId")
	public List<ProductColorEntity> findByProductId(@Param("productId") String productId);

}
