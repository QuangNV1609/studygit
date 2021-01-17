package com.quangnv.uet.entities.orm;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.GenericGenerator;

import com.quangnv.uet.entities.BaseEntity;
import com.quangnv.uet.entities.autocreateid.StringPrefixedSequenceIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Proxy(lazy = false)
@Table(name = "category")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryEntity extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "category_id", nullable = false, length = 10)
	@Id
	@GeneratedValue(generator = "CATEGORY_CATEGORY_ID_GENERATOR")
	@GenericGenerator(name = "CATEGORY_CATEGORY_ID_GENERATOR", strategy = "com.quangnv.uet.entities.autocreateid.StringPrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "CGR"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d") })
	private String categoryId;

	@Column(name = "name", nullable = true, length = 10)
	private String name;

	@OneToMany(mappedBy = "category", targetEntity = ProductEntity.class)
	private Set<ProductEntity> product = new HashSet<ProductEntity>();

}
