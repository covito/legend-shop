package com.legendshop.model;

public class ValidationException extends RuntimeException {
	private static final long serialVersionUID = 5850511968628572771L;
	private int code;

	public ValidationException(String message) {
		super(message);
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}