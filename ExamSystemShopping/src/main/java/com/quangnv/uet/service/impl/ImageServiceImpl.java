package com.quangnv.uet.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.quangnv.uet.dto.extend.ImageDto;
import com.quangnv.uet.entities.orm.CustomerEntity;
import com.quangnv.uet.entities.orm.ImageEntity;
import com.quangnv.uet.entities.orm.ProductEntity;
import com.quangnv.uet.exception.ResourceNotFoundException;
import com.quangnv.uet.repository.CustomerRepository;
import com.quangnv.uet.repository.ImageRepository;
import com.quangnv.uet.repository.ProductRepository;
import com.quangnv.uet.service.ImageService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RedisTemplate<String, Object> redistemplate;

	@Override
	public ImageDto saveImageForProduct(String productId, ImageEntity imageEntity) {
		imageEntity.setProduct(productRepository.findById(productId).get());
		imageRepository.save(imageEntity);
		ImageDto imageDto = modelMapper.map(imageEntity, ImageDto.class);
		imageDto.setMessage("File uploaded sucessfuly!");
		imageDto.setUploadStatus(true);
		log.info("ImageServiceImpl.saveImageForProduct(): save image: " + imageDto.getImageId() + " for " + productId);
		return imageDto;
	}

	@Override
	public List<ImageEntity> findImageByProductId(String productId) {
		ProductEntity productEntity = productRepository.findById(productId).get();
		List<ImageEntity> imageEntities = imageRepository.findAllByProduct(productEntity);
		return imageEntities;
	}

	@Override
	public ImageEntity getImageById(String imageId) {
		ValueOperations<String, Object> operations = redistemplate.opsForValue();
		if (redistemplate.hasKey(imageId)) {
			return (ImageEntity) operations.get(imageId);
		}
		Optional<ImageEntity> optional = Optional.ofNullable(imageRepository.findById(imageId).orElse(null));
		if (optional.isPresent()) {
			ImageEntity imageEntity = optional.get();
			operations.set(imageId, imageEntity, 60, TimeUnit.SECONDS);
			log.info("ImageServiceImpl.getImageById(): save image >> " + imageId + " to redis");
			return imageEntity;
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public void deleteImageById(String imageId) {
		imageRepository.deleteById(imageId);
		log.info("ImageServiceImpl.deleteImageById(): delete + " + imageId);
	}

	@Override
	public ImageDto saveImageForCustomer(String customerId, ImageEntity imageEntity) {
		CustomerEntity customerEntity = customerRepository.findById(customerId).get();
		imageEntity.setCustomer(customerEntity);
		imageRepository.save(imageEntity);
		ImageDto imageDto = modelMapper.map(imageEntity, ImageDto.class);
		imageDto.setMessage("File uploaded sucessfuly!");
		imageDto.setUploadStatus(true);
		log.info("ImageServiceImpl.saveImageForCustomer(): save image: " + imageDto.getImageId() + " for " + customerId);
		return imageDto;
	}

}
