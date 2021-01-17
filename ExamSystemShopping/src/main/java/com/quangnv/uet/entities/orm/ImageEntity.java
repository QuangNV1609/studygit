package com.quangnv.uet.entities.orm;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;

import com.quangnv.uet.entities.BaseEntity;
import com.quangnv.uet.entities.autocreateid.StringPrefixedSequenceIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "image")
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

	@Column(name = "image_id", nullable = false, length = 10)
	@Id
	@GeneratedValue(generator = "IMAGE_IMAGE_ID_GENERATOR")
	@GenericGenerator(name = "IMAGE_IMAGE_ID_GENERATOR", strategy = "com.quangnv.uet.entities.autocreateid.StringPrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "IMG"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d") })
	private String imageId;

	@Column(name = "type", nullable = true)
	private Boolean type;

	@Column(name = "path", nullable = true, length = 100)
	private String path;

	@ManyToOne(targetEntity = ProductEntity.class, fetch = FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false) }, foreignKey = @ForeignKey(name = "FKimage111430"))
	private ProductEntity product;

}
