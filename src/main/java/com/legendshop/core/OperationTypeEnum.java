package com.legendshop.core;

import com.legendshop.util.constant.StringEnum;

public enum OperationTypeEnum implements StringEnum {
	SAVE, UPDATE, DELETE, TURN_ON, TURN_OFF, UPDATE_PASSWORD, UPDATE_STATUS, UPDATE_PRICE;

	private final String _$2 = name();

	public String value() {
		return this._$2;
	}

	public static boolean instance(String paramString) {
		OperationTypeEnum[] arrayOfOperationTypeEnum1 = values();
		OperationTypeEnum[] arrayOfOperationTypeEnum2 = arrayOfOperationTypeEnum1;
		int i = arrayOfOperationTypeEnum2.length;
		for (int j = 0; j < i; ++j) {
			OperationTypeEnum localOperationTypeEnum = arrayOfOperationTypeEnum2[j];
			if (localOperationTypeEnum.name().equals(paramString))
				return true;
		}
		return false;
	}

	public static String getValue(String paramString) {
		OperationTypeEnum[] arrayOfOperationTypeEnum1 = values();
		OperationTypeEnum[] arrayOfOperationTypeEnum2 = arrayOfOperationTypeEnum1;
		int i = arrayOfOperationTypeEnum2.length;
		for (int j = 0; j < i; ++j) {
			OperationTypeEnum localOperationTypeEnum = arrayOfOperationTypeEnum2[j];
			if (localOperationTypeEnum.name().equals(paramString))
				return localOperationTypeEnum.value();
		}
		return null;
	}
}