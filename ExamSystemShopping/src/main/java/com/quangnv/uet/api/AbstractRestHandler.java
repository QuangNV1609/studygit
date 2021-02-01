package com.quangnv.uet.api;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.quangnv.uet.domain.RestErrorInfo;
import com.quangnv.uet.exception.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AbstractRestHandler implements ApplicationEventPublisherAware {
	protected ApplicationEventPublisher applicationEventPublisher;

	protected static final String DEFAULT_PAGE_SIZE = "3";
	protected static final String DEFAULT_PAGE_NUM = "0";

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	public RestErrorInfo handlerResourceNotFoundException(ResourceNotFoundException exception, WebRequest request,
			HttpServletResponse httpServletResponse) {
		log.info("ResourceNotFoundException handler: " + exception.getMessage());
		return new RestErrorInfo(exception, "Sorry, I could not find it!");
	}

	public RestErrorInfo handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception,
			WebRequest request, HttpServletResponse httpServletResponse) {
		log.info("MethodArgumentNotValidException handler: " + exception.getMessage());
		return new RestErrorInfo(exception, "Please, Check your data again!");
	}

}
