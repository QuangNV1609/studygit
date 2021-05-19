package com.quangnv.uet.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quangnv.uet.dto.extend.ProductColorDto;
import com.quangnv.uet.dto.extend.ProductDto;
import com.quangnv.uet.entities.orm.CategoryEntity;
import com.quangnv.uet.entities.orm.ImageEntity;
import com.quangnv.uet.entities.orm.ProductColorEntity;
import com.quangnv.uet.entities.orm.ProductEntity;
import com.quangnv.uet.entities.pk.ProductColorPK;
import com.quangnv.uet.exception.NotEnoughtQuantityException;
import com.quangnv.uet.exception.ResourceNotFoundException;
import com.quangnv.uet.repository.CategoryRepository;
import com.quangnv.uet.repository.ColorRepository;
import com.quangnv.uet.repository.ProductColorRepository;
import com.quangnv.uet.repository.ProductRepository;
import com.quangnv.uet.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductColorRepository productColorRepository;

	@Autowired
	private ColorRepository colorRepository;

	@Autowired
	private ModelMapper modelMapper;

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
		return modelMapper.map(productEntity, ProductDto.class);
	}

	@Override
	public void deleteProduct(String productId) {
		productRepository.deleteById(productId);
		log.info("ProductServiceImpl.deleteListProduct(): delete product with id" + productId);
	}

	@Override
	public void deleteListProduct(String[] ids) {
		for (String id : ids) {
			productRepository.deleteById(id);
			log.info("ProductServiceImpl.deleteListProduct(): delete product with id" + id);
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
				log.info("ProductDto.getProduct(): save product to redis with key" + productId);
			} else {
				throw new ResourceNotFoundException();
			}
		}
		productDto = modelMapper.map(productEntity, ProductDto.class);
		log.info("ProductDto.getProduct(): get product >> " + productDto.toString());
		return productDto;
	}

	@Override
	public List<ProductDto> getPageProduct(Integer page, Integer size) {
		List<ProductDto> productDtos = new ArrayList<>();
		List<ProductEntity> productEntities = productRepository.findAll(PageRequest.of(page, size)).getContent();

		for (ProductEntity productEntity : productEntities) {
			productDtos.add(modelMapper.map(productEntity, ProductDto.class));
		}
		return productDtos;
	}

	@Transactional
	@Override
	public List<ProductColorDto> addColorForProduct(List<ProductColorDto> listProductColorDto) {
		ProductColorEntity productColorEntity = new ProductColorEntity();
		for (ProductColorDto productColorDto : listProductColorDto) {
			productColorEntity = modelMapper.map(productColorDto, ProductColorEntity.class);
			productColorEntity
					.setColor(colorRepository.findById(productColorDto.getProductColorPK().getColorId()).get());
			productColorEntity
					.setProduct(productRepository.findById(productColorDto.getProductColorPK().getProductId()).get());
			productColorRepository.save(productColorEntity);
			log.info("ProductServiceImpl.addColorForProduct() : add colorId "
					+ productColorDto.getProductColorPK().getColorId() + " for productId "
					+ productColorDto.getProductColorPK().getProductId());
		}
		return listProductColorDto;
	}

	@Transactional
	@Override
	public void updateColorForProduct(List<ProductColorDto> listProductColorDto) {
		ProductColorEntity productColorEntity;
		for (ProductColorDto productColorDto : listProductColorDto) {
			if (redisTemplate.hasKey(productColorDto.getProductColorPK().toString())) {
				redisTemplate.delete(productColorDto.getProductColorPK().toString());
			}
			productColorEntity = productColorRepository.findById(productColorDto.getProductColorPK()).get();
			if (productColorEntity.getQuantity() + productColorDto.getQuantity() >= 0) {
				productColorEntity.setQuantity(productColorEntity.getQuantity() + productColorDto.getQuantity());
			} else {
				throw new NotEnoughtQuantityException(productColorEntity.getProduct().getName() + " out of color "
						+ productColorEntity.getColor().getName());
			}
			productColorRepository.save(productColorEntity);
		}
	}

	@Override
	public List<ProductColorDto> findProductColorByProductId(String productId) {
		List<ProductColorDto> listProductColorDto = new ArrayList<ProductColorDto>();
		for (ProductColorEntity productColorEntity : productColorRepository.findByProductId(productId)) {
			listProductColorDto.add(modelMapper.map(productColorEntity, ProductColorDto.class));
		}
		log.info("ProductServiceImpl.findProductColorByProductId(): get productColor for productId >> " + productId);
		return listProductColorDto;
	}

	@Override
	public ProductColorDto findProductColorById(ProductColorPK productColorPK) {
		String key = productColorPK.toString();
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		ProductColorEntity productColorEntity;
		if (redisTemplate.hasKey(key)) {
			productColorEntity = (ProductColorEntity) operations.get(key);
			log.info("PronductServiceImpl.findProductColorById(): get productColorEntity: " + key + " from redis");
			return modelMapper.map(productColorEntity, ProductColorDto.class);
		}
		Optional<ProductColorEntity> optional = Optional
				.ofNullable(productColorRepository.findById(productColorPK).orElse(null));
		if (optional.isPresent()) {
			productColorEntity = optional.get();
			log.info("PronductServiceImpl.findProductColorById(): save productColorEntity: " + key + " to redis");
			log.info("PronductServiceImpl.findProductColorById(): get productColorEntity: " + key + " from mysql");
			operations.set(key, productColorEntity, 60, TimeUnit.SECONDS);
			return modelMapper.map(productColorEntity, ProductColorDto.class);
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public long getTotalPage(int pageSize) {
		long totalProduct = productRepository.count();
		
		if(totalProduct % pageSize == 0) {
			return totalProduct / pageSize;
		}else {
			return totalProduct / pageSize + 1;
		}
	}
}
