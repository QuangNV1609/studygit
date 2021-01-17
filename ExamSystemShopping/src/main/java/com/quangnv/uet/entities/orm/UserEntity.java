package com.quangnv.uet.entities.orm;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.quangnv.uet.entities.BaseEntity;
import com.quangnv.uet.entities.autocreateid.StringPrefixedSequenceIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "username")
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
	@GeneratedValue(generator = "USERNAME_USER_ID_GENERATOR")
	@GenericGenerator(name = "USERNAME_USER_ID_GENERATOR", strategy = "com.quangnv.uet.entities.autocreateid.StringPrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "USE"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d") })
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
