package com.quangnv.uet.dto.extend;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.quangnv.uet.dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto extends BaseDto {
	@NotBlank
	private String name;

	@NotBlank
	private String ram;

	@NotBlank
	private String memory;

	@NotNull
	private Float price;

	private String decription;

	private String categoryName;

	private Set<ProductColorDto> product_colors;

}
