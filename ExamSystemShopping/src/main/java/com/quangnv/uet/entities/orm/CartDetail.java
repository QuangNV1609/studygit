package com.quangnv.uet.entities.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.quangnv.uet.entities.BaseEntity;
import com.quangnv.uet.entities.pk.CartDetailPK;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "cart_detail")
@IdClass(CartDetailPK.class)
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartDetail extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKeyJoinColumn
	@ManyToOne(targetEntity = ProductEntity.class, fetch = FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false) }, foreignKey = @ForeignKey(name = "FKcart_detai482723"))
	private ProductEntity product;

	@Column(name = "product_id", nullable = false, insertable = false, updatable = false)
	@Id
	@GeneratedValue(generator = "CART_DETAIL_PRODUCTID_GENERATOR")
	@GenericGenerator(name = "CART_DETAIL_PRODUCTID_GENERATOR", strategy = "foreign", parameters = @org.hibernate.annotations.Parameter(name = "property", value = "product"))
	private String productId;

	@PrimaryKeyJoinColumn
	@ManyToOne(targetEntity = CartEntity.class, fetch = FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "cart_id", referencedColumnName = "cart_id", nullable = false) }, foreignKey = @ForeignKey(name = "FKcart_detai195314"))
	private CartEntity cart;

	@Column(name = "cart_id", nullable = false, insertable = false, updatable = false)
	@Id
	@GeneratedValue(generator = "CART_DETAIL_CARTID_GENERATOR")
	@GenericGenerator(name = "CART_DETAIL_CARTID_GENERATOR", strategy = "foreign", parameters = @org.hibernate.annotations.Parameter(name = "property", value = "cart"))
	private String cartId;

	@Column(name = "quantity", nullable = true, length = 3)
	private Integer quantity;

	@Column(name = "price", nullable = true, length = 10)
	private Float price;

	@Column(name = "status", nullable = true)
	private Boolean status;

	@Column(name = "color", nullable = true, length = 10)
	private Integer color;

}
