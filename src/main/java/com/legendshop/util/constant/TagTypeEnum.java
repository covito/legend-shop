package com.legendshop.util.constant;

public enum TagTypeEnum implements StringEnum {
	INDEX;

	private final String _$2="INDEX";

	public String value() {
		return this._$2;
	}

	public static boolean instance(String paramString) {
		TagTypeEnum[] arrayOfTagTypeEnum1 = values();
		TagTypeEnum[] arrayOfTagTypeEnum2 = arrayOfTagTypeEnum1;
		int i = arrayOfTagTypeEnum2.length;
		for (int j = 0; j < i; ++j) {
			TagTypeEnum localTagTypeEnum = arrayOfTagTypeEnum2[j];
			if (localTagTypeEnum.name().equals(paramString))
				return true;
		}
		return false;
	}

	public static String getValue(String paramString) {
		TagTypeEnum[] arrayOfTagTypeEnum1 = values();
		TagTypeEnum[] arrayOfTagTypeEnum2 = arrayOfTagTypeEnum1;
		int i = arrayOfTagTypeEnum2.length;
		for (int j = 0; j < i; ++j) {
			TagTypeEnum localTagTypeEnum = arrayOfTagTypeEnum2[j];
			if (localTagTypeEnum.name().equals(paramString))
				return localTagTypeEnum.value();
		}
		return null;
	}
}