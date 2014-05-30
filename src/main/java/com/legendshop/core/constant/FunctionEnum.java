package com.legendshop.core.constant;

import com.legendshop.util.constant.StringEnum;

public enum FunctionEnum implements StringEnum {
	FUNCTION_VIEW_ALL_DATA, FUNCTION_F_ADMIN, FUNCTION_F_SYSTEM, FUNCTION_SECURED, FUNCTION_SECUREST;

	private final String value = name();

	public String value() {
		return this.value;
	}

	public static boolean instance(String paramString) {
		FunctionEnum[] arrayOfFunctionEnum1 = values();
		FunctionEnum[] arrayOfFunctionEnum2 = arrayOfFunctionEnum1;
		int i = arrayOfFunctionEnum2.length;
		for (int j = 0; j < i; ++j) {
			FunctionEnum localFunctionEnum = arrayOfFunctionEnum2[j];
			if (localFunctionEnum.name().equals(paramString))
				return true;
		}
		return false;
	}

	public static String getValue(String paramString) {
		FunctionEnum[] arrayOfFunctionEnum1 = values();
		FunctionEnum[] arrayOfFunctionEnum2 = arrayOfFunctionEnum1;
		int i = arrayOfFunctionEnum2.length;
		for (int j = 0; j < i; ++j) {
			FunctionEnum localFunctionEnum = arrayOfFunctionEnum2[j];
			if (localFunctionEnum.name().equals(paramString))
				return localFunctionEnum.value();
		}
		return null;
	}
}