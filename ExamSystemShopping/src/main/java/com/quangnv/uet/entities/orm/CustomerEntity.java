package com.quangnv.uet.entities.orm;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	private Integer name;

	@Column(name = "age", nullable = true, length = 3)
	private Integer age;

	@Column(name = "address", nullable = true, length = 50)
	private Integer address;

	@Column(name = "email", nullable = true, length = 50)
	private String email;

	@Column(name = "gender", nullable = true)
	private Boolean gender;

	@OneToOne(optional = false, targetEntity = UserEntity.class, fetch = FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false) }, foreignKey = @ForeignKey(name = "FKcustomer195968"))
	private UserEntity user;

	@OneToMany(mappedBy = "foreign_key1", targetEntity = PhoneEntity.class, fetch = FetchType.EAGER)
	private Set<PhoneEntity> phone = new HashSet<PhoneEntity>();

	@OneToOne(mappedBy = "customer", targetEntity = CartEntity.class, fetch = FetchType.LAZY)
	private CartEntity cart;

}
