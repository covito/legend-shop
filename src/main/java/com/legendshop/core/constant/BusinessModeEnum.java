package com.legendshop.core.constant;

import com.legendshop.util.constant.StringEnum;

public enum BusinessModeEnum implements StringEnum {
	B2C, C2C, B2B2C;

	private String _$2;

	public String value() {
		return this._$2;
	}

	public static boolean instance(String paramString) {
		BusinessModeEnum[] arrayOfBusinessModeEnum1 = values();
		BusinessModeEnum[] arrayOfBusinessModeEnum2 = arrayOfBusinessModeEnum1;
		int i = arrayOfBusinessModeEnum2.length;
		for (int j = 0; j < i; ++j) {
			BusinessModeEnum localBusinessModeEnum = arrayOfBusinessModeEnum2[j];
			if (localBusinessModeEnum.name().equals(paramString))
				return true;
		}
		return false;
	}

	public static String getValue(String paramString) {
		BusinessModeEnum[] arrayOfBusinessModeEnum1 = values();
		BusinessModeEnum[] arrayOfBusinessModeEnum2 = arrayOfBusinessModeEnum1;
		int i = arrayOfBusinessModeEnum2.length;
		for (int j = 0; j < i; ++j) {
			BusinessModeEnum localBusinessModeEnum = arrayOfBusinessModeEnum2[j];
			if (localBusinessModeEnum.name().equals(paramString))
				return localBusinessModeEnum.value();
		}
		return null;
	}
}