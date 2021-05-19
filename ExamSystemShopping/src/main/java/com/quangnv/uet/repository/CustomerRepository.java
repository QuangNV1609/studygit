package com.quangnv.uet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quangnv.uet.entities.orm.CustomerEntity;
import com.quangnv.uet.entities.orm.UserEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String>{
	@Query("Select c From CustomerEntity c Where c.user.userId = :userId")
	public Optional<CustomerEntity> findOneByUserId(@Param("userId") String userId);
	
	public Optional<CustomerEntity> findOneByUser(UserEntity userEntity);
	

}
