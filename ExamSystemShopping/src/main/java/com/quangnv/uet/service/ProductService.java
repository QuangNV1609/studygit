package com.quangnv.uet.service;

import java.util.List;

import com.quangnv.uet.dto.extend.ProductDto;

public interface ProductService {
	public ProductDto saveProduct(ProductDto productDto, String productId);

	public void deleteProduct(String productId);

	public void deleteListProduct(String[] ids);

	public ProductDto getProduct(String productId);
	
	public List<ProductDto> getAllProduct(Integer page, Integer size);
}
