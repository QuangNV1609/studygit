package com.quangnv.uet.domain;

import lombok.Data;

@Data
public class RestErrorInfo {
	private String detail;
	private String message;

	public RestErrorInfo(Exception exception, String detail) {
		super();
		this.detail = detail;
		this.message = exception.getLocalizedMessage();
	}

}
