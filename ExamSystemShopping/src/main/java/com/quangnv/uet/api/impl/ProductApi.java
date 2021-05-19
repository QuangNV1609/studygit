package com.quangnv.uet.api.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.quangnv.uet.api.AbstractRestHandler;
import com.quangnv.uet.dto.extend.PageProduct;
import com.quangnv.uet.dto.extend.ProductColorDto;
import com.quangnv.uet.dto.extend.ProductDto;
import com.quangnv.uet.entities.pk.ProductColorPK;
import com.quangnv.uet.service.ProductService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/product")
public class ProductApi extends AbstractRestHandler {
	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<PageProduct> getAllProduct(
			@RequestParam(name = "page", defaultValue = DEFAULT_PAGE_NUM) Integer page,
			@RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size, 
			@RequestParam(name = "totalPage", defaultValue = "1") Long totalPage) {
		List<ProductDto> productDtos = productService.getPageProduct(page - 1, size);
		totalPage = productService.getTotalPage(size);
		PageProduct pageProduct = new PageProduct(productDtos, totalPage);
		return new ResponseEntity<PageProduct>(pageProduct, HttpStatus.OK);
	}

	@GetMapping(value = "/{productId}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "productId") String productId) {
		ProductDto productDto = productService.getProduct(productId);
		return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
	}

//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/save")
	public ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto productDto) {
		productDto = productService.saveProduct(productDto, null);
		return new ResponseEntity<ProductDto>(productDto, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(value = "/update/{productId}")
	public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto,
			@PathVariable(name = "productId") String productId) {
		productDto = productService.saveProduct(productDto, productId);
		return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
	}

	@DeleteMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteProduct(@RequestBody String[] ids) {
		productService.deleteListProduct(ids);
	}

	@DeleteMapping(value = "/{productId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable(name = "productId") String productId) {
		productService.deleteProduct(productId);
	}

	@GetMapping(value = "/color")
	public ResponseEntity<List<ProductColorDto>> getProductColorByProductId(
			@RequestParam("productId") String productId) {
		return new ResponseEntity<List<ProductColorDto>>(productService.findProductColorByProductId(productId),
				HttpStatus.OK);
	}

	@GetMapping(value = "/color/{productId}")
	public ResponseEntity<ProductColorDto> getProductColorByProductId(@PathVariable("productId") String productId,
			@RequestParam("colorId") String colorId) {
		ProductColorPK productColorPK = new ProductColorPK(colorId, productId);
		return new ResponseEntity<ProductColorDto>(productService.findProductColorById(productColorPK), HttpStatus.OK);
	}

	@PostMapping(value = "/color")
	public ResponseEntity<List<ProductColorDto>> addColorForProduct(@RequestBody List<ProductColorDto> listColor) {
		List<ProductColorDto> productColorDto = productService.addColorForProduct(listColor);
		return new ResponseEntity<List<ProductColorDto>>(productColorDto, HttpStatus.CREATED);
	}

	@PutMapping(value = "/color")
	@ResponseStatus(code = HttpStatus.OK)
	public void updateColorForProduct(@RequestBody List<ProductColorDto> listColor) {
		productService.updateColorForProduct(listColor);
	}
}
