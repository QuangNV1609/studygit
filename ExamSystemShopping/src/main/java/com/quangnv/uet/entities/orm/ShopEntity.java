package com.quangnv.uet.entities.orm;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;

import com.quangnv.uet.entities.BaseEntity;
import com.quangnv.uet.entities.autocreateid.StringPrefixedSequenceIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "shop")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShopEntity extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "shop_id", nullable = false, length = 10)
	@Id
	@GeneratedValue(generator = "SHOP_SHOP_ID_GENERATOR")
	@GenericGenerator(name = "SHOP_SHOP_ID_GENERATOR", strategy = "com.quangnv.uet.entities.autocreateid.StringPrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "SHO"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d") })
	private String shopId;

	@Column(name = "name", nullable = true, length = 10)
	private String name;

	@Column(name = "address", nullable = true, length = 50)
	private Integer address;

	@Column(name = "email", nullable = true, length = 50)
	private String email;

	@OneToMany(mappedBy = "foreign_key", targetEntity = PhoneEntity.class, fetch = FetchType.EAGER)
	private Set<PhoneEntity> phone = new HashSet<PhoneEntity>();

	@OneToOne(mappedBy = "shop", targetEntity = OrderEntity.class, fetch = FetchType.LAZY)
	private OrderEntity order;

}
