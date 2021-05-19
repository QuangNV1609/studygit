package com.quangnv.uet.entities.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductColorPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String colorId;

	private String productId;
//	@ManyToOne(targetEntity = ProductEntity.class, fetch = FetchType.LAZY)
//	@Cascade({ org.hibernate.annotations.CascadeType.LOCK })
//	@JoinColumns(value = {
//			@JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false) }, 
//	foreignKey = @ForeignKey(name = "FKproduct_co700136"))
//	private ProductEntity product;
//
//	@ManyToOne(targetEntity = ColorEntity.class, fetch = FetchType.LAZY)
//	@Cascade({ org.hibernate.annotations.CascadeType.LOCK })
//	@JoinColumns(value = {
//			@JoinColumn(name = "color_id", referencedColumnName = "color_id", nullable = false) }, 
//	foreignKey = @ForeignKey(name = "FKproduct_co688965"))
//	private ColorEntity color;

}
