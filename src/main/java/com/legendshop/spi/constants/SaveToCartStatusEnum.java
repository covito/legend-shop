package com.legendshop.spi.constants;

import com.legendshop.util.constant.StringEnum;

public enum SaveToCartStatusEnum implements StringEnum {
	OK, ERR, OWNER, LESS, MAX;

	private final String value = name();

	public String value() {
		return this.value;
	}
}