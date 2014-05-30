package com.legendshop.spi.constants;

import com.legendshop.util.constant.StringEnum;

public enum SysParamGroupEnum implements StringEnum {
	SYS, SHOP, LOG, MAIL;

	private final String value = name();

	public String value() {
		return this.value;
	}

	public static boolean instance(String name) {
		SysParamGroupEnum[] arrayOfSysParamGroupEnum1;
		SysParamGroupEnum[] licenseEnums = values();
		int j = (arrayOfSysParamGroupEnum1 = licenseEnums).length;
		for (int i = 0; i < j; ++i) {
			SysParamGroupEnum licenseEnum = arrayOfSysParamGroupEnum1[i];
			if (licenseEnum.name().equals(name))
				return true;
		}

		return false;
	}

	public static String getValue(String name) {
		SysParamGroupEnum[] arrayOfSysParamGroupEnum1;
		SysParamGroupEnum[] licenseEnums = values();
		int j = (arrayOfSysParamGroupEnum1 = licenseEnums).length;
		for (int i = 0; i < j; ++i) {
			SysParamGroupEnum licenseEnum = arrayOfSysParamGroupEnum1[i];
			if (licenseEnum.name().equals(name))
				return licenseEnum.value();
		}

		return null;
	}

	public static String getName(String value) {
		SysParamGroupEnum[] arrayOfSysParamGroupEnum1;
		SysParamGroupEnum[] licenseEnums = values();
		int j = (arrayOfSysParamGroupEnum1 = licenseEnums).length;
		for (int i = 0; i < j; ++i) {
			SysParamGroupEnum sysParamGroup = arrayOfSysParamGroupEnum1[i];
			if (sysParamGroup.value().equals(value))
				return sysParamGroup.name();
		}

		return null;
	}
}