package com.quangnv.uet.entities.orm;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.quangnv.uet.entities.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Proxy(lazy = false)
@Table(name = "user")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "user_id", nullable = false, length = 10)
	@Id
	private String userId;

	@Column(name = "username", nullable = true, length = 100)
	private String username;

	@Column(name = "password", nullable = true, length = 30)
	private String password;

	@Column(name = "role_id", nullable = true, length = 15)
	private String role_id;

	@ManyToMany(mappedBy = "user", targetEntity = RoleEntity.class)
	private Set<RoleEntity> role = new HashSet<RoleEntity>();

	@OneToOne(mappedBy = "user", targetEntity = CustomerEntity.class, fetch = FetchType.LAZY)
	private CustomerEntity customer;

}
