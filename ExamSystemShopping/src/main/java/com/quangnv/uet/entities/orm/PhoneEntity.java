package com.quangnv.uet.entities.orm;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.quangnv.uet.entities.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "phone")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PhoneEntity extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "phone", nullable = false, length = 10)
	@Id
	@GeneratedValue(generator = "PHONE_PHONE_GENERATOR")
	@GenericGenerator(name = "PHONE_PHONE_GENERATOR", strategy = "native")
	private int phone;

	@ManyToOne(targetEntity = ShopEntity.class, fetch = FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "foreign_key", referencedColumnName = "shop_id", nullable = false) }, foreignKey = @ForeignKey(name = "FKphone418421"))
	private ShopEntity foreign_key;

	@ManyToOne(targetEntity = CustomerEntity.class, fetch = FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "foreign_key", referencedColumnName = "customer_id", nullable = false) }, foreignKey = @ForeignKey(name = "FKphone889111"))
	private CustomerEntity foreign_key1;

}
