package com.quangnv.uet.entities.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import com.quangnv.uet.entities.orm.CartEntity;
import com.quangnv.uet.entities.orm.ColorEntity;
import com.quangnv.uet.entities.orm.ProductEntity;

import lombok.Data;
import lombok.ToString;

@Embeddable
@Data
@ToString
public class CartDetailPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne(targetEntity = ProductEntity.class, fetch = FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false) }, foreignKey = @ForeignKey(name = "FKcart_detai482723"))
	private ProductEntity product;

	@ManyToOne(targetEntity = CartEntity.class, fetch = FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "cart_id", referencedColumnName = "cart_id", nullable = false) }, foreignKey = @ForeignKey(name = "FKcart_detai195314"))
	private CartEntity cart;

	@ManyToOne(targetEntity = ColorEntity.class, fetch = FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "color_id", referencedColumnName = "color_id", nullable = false) }, foreignKey = @ForeignKey(name = "FKcart_detai195315"))
	private ColorEntity color;

}
