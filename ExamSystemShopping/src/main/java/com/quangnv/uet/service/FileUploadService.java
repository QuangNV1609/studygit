package com.quangnv.uet.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.quangnv.uet.entities.orm.ImageEntity;

import net.lingala.zip4j.exception.ZipException;

public interface FileUploadService {
	public void unzipFiles(MultipartFile image) throws IOException, ZipException;

	public ImageEntity convertMultipartFileToImageEntity(MultipartFile image) throws IOException;

	public void uploadToLocal(String productId, MultipartFile image);

}
