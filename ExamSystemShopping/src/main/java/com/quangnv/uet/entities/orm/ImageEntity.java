package com.quangnv.uet.entities.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.quangnv.uet.entities.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Proxy(lazy = false)
@Table(name = "image_entity")
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImageEntity extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "image_id", nullable = false)
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
	private String imageId;
	
	@Column(name = "file_name", nullable = false)
	private String fileName;
	
	@Column(name = "type", nullable = true)
	private Boolean type;

	@Column(name = "file_type", nullable = false)
	private String fileType;
	
	@Lob
	private byte[] fileData;
	
	@ManyToOne(targetEntity = ProductEntity.class, fetch = FetchType.LAZY)
	@JoinTable(name = "product_image", joinColumns = { @JoinColumn(name = "image_id") }, inverseJoinColumns = {
			@JoinColumn(name = "product_id") })
	private ProductEntity product;
	
	@OneToOne(targetEntity = CustomerEntity.class, fetch = FetchType.LAZY)
	@JoinTable(name = "customer_image", joinColumns = { @JoinColumn(name = "image_id") }, inverseJoinColumns = {
			@JoinColumn(name = "customer_id") })
	private CustomerEntity customer;

}
