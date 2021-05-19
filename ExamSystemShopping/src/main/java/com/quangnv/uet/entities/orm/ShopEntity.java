package com.quangnv.uet.entities.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.quangnv.uet.entities.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Proxy(lazy = false)
@Table(name = "shop")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShopEntity extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "shop_id", nullable = false, length = 10)
	@Id
	private String shopId;

	@Column(name = "name", nullable = true, length = 10)
	private String name;

	@Column(name = "address", nullable = true, length = 50)
	private String address;

	@Column(name = "email", nullable = true, length = 50)
	private String email;

	@Column(name = "phone", nullable = true, length = 10)
	private String phone;

	@OneToOne(mappedBy = "shop", targetEntity = OrderEntity.class, fetch = FetchType.LAZY)
	private OrderEntity order;

}
