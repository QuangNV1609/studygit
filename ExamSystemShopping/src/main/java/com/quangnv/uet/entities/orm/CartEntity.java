package com.quangnv.uet.entities.orm;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.quangnv.uet.entities.BaseEntity;
import com.quangnv.uet.entities.autocreateid.StringPrefixedSequenceIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "cart")
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartEntity extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "cart_id", nullable = false, length = 10)
	@Id
	@GeneratedValue(generator = "CART_CART_ID_GENERATOR")
	@GenericGenerator(name = "CART_CART_ID_GENERATOR", strategy = "com.quangnv.uet.entities.autocreateid.StringPrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "CAR"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d") })
	private String cartId;

	@OneToOne(optional = false, targetEntity = CustomerEntity.class, fetch = FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false) }, foreignKey = @ForeignKey(name = "FKcart67206"))
	private CustomerEntity customer;

	@OneToMany(mappedBy = "cart", targetEntity = CartDetail.class, fetch = FetchType.EAGER)
	@Builder.Default
	private Set<CartDetail> cartDetail = new HashSet<CartDetail>();

	@OneToOne(mappedBy = "cart", targetEntity = OrderEntity.class, fetch = FetchType.LAZY)
	private OrderEntity order;

}
