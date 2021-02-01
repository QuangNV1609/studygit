package com.quangnv.uet.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.quangnv.uet.dto.extend.ProductDto;
import com.quangnv.uet.entities.orm.CategoryEntity;
import com.quangnv.uet.entities.orm.ProductEntity;
import com.quangnv.uet.exception.ResourceNotFoundException;
import com.quangnv.uet.repository.CategoryRepository;
import com.quangnv.uet.repository.ProductRepository;
import com.quangnv.uet.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public ProductDto saveProduct(ProductDto productDto, String productId) {
		String categoryName = productDto.getCategoryName();
		Optional<CategoryEntity> optional = Optional
				.ofNullable(categoryRepository.findOneByName(categoryName).orElse(null));
		CategoryEntity categoryEntity;
		if (optional.isPresent()) {
			categoryEntity = optional.get();
		} else {
			categoryEntity = categoryRepository
					.save(CategoryEntity.builder().name(productDto.getCategoryName()).build());
		}
		ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);
		productEntity.setProductId(productId);
		productEntity.setCategory(categoryEntity);
		productRepository.save(productEntity);
		if (redisTemplate.hasKey(productEntity.getProductId())) {
			redisTemplate.delete(productEntity.getProductId());
			log.info("ProductServiceImpl.saveProduct() : update product");
		} else {
			log.info("ProductServiceImpl.saveProduct() : save product");
		}
		return productDto;
	}

	@Override
	public void deleteProduct(String productId) {
		productRepository.deleteById(productId);
	}

	@Override
	public void deleteListProduct(String[] ids) {
		for (String id : ids) {
			productRepository.deleteById(id);
		}
	}

	@Override
	public ProductDto getProduct(String productId) {
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		ProductDto productDto;
		ProductEntity productEntity;
		if (redisTemplate.hasKey(productId)) {
			productEntity = (ProductEntity) operations.get(productId);
		} else {
			Optional<ProductEntity> optional = Optional.ofNullable(productRepository.findById(productId).orElse(null));
			if (optional.isPresent()) {
				productEntity = optional.get();
				operations.set(productId, productEntity, 60, TimeUnit.SECONDS);
				log.info("ProductDto.getProduct(): save product to redis with key", productId);
			} else {
				throw new ResourceNotFoundException();
			}
		}
		productDto = modelMapper.map(productEntity, ProductDto.class);
		log.info("ProductDto.getProduct(): get product >> " + productDto.toString());
		return productDto;
	}

	@Override
	public List<ProductDto> getAllProduct(Integer page, Integer size) {
		List<ProductDto> productDtos = new ArrayList<ProductDto>();
		for (ProductEntity productEntity : productRepository.findAll(PageRequest.of(page, size)).getContent()) {
			productDtos.add(modelMapper.map(productEntity, ProductDto.class));
		}
		return productDtos;
	}
}
