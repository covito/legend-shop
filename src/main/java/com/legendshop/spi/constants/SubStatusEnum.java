package com.legendshop.spi.constants;

import com.legendshop.util.constant.StringEnum;

public enum SubStatusEnum implements StringEnum {
	ORDER_CAPTURE, ORDER_DEL, PRICE_CHANGE, CREDIT_SCORE, DEBIT_SCORE, ORDER_OVER_TIME, CHANGE_STATUS;

	private final String value = name();

	public String value() {
		return this.value;
	}

	public static boolean instance(String name) {
		SubStatusEnum[] arrayOfSubStatusEnum1;
		SubStatusEnum[] licenseEnums = values();
		int j = (arrayOfSubStatusEnum1 = licenseEnums).length;
		for (int i = 0; i < j; ++i) {
			SubStatusEnum licenseEnum = arrayOfSubStatusEnum1[i];
			if (licenseEnum.name().equals(name))
				return true;
		}

		return false;
	}

	public static String getValue(String name) {
		SubStatusEnum[] arrayOfSubStatusEnum1;
		SubStatusEnum[] licenseEnums = values();
		int j = (arrayOfSubStatusEnum1 = licenseEnums).length;
		for (int i = 0; i < j; ++i) {
			SubStatusEnum licenseEnum = arrayOfSubStatusEnum1[i];
			if (licenseEnum.name().equals(name))
				return licenseEnum.value();
		}

		return null;
	}
}