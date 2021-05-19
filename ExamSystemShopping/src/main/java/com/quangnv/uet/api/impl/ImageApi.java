package com.quangnv.uet.api.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.quangnv.uet.api.AbstractRestHandler;
import com.quangnv.uet.dto.extend.ImageDto;
import com.quangnv.uet.entities.orm.ImageEntity;
import com.quangnv.uet.service.FileUploadService;
import com.quangnv.uet.service.ImageService;

import net.lingala.zip4j.exception.ZipException;

@RestController
@RequestMapping(value = "/image")
public class ImageApi extends AbstractRestHandler {
	@Autowired
	private ImageService imageServiceImpl;
	@Autowired
	private FileUploadService fileUploadServiceImpl;

	@PostMapping(value = "/product/upload/db/{productId}")
	public ResponseEntity<List<ImageDto>> addImageForProduct(@PathVariable("productId") String productId,
			@RequestParam("productImages") MultipartFile[] productImages) throws IOException {
		List<ImageDto> imageDtos = new ArrayList<ImageDto>();
		String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").toUriString();
		for (MultipartFile image : productImages) {
			ImageDto imageDto = imageServiceImpl.saveImageForProduct(productId,
					fileUploadServiceImpl.convertMultipartFileToImageEntity(image));
			imageDto.setDownloadUri(downloadUri + imageDto.getImageId());
			imageDtos.add(imageDto);
		}
		return new ResponseEntity<List<ImageDto>>(imageDtos, HttpStatus.CREATED);
	}

	@PostMapping(value = "/product/upload/local/{productId}")
	@ResponseStatus(code = HttpStatus.OK)
	public void uploadLocal(@PathVariable("productId") String productId, @RequestParam("images") MultipartFile[] images)
			throws IOException, ZipException {
		for (MultipartFile image : images) {
			fileUploadServiceImpl.uploadToLocal(productId, image);
		}
	}

	@PostMapping(value = "/customer/upload/db/{customerId}")
	public ResponseEntity<ImageDto> addImageForProduct(@PathVariable("customerId") String customerId,
			@RequestParam("customerImage") MultipartFile customerImage) throws IOException {
		ImageDto imageDto = imageServiceImpl.saveImageForCustomer(customerId,
				fileUploadServiceImpl.convertMultipartFileToImageEntity(customerImage));
		return new ResponseEntity<ImageDto>(imageDto, HttpStatus.CREATED);
	}

	@PostMapping(value = "/customer/upload/local/{customerId}")
	@ResponseStatus(code = HttpStatus.OK)
	public void uploadLocal(@PathVariable("customerId") String customerId,
			@RequestParam("customerImage") MultipartFile customerImage) throws IOException, ZipException {
			fileUploadServiceImpl.uploadToLocal(customerId, customerImage);
	}

//	@GetMapping(value = "/{productId}")
//	public ResponseEntity<List<ImageEntity>> getImageByProductId(@PathVariable("productId") String productId) {
//		List<ImageEntity> imageEntities = imageServiceImpl.findImageByProductId(productId);
//		return new ResponseEntity<List<ImageEntity>>(imageEntities, HttpStatus.OK);
//	}

	@GetMapping(value = "/{imageId}")
	public ImageEntity test(@PathVariable("imageId") String imageId) {
		return imageServiceImpl.getImageById(imageId);
	}

	@GetMapping(value = "/download/{imageId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable("imageId") String imageId) {
		ImageEntity imageEntity = imageServiceImpl.getImageById(imageId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(imageEntity.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; image= " + imageEntity.getFileName())
				.body(new ByteArrayResource(imageEntity.getFileData()));
	}

	@DeleteMapping(value = "/delete/{imageId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteImage(@PathVariable("imageId") String imageId) {
		imageServiceImpl.deleteImageById(imageId);
	}
}
