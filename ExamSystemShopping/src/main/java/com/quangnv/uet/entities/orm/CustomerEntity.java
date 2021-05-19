package com.quangnv.uet.entities.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Proxy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.quangnv.uet.entities.BaseEntity;
import com.quangnv.uet.entities.autocreateid.StringPrefixedSequenceIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Proxy(lazy = false)
@Table(name = "customer")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "customer_id", nullable = false, length = 10)
	@Id
	@GeneratedValue(generator = "CUSTOMER_CUSTOMER_ID_GENERATOR")
	@org.hibernate.annotations.GenericGenerator(name = "CUSTOMER_CUSTOMER_ID_GENERATOR", strategy = "com.quangnv.uet.entities.autocreateid.StringPrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "CUS"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d") })
	private String customerId;

	@Column(name = "name", nullable = true, length = 30)
	private String name;

	@Column(name = "age", nullable = true)
	private Integer age;

	@Column(name = "address", nullable = true, length = 50)
	private String address;

	@Column(name = "email", nullable = true, length = 50)
	private String email;

	@Column(name = "phone", nullable = true, length = 10)
	private String phone;

	@Column(name = "gender", nullable = true)
	private Boolean gender;

	@OneToOne(optional = false, targetEntity = UserEntity.class, fetch = FetchType.LAZY)
	private UserEntity user;

	@OneToOne(mappedBy = "customer", targetEntity = CartEntity.class, fetch = FetchType.LAZY)
	private CartEntity cart;

	@OneToOne(mappedBy = "customer", targetEntity = ImageEntity.class, fetch = FetchType.LAZY)
	private ImageEntity image;

}
