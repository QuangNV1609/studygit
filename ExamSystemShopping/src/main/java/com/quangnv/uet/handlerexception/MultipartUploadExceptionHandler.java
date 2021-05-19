package com.quangnv.uet.handlerexception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.quangnv.uet.domain.RestErrorInfo;

@RestControllerAdvice
public class MultipartUploadExceptionHandler {

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public RestErrorInfo handlerMaxUploadSizeExceededException(MaxUploadSizeExceededException exception,
			WebRequest request, HttpServletResponse response) {
		return new RestErrorInfo(exception,
				"File Size limit exceeded. Please make sure the file size is well within 128KB");
	}
}
