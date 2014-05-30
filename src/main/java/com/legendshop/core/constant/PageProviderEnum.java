package com.legendshop.core.constant;

import com.legendshop.util.constant.StringEnum;

public enum PageProviderEnum implements StringEnum {
	SIMPLE_PAGE_PROVIDER, PAGE_PROVIDER;

	private final String value = name();

	public String value() {
		return this.value;
	}
}