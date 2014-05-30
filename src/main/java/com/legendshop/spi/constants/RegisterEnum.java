package com.legendshop.spi.constants;

import com.legendshop.util.constant.StringEnum;
import java.io.PrintStream;

public enum RegisterEnum implements StringEnum {
	REGISTER_SUCCESS, REGISTER_NO_USER_FOUND, REGISTER_CODE_NOT_MATCH, REGISTER_FAIL;

	private final String value = name();

	public String value() {
		return this.value;
	}

	public static void main(String[] args) {
		System.out.println(REGISTER_SUCCESS.value);
	}
}