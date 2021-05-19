package com.quangnv.uet.dto.extend;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageProduct {
	private List<ProductDto> productDtos;
	private long totalPage;
}
