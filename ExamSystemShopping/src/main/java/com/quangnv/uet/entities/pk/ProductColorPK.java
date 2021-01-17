package com.quangnv.uet.entities.pk;

import java.io.Serializable;
import javax.persistence.*;

import com.quangnv.uet.entities.orm.ColorEntity;
import com.quangnv.uet.entities.orm.ProductEntity;

@Embeddable
public class ProductColorPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(targetEntity = ProductEntity.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns(value = {
			@JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false) }, foreignKey = @ForeignKey(name = "FKproduct_co700136"))
	private ProductEntity product;

	public void setProduct(ProductEntity value) {
		this.product = value;
	}

	public ProductEntity getProduct() {
		return this.product;
	}

	@ManyToOne(targetEntity = ColorEntity.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns(value = {
			@JoinColumn(name = "colorId", referencedColumnName = "color_id", nullable = false) }, foreignKey = @ForeignKey(name = "FKproduct_co688965"))
	private ColorEntity color;

}
