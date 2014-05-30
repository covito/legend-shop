package com.legendshop.util;

import java.util.Map;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.util.ReflectionUtils;

public class BeanUtils {
	private static BeanUtilsBean _$1 = BeanUtilsBean.getInstance();

	public static Object cloneBean(Object paramObject) {
		try {
			return _$1.cloneBean(paramObject);
		} catch (Exception localException) {
			_$1(localException);
		}
		return null;
	}

	public static <T> T copyProperties(Class<T> paramClass, Object paramObject) {
		T localObject = null;
		try {
			localObject = paramClass.newInstance();
			copyProperties(localObject, paramObject);
			return localObject;
		} catch (Exception localException) {
			_$1(localException);
		}
		return null;
	}

	public static void copyProperties(Object paramObject1, Object paramObject2) {
		try {
			_$1.copyProperties(paramObject1, paramObject2);
		} catch (Exception localException) {
			_$1(localException);
		}
	}

	public static void copyProperty(Object paramObject1, String paramString,
			Object paramObject2) {
		try {
			_$1.copyProperty(paramObject1, paramString, paramObject2);
		} catch (Exception localException) {
			_$1(localException);
		}
	}

	public static Map describe(Object paramObject) {
		try {
			return _$1.describe(paramObject);
		} catch (Exception localException) {
			_$1(localException);
		}
		return null;
	}

	public static String[] getArrayProperty(Object paramObject,
			String paramString) {
		try {
			return _$1.getArrayProperty(paramObject, paramString);
		} catch (Exception localException) {
			_$1(localException);
		}
		return null;
	}

	public static ConvertUtilsBean getConvertUtils() {
		return _$1.getConvertUtils();
	}

	public static String getIndexedProperty(Object paramObject,
			String paramString, int paramInt) {
		try {
			return _$1.getIndexedProperty(paramObject, paramString, paramInt);
		} catch (Exception localException) {
			_$1(localException);
		}
		return null;
	}

	public static String getIndexedProperty(Object paramObject,
			String paramString) {
		try {
			return _$1.getIndexedProperty(paramObject, paramString);
		} catch (Exception localException) {
			_$1(localException);
		}
		return null;
	}

	public static String getMappedProperty(Object paramObject,
			String paramString1, String paramString2) {
		try {
			return _$1.getMappedProperty(paramObject, paramString1,
					paramString2);
		} catch (Exception localException) {
			_$1(localException);
		}
		return null;
	}

	public static String getMappedProperty(Object paramObject,
			String paramString) {
		try {
			return _$1.getMappedProperty(paramObject, paramString);
		} catch (Exception localException) {
			_$1(localException);
		}
		return null;
	}

	public static String getNestedProperty(Object paramObject,
			String paramString) {
		try {
			return _$1.getNestedProperty(paramObject, paramString);
		} catch (Exception localException) {
			_$1(localException);
		}
		return null;
	}

	public static String getProperty(Object paramObject, String paramString) {
		try {
			return _$1.getProperty(paramObject, paramString);
		} catch (Exception localException) {
			_$1(localException);
		}
		return null;
	}

	public static PropertyUtilsBean getPropertyUtils() {
		try {
			return _$1.getPropertyUtils();
		} catch (Exception localException) {
			_$1(localException);
		}
		return null;
	}

	public static String getSimpleProperty(Object paramObject,
			String paramString) {
		try {
			return _$1.getSimpleProperty(paramObject, paramString);
		} catch (Exception localException) {
			_$1(localException);
		}
		return null;
	}

	public static void populate(Object paramObject, Map paramMap) {
		try {
			_$1.populate(paramObject, paramMap);
		} catch (Exception localException) {
			_$1(localException);
		}
	}

	public static void setProperty(Object paramObject1, String paramString,
			Object paramObject2) {
		try {
			_$1.setProperty(paramObject1, paramString, paramObject2);
		} catch (Exception localException) {
			_$1(localException);
		}
	}

	private static void _$1(Exception paramException) {
		ReflectionUtils.handleReflectionException(paramException);
	}
}