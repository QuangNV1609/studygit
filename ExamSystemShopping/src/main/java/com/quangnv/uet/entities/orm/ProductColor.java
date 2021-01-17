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
import com.quangnv.uet.entities.pk.ProductColorPK;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "product_color")
@IdClass(ProductColorPK.class)
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductColor extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKeyJoinColumn
	@ManyToOne(targetEntity = ProductEntity.class, fetch = FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false) }, foreignKey = @ForeignKey(name = "FKproduct_co700136"))
	private ProductEntity product;

	@Column(name = "product_id", nullable = false, insertable = false, updatable = false)
	@Id
	@GeneratedValue(generator = "PRODUCT_COLOR_PRODUCTID_GENERATOR")
	private String productId;

	@PrimaryKeyJoinColumn
	@ManyToOne(targetEntity = ColorEntity.class, fetch = FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "colorId", referencedColumnName = "color_id", nullable = false) }, foreignKey = @ForeignKey(name = "FKproduct_co688965"))
	private ColorEntity color;

	@Column(name = "colorId", nullable = false, insertable = false, updatable = false)
	@Id
	@GeneratedValue(generator = "PRODUCT_COLOR_COLORID_GENERATOR")
	@GenericGenerator(name = "PRODUCT_COLOR_COLORID_GENERATOR", strategy = "foreign", parameters = @org.hibernate.annotations.Parameter(name = "property", value = "color"))
	private String colorId;

	@Column(name = "quantity", nullable = true, length = 5)
	private Integer quantity;

}
