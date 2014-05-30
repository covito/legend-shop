package com.legendshop.central.license;

import com.legendshop.util.constant.StringEnum;

public enum LicenseEnum implements StringEnum {
	B2C_ALWAYS, C2C_ALWAYS, B2C_YEAR, C2C_YEAR, FREE, LOCAL, EXPIRED, UNKNOWN, UN_AUTH;

	private final String _$2 = name();

	public String value() {
		return this._$2;
	}

	public static boolean instance(String paramString) {
		LicenseEnum[] arrayOfLicenseEnum1 = values();
		LicenseEnum[] arrayOfLicenseEnum2 = arrayOfLicenseEnum1;
		int i = arrayOfLicenseEnum2.length;
		for (int j = 0; j < i; ++j) {
			LicenseEnum localLicenseEnum = arrayOfLicenseEnum2[j];
			if (localLicenseEnum.name().equals(paramString))
				return true;
		}
		return false;
	}

	public static boolean needUpgrade(String paramString) {
		LicenseEnum[] arrayOfLicenseEnum1 = { B2C_YEAR, C2C_YEAR, FREE,
				EXPIRED, UNKNOWN, UN_AUTH };
		LicenseEnum[] arrayOfLicenseEnum2 = arrayOfLicenseEnum1;
		int i = arrayOfLicenseEnum2.length;
		for (int j = 0; j < i; ++j) {
			LicenseEnum localLicenseEnum = arrayOfLicenseEnum2[j];
			if (localLicenseEnum.name().equals(paramString))
				return true;
		}
		return false;
	}

	public static boolean isNormal(String paramString) {
		LicenseEnum[] arrayOfLicenseEnum1 = { B2C_ALWAYS, C2C_ALWAYS, B2C_YEAR,
				C2C_YEAR };
		LicenseEnum[] arrayOfLicenseEnum2 = arrayOfLicenseEnum1;
		int i = arrayOfLicenseEnum2.length;
		for (int j = 0; j < i; ++j) {
			LicenseEnum localLicenseEnum = arrayOfLicenseEnum2[j];
			if (localLicenseEnum.name().equals(paramString))
				return true;
		}
		return false;
	}

	public static String getValue(String paramString) {
		LicenseEnum[] arrayOfLicenseEnum1 = values();
		LicenseEnum[] arrayOfLicenseEnum2 = arrayOfLicenseEnum1;
		int i = arrayOfLicenseEnum2.length;
		for (int j = 0; j < i; ++j) {
			LicenseEnum localLicenseEnum = arrayOfLicenseEnum2[j];
			if (localLicenseEnum.name().equals(paramString))
				return localLicenseEnum.value();
		}
		return null;
	}
}