package com.quangnv.uet.entities.pk;

import java.io.Serializable;
import javax.persistence.*;

import com.quangnv.uet.entities.orm.CartEntity;
import com.quangnv.uet.entities.orm.ProductEntity;

@Embeddable
public class CartDetailPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne(targetEntity = ProductEntity.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns(value = {
			@JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false) }, foreignKey = @ForeignKey(name = "FKcart_detai482723"))
	private ProductEntity product;

	public void setProduct(ProductEntity value) {
		this.product = value;
	}

	public ProductEntity getProduct() {
		return this.product;
	}

	@ManyToOne(targetEntity = CartEntity.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns(value = {
			@JoinColumn(name = "cart_id", referencedColumnName = "cart_id", nullable = false) }, foreignKey = @ForeignKey(name = "FKcart_detai195314"))
	private CartEntity cart;

}
