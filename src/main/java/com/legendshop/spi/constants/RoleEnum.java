package com.legendshop.spi.constants;

import com.legendshop.util.constant.StringEnum;

public enum RoleEnum implements StringEnum {
	ROLE_SUPERVISOR, ROLE_ADMIN, ROLE_USER;

	private final String value = name();

	public String value() {
		return this.value;
	}

	public static boolean instance(String name) {
		RoleEnum[] arrayOfRoleEnum1;
		RoleEnum[] licenseEnums = values();
		int j = (arrayOfRoleEnum1 = licenseEnums).length;
		for (int i = 0; i < j; ++i) {
			RoleEnum licenseEnum = arrayOfRoleEnum1[i];
			if (licenseEnum.name().equals(name))
				return true;
		}

		return false;
	}

	public static String getValue(String name) {
		RoleEnum[] arrayOfRoleEnum1;
		RoleEnum[] licenseEnums = values();
		int j = (arrayOfRoleEnum1 = licenseEnums).length;
		for (int i = 0; i < j; ++i) {
			RoleEnum licenseEnum = arrayOfRoleEnum1[i];
			if (licenseEnum.name().equals(name))
				return licenseEnum.value();
		}

		return null;
	}
}