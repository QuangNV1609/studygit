package com.quangnv.uet.dto.extend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {
	private String imageId;
	private Boolean type;
	private String fileType;
	private String message;
	private boolean uploadStatus;
	private String downloadUri;
}
