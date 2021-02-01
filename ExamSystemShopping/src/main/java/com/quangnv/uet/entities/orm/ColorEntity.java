package com.quangnv.uet.entities.orm;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.quangnv.uet.entities.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "color")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ColorEntity extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "color_id", nullable = false, length = 10)
	@Id
	private String colorId;

	@Column(name = "name", nullable = true)
	private String name;

	@OneToMany(mappedBy = "color", targetEntity = ProductColor.class)
	private Set<ProductColor> product_color = new HashSet<ProductColor>();
	
	@OneToMany(mappedBy = "color", targetEntity = CartDetail.class, fetch = FetchType.LAZY)
	private Set<CartDetail> cartDetail = new HashSet<CartDetail>();

}
