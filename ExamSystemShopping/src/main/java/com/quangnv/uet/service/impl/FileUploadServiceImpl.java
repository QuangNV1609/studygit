package com.quangnv.uet.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.quangnv.uet.entities.orm.ImageEntity;
import com.quangnv.uet.service.FileUploadService;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

@Service
public class FileUploadServiceImpl implements FileUploadService {
	private static final String destinationFile = "C:/Users/ADMIN/Desktop/Images/";

	@Transactional
	@Override
	public void unzipFiles(MultipartFile image) throws IOException, ZipException {
		File zip = File.createTempFile(UUID.randomUUID().toString(), "temp");
		FileOutputStream outputStream = new FileOutputStream(zip);
		IOUtils.copy(image.getInputStream(), outputStream);
		outputStream.close();
		ZipFile zipFile = new ZipFile(zip);
		zipFile.extractAll(destinationFile);
		zip.delete();

	}

	@Override
	public ImageEntity convertMultipartFileToImageEntity(MultipartFile image) throws IOException {
		ImageEntity imageEntity = new ImageEntity();
		imageEntity.setFileType(image.getContentType());
		imageEntity.setFileData(image.getBytes());
		imageEntity.setFileName(image.getOriginalFilename());
		return imageEntity;
	}

	@Override
	public void uploadToLocal(String productId, MultipartFile image) {
		try {
			byte[] data = image.getBytes();
			File file = new File(destinationFile + productId);
			if (!file.exists()) {
				file.mkdirs();
			}
			Path path = Paths.get(destinationFile + productId + "/" + image.getOriginalFilename());
			Files.write(path, data);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
