package com.quangnv.uet.service;

import java.util.List;

import com.quangnv.uet.dto.extend.ProductColorDto;
import com.quangnv.uet.dto.extend.ProductDto;
import com.quangnv.uet.entities.pk.ProductColorPK;

public interface ProductService {
	public ProductDto saveProduct(ProductDto productDto, String productId);

	public void deleteProduct(String productId);

	public void deleteListProduct(String[] ids);

	public ProductDto getProduct(String productId);

	public List<ProductDto> getPageProduct(Integer page, Integer size);
	
	public long getTotalPage(int pageSize);

	public List<ProductColorDto> addColorForProduct(List<ProductColorDto> listProductColorDto);

	public List<ProductColorDto> findProductColorByProductId(String productId);

	public ProductColorDto findProductColorById(ProductColorPK productColorPK);

	public void updateColorForProduct(List<ProductColorDto> listProductColorDto);
}
