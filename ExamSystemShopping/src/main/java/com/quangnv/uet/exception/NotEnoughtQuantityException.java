package com.quangnv.uet.exception;

public class NotEnoughtQuantityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotEnoughtQuantityException() {
		super();
	}

	public NotEnoughtQuantityException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotEnoughtQuantityException(String message) {
		super(message);
	}

	public NotEnoughtQuantityException(Throwable cause) {
		super(cause);
	}

}
