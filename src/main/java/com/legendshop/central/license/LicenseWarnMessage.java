package com.legendshop.central.license;

import com.legendshop.util.constant.StringEnum;

public enum LicenseWarnMessage implements StringEnum {
	EXPIRED_WARNING, EXPIRED_ERROR, SYSTEM_ERROR;

	private final String _$2 = name();

	public String value() {
		return this._$2;
	}

	public static boolean instance(String paramString) {
		LicenseWarnMessage[] arrayOfLicenseWarnMessage1 = values();
		LicenseWarnMessage[] arrayOfLicenseWarnMessage2 = arrayOfLicenseWarnMessage1;
		int i = arrayOfLicenseWarnMessage2.length;
		for (int j = 0; j < i; ++j) {
			LicenseWarnMessage localLicenseWarnMessage = arrayOfLicenseWarnMessage2[j];
			if (localLicenseWarnMessage.name().equals(paramString))
				return true;
		}
		return false;
	}

	public static String getValue(String paramString) {
		LicenseWarnMessage[] arrayOfLicenseWarnMessage1 = values();
		LicenseWarnMessage[] arrayOfLicenseWarnMessage2 = arrayOfLicenseWarnMessage1;
		int i = arrayOfLicenseWarnMessage2.length;
		for (int j = 0; j < i; ++j) {
			LicenseWarnMessage localLicenseWarnMessage = arrayOfLicenseWarnMessage2[j];
			if (localLicenseWarnMessage.name().equals(paramString))
				return localLicenseWarnMessage.value();
		}
		return null;
	}
}