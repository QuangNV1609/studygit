package com.quangnv.uet.entities.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.quangnv.uet.entities.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "order")
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderEntity extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "id", nullable = false, length = 255)
	@Id
	private String id;

	@OneToOne(optional = false, targetEntity = CartEntity.class, fetch = FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "cart_id", referencedColumnName = "cart_id", nullable = false) }, foreignKey = @ForeignKey(name = "FKorder875043"))
	private CartEntity cart;

	@OneToOne(optional = false, targetEntity = ShopEntity.class, fetch = FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "shop_id", referencedColumnName = "shop_id", nullable = false) }, foreignKey = @ForeignKey(name = "FKorder267559"))
	private ShopEntity shop;

	@Column(name = "phone", nullable = true, length = 10)
	private Integer phone;

	@Column(name = "name", nullable = true, length = 30)
	private String name;

}
