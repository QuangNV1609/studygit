package com.quangnv.uet.api.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.quangnv.uet.api.AbstractRestHandler;
import com.quangnv.uet.dto.extend.ProductDto;
import com.quangnv.uet.service.ProductService;

@RestController
public class ProductApi extends AbstractRestHandler {
	@Autowired
	private ProductService productService;

	@GetMapping(value = "/product")
	public ResponseEntity<List<ProductDto>> getAllProduct(
			@RequestParam(name = "page", defaultValue = DEFAULT_PAGE_NUM) Integer page,
			@RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
		List<ProductDto> productDtos = productService.getAllProduct(page, size);
		return new ResponseEntity<List<ProductDto>>(productDtos, HttpStatus.OK);
	}

	@GetMapping(value = "/product/{productId}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "productId") String productId) {
		ProductDto productDto = productService.getProduct(productId);
		return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
	}

	@PostMapping(value = "/product")
	public ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto productDto) {
		productDto = productService.saveProduct(productDto, null);
		return new ResponseEntity<ProductDto>(productDto, HttpStatus.CREATED);
	}

	@PutMapping(value = "/product/{productId}")
	public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto,
			@PathVariable(name = "productId") String productId) {
		productDto = productService.saveProduct(productDto, productId);
		return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
	}

	@DeleteMapping(value = "/product")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteProduct(@RequestBody String[] ids) {
		productService.deleteListProduct(ids);
	}

	@DeleteMapping(value = "/product/{productId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable(name = "productId") String productId) {
		productService.deleteProduct(productId);
	}
}
