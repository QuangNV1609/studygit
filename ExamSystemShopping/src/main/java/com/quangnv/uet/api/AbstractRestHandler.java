package com.quangnv.uet.api;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.quangnv.uet.domain.RestErrorInfo;
import com.quangnv.uet.exception.NotEnoughtQuantityException;
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

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public RestErrorInfo handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception,
			WebRequest request, HttpServletResponse httpServletResponse) {
		log.info("MethodArgumentNotValidException handler: " + exception.getMessage());
		return new RestErrorInfo(exception, "Please, Check your data again!");
	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public RestErrorInfo handlerEmptyResultDataAccessException(EmptyResultDataAccessException exception,
			WebRequest request, HttpServletResponse httpServletResponse) {
		return new RestErrorInfo(exception, "Id don't exists!");
	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NoSuchElementException.class)
	public RestErrorInfo handlerNoSuchElementException(NoSuchElementException exception, WebRequest request,
			HttpServletResponse httpServletResponse) {
		return new RestErrorInfo(exception, "Id don't exists!");
	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NotEnoughtQuantityException.class)
	public RestErrorInfo handlerNoSuchElementException(NotEnoughtQuantityException exception, WebRequest request,
			HttpServletResponse httpServletResponse) {
		log.info("sản phẩm hết hàng");
		return new RestErrorInfo(exception, "Out of color!");
	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(IllegalStateException.class)
	public RestErrorInfo handlerIllegaStateException(IllegalStateException exception, WebRequest request,
			HttpServletResponse httpServletResponse) {
		return new RestErrorInfo(exception, "save image failure!");
	}
	
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UsernameNotFoundException.class)
	public RestErrorInfo handlerUserNotFoundException(AuthenticationException exception, WebRequest request, 
			HttpServletResponse response) {
		log.info("user not found!");
		return new RestErrorInfo(exception, "user not found");
	}

}
