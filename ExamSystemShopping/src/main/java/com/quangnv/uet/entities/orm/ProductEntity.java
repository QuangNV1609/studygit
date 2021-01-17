package com.quangnv.uet.entities.orm;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "product")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductEntity extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "product_id", nullable = false, length = 10)
	@Id
	@GeneratedValue(generator = "PRODUCT_PRODUCT_ID_GENERATOR")
	@GenericGenerator(name = "PRODUCT_PRODUCT_ID_GENERATOR", strategy = "com.quangnv.uet.entities.autocreateid.StringPrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PRD"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d") })
	private String productId;

	@Column(name = "name", nullable = true, length = 255)
	private String name;

	@Column(name = "ram", nullable = true, length = 255)
	private String ram;

	@Column(name = "memory", nullable = true, length = 255)
	private String memory;

	@Column(name = "price", nullable = true, length = 10)
	private Float price;

	@Column(name = "decription", nullable = true, length = 10)
	private Integer decription;

	@ManyToOne(targetEntity = CategoryEntity.class, fetch = FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false) }, foreignKey = @ForeignKey(name = "FKproduct370273"))
	private CategoryEntity category;

	@OneToMany(mappedBy = "product", targetEntity = ProductColor.class)
	private Set<ProductColor> product_color = new HashSet<ProductColor>();

	@OneToMany(mappedBy = "product", targetEntity = ImageEntity.class)
	private Set<ImageEntity> image = new HashSet<ImageEntity>();

	@OneToMany(mappedBy = "product", targetEntity = CartDetail.class)
	private Set<CartDetail> cart_detail = new HashSet<CartDetail>();

}
