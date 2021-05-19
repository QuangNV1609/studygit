package com.quangnv.uet.dto.extend;

import javax.validation.constraints.NotNull;

import com.quangnv.uet.entities.pk.ProductColorPK;

import lombok.Data;

@Data
public class ProductColorDto {
	@NotNull
	private ProductColorPK productColorPK;

	@NotNull
	private Integer quantity;

}
