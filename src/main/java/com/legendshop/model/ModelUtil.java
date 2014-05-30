package com.legendshop.model;

public class ModelUtil {
	public void isNull(Object obj) {
		boolean result = (obj == null) || ("".equals(obj));
		if (!(result))
			throw new ValidationException(obj + " is Not Null");
	}

	public void isNotNull(Object obj, String message) {
		boolean result = (obj == null) || ("".equals(obj));
		if (result)
			throw new ValidationException(message + " is Null");
	}

	public void range(String str, int minLength, int maxLength, String message) {
		boolean result = (str == null) || ("".equals(str));
		if (result)
			throw new ValidationException(message + " is Null");

		if ((str.length() < minLength) || (str.length() > maxLength)) {
			throw new ValidationException(str + "' length is not in range [ "
					+ minLength + ", " + maxLength + " ]");
		}
	}

	public void lt(String str, int maxLength) {
		boolean result = (str == null) || ("".equals(str));
		if (result)
			throw new ValidationException("Object " + str + " is Null");

		if (str.length() > maxLength) {
			throw new ValidationException(str + "' length is max then  "
					+ maxLength);
		}
	}

	public void gt(String str, int minLength) {
		boolean result = (str == null) || ("".equals(str));
		if (result)
			throw new ValidationException("Object " + str + " is Null");

		if (str.length() < minLength) {
			throw new ValidationException(str + "' length is less then  "
					+ minLength);
		}
	}
}