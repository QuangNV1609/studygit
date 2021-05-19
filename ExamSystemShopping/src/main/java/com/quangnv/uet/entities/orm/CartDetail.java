package com.quangnv.uet.entities.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
import org.hibernate.annotations.Proxy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.quangnv.uet.entities.BaseEntity;
import com.quangnv.uet.entities.pk.CartDetailPK;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Proxy(lazy = false)
@EntityListeners(AuditingEntityListener.class)
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

	@PrimaryKeyJoinColumn
	@ManyToOne(targetEntity = ColorEntity.class, fetch = FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "color_id", referencedColumnName = "color_id", nullable = false) }, foreignKey = @ForeignKey(name = "FKcart_detai195315"))
	private ColorEntity color;

	@Column(name = "color_id", nullable = false, insertable = false, updatable = false)
	@Id
	@GeneratedValue(generator = "CART_DETAIL_CARTID_GENERATOR")
	@GenericGenerator(name = "CART_DETAIL_CARTID_GENERATOR", strategy = "foreign", parameters = @org.hibernate.annotations.Parameter(name = "property", value = "color"))
	private String colorId;

	@Column(name = "quantity", nullable = true)
	private Integer quantity;

	@Column(name = "price", nullable = true)
	private Float price;

	@Column(name = "status", nullable = true)
	private Boolean status;

}
