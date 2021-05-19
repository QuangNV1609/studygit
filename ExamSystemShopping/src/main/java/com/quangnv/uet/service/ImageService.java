package com.quangnv.uet.service;

import java.util.List;

import com.quangnv.uet.dto.extend.ImageDto;
import com.quangnv.uet.entities.orm.ImageEntity;

public interface ImageService {

	public ImageDto saveImageForProduct(String productId, ImageEntity imageEntity);
	
	public ImageDto saveImageForCustomer(String customerId, ImageEntity imageEntity);

	public List<ImageEntity> findImageByProductId(String productId);
	
	public ImageEntity getImageById(String imgaeId);
	
	public void deleteImageById(String imageId);
	
}
