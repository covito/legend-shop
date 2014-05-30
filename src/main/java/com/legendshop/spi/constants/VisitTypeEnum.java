package com.legendshop.spi.constants;

import com.legendshop.util.constant.StringEnum;

public enum VisitTypeEnum implements StringEnum {
	INDEX, PROD;

	private final String value = name();

	public String value() {
		return this.value;
	}
}