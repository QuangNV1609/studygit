package com.quangnv.uet.entities.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.quangnv.uet.entities.BaseEntity;
import com.quangnv.uet.entities.pk.ProductColorPK;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Proxy(lazy = false)
@Table(name = "product_color_entity")
//@IdClass(ProductColorPK.class)
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductColorEntity extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductColorPK productColorPK;
	
	@ManyToOne(targetEntity = ProductEntity.class, fetch = FetchType.LAZY)
	@MapsId("productId")
	@JoinColumns(value = {
			@JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false) }, 
	foreignKey = @ForeignKey(name = "FKproduct_co700136"))
	private ProductEntity product;
	
//	@Column(name = "product_id", nullable = false, insertable = false, updatable = false)
//	@Id
//	@GeneratedValue(generator = "PRODUCT_COLOR_PRODUCTID_GENERATOR")
//	private String productId;
//
	@ManyToOne(targetEntity = ColorEntity.class, fetch = FetchType.LAZY)
	@MapsId("colorId")
	@JoinColumns(value = {
			@JoinColumn(name = "color_id", referencedColumnName = "color_id", nullable = false) }, 
	foreignKey = @ForeignKey(name = "FKproduct_co688965"))
	private ColorEntity color;
//
//	@Column(name = "color_id", nullable = false, insertable = false, updatable = false)
//	@Id
//	@GeneratedValue(generator = "PRODUCT_COLOR_COLORID_GENERATOR")
//	@GenericGenerator(name = "PRODUCT_COLOR_COLORID_GENERATOR", strategy = "foreign")
//	private String colorId;

	@Column(name = "quantity", nullable = true)
	private Integer quantity;

}
