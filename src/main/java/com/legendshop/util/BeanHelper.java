package com.legendshop.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

public final class BeanHelper extends BeanUtils {
	private static final Log logger = LogFactory.getLog(BeanHelper.class);
	private static final DateFormat _$3 = new SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat _$2 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static final NumberFormat _$1 = NumberFormat.getInstance();

	public static Date parseDate(String paramString) {
		if ((paramString == null) || (paramString.trim().equals("")))
			return null;
		Date localDate = null;
		try {
			localDate = _$3.parse(paramString);
		} catch (Exception localException) {
			throw new RuntimeException("错误的日期格式 : " + paramString);
		}
		return localDate;
	}

	public static String formatDate(Date paramDate) {
		if (paramDate == null)
			return null;
		String str = null;
		try {
			str = _$3.format(paramDate);
		} catch (Exception localException) {
			throw new RuntimeException("无法解析的日期 : " + paramDate);
		}
		return str;
	}

	public static void copyProperties(Object source, Object target,
			boolean paramBoolean) {
		
		try {
			Set<String> tarSet = BeanUtils.describe(target).keySet();
			Set<String> sourceSet = BeanUtils.describe(source).keySet();
			for(String t:tarSet){
				if ((t.equals("class"))
						|| (!(sourceSet.contains(t)))){
					continue;
				}
				
				Class tpt = PropertyUtils.getPropertyDescriptor(
						target, t).getPropertyType();
				Class tst = PropertyUtils.getPropertyDescriptor(
						source, t).getPropertyType();
				if (tpt != tst){
					logger.warn("源数据类型[" + tst.getName() + "]和目标数据类型["
							+ tst.getName() + "]不匹配, 忽略此次赋值");
					continue;
				}
			
				Object v=PropertyUtils.getNestedProperty(target, t);
				
				PropertyUtils.setNestedProperty(target, t,v);
				
			}
			
		} catch (Exception e) {
			throw new RuntimeException("复制属性出错 ...", e);
		}
	}

	public static void copyProperties(Object paramObject1, Object paramObject2)
			throws IllegalAccessException, InvocationTargetException {
		copyProperties(paramObject1, paramObject2, true);
	}

	public static Integer getInt(String paramString, Integer paramInteger) {
		if ((paramString == null) || (paramString.trim().equals("")))
			return paramInteger;
		Integer localInteger = null;
		try {
			localInteger = Integer.valueOf(paramString);
		} catch (Exception localException) {
			localInteger = paramInteger;
		}
		return localInteger;
	}

	public static Long getLong(String paramString, Long paramLong) {
		if ((paramString == null) || (paramString.trim().equals("")))
			return paramLong;
		Long localLong = null;
		try {
			localLong = Long.valueOf(paramString);
		} catch (Exception localException) {
			localLong = paramLong;
		}
		return localLong;
	}

	public static Float getFloat(String paramString, Float paramFloat) {
		if ((paramString == null) || (paramString.trim().equals("")))
			return paramFloat;
		Float localFloat = null;
		try {
			localFloat = Float.valueOf(paramString);
		} catch (Exception localException) {
			localFloat = paramFloat;
		}
		return localFloat;
	}

	public static Double getDouble(String paramString, Double paramDouble) {
		if ((paramString == null) || (paramString.trim().equals("")))
			return paramDouble;
		Double localDouble = null;
		try {
			localDouble = Double.valueOf(paramString);
		} catch (Exception localException) {
			localDouble = paramDouble;
		}
		return localDouble;
	}

	public static Integer getInt(String paramString) {
		return getInt(paramString, Integer.valueOf(0));
	}

	public static Long getLong(String paramString) {
		return getLong(paramString, Long.valueOf(5439837094136512512L));
	}

	public static Float getFloat(String paramString) {
		return getFloat(paramString, Float.valueOf(0F));
	}

	public static Double getDouble(String paramString) {
		return getDouble(paramString, Double.valueOf(0D));
	}

	public static String describeAsString(Object paramObject) {
		return ToStringBuilder.reflectionToString(paramObject,
				ToStringStyle.MULTI_LINE_STYLE);
	}

	public static String getNotNullString(Object paramObject) {
		return ((paramObject == null) ? "" : String.valueOf(paramObject));
	}

	public static Object getBeanProperty(Object paramObject, String paramString)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return PropertyUtils.getProperty(paramObject, paramString);
	}

	public static Double getDoubleByObject(Object paramObject) {
		if (paramObject == null)
			return Double.valueOf(0D);
		Double localDouble = null;
		try {
			localDouble = Double.valueOf(String.valueOf(paramObject));
		} catch (Exception localException) {
			localDouble = Double.valueOf(0D);
		}
		return localDouble;
	}

	public static BigDecimal getBigDecimalByObject(Object paramObject) {
		if (paramObject == null)
			return BigDecimal.valueOf(0D);
		BigDecimal localBigDecimal = null;
		try {
			localBigDecimal = new BigDecimal(String.valueOf(paramObject));
		} catch (Exception localException) {
			localBigDecimal = BigDecimal.valueOf(0D);
		}
		return localBigDecimal;
	}

	public static Map Object2Map(Object paramObject) throws Exception {
		return describe(paramObject);
	}

	public static void Map2Object(Map paramMap, Object paramObject)
			throws Exception {
		populate(paramObject, paramMap);
	}

	public static String formatNumber(Object paramObject) {
		if ((paramObject != null) || (paramObject instanceof Number))
			return _$1.format(paramObject);
		return "";
	}

	public static Object converToString(Object paramObject) {
		if (paramObject == null)
			return null;
		if (paramObject instanceof String)
			return paramObject;
		if (paramObject instanceof Date)
			return formatDate((Date) paramObject);
		if (paramObject instanceof Double)
			return formatNumber(paramObject);
		return String.valueOf(paramObject);
	}

	public static Object Object2Object(Class paramClass, Object paramObject) {
		Object localObject;
		try {
			localObject = paramClass.newInstance();
			return Object2Object(localObject, paramObject);
		} catch (InstantiationException localInstantiationException) {
			localInstantiationException.printStackTrace();
		} catch (IllegalAccessException localIllegalAccessException) {
			localIllegalAccessException.printStackTrace();
		}
		return null;
	}

	public static Object Object2Object(Object paramObject1, Object paramObject2) {
		Method[] arrayOfMethod1;
		try {
			String str2;
			Object localObject1;
			Object localObject2;
			arrayOfMethod1 = paramObject2.getClass().getDeclaredMethods();
			ArrayList localArrayList = new ArrayList();
			for (int i = 0; i < arrayOfMethod1.length; ++i) {
				String str1 = arrayOfMethod1[i].getName();
				if (str1.indexOf("get") == 0) {
					str2 = _$1(str1);
					localObject1 = arrayOfMethod1[i].invoke(paramObject2,
							new Object[0]);
					localObject2 = new IIlllllllIlIllIl();
					((IIlllllllIlIllIl) localObject2).setFieldname(str2);
					((IIlllllllIlIllIl) localObject2)
							.setFieldvalue(localObject1);
					localArrayList.add(localObject2);
				}
			}
			Method[] arrayOfMethod2 = paramObject1.getClass()
					.getDeclaredMethods();
			for (int j = 0; j < arrayOfMethod2.length; ++j) {
				str2 = arrayOfMethod2[j].getName();
				if (str2.indexOf("set") == 0) {
					localObject1 = _$1(str2);
					localObject2 = null;
					for (int k = 0; k < localArrayList.size(); ++k) {
						IIlllllllIlIllIl localIIlllllllIlIllIl = (IIlllllllIlIllIl) localArrayList
								.get(k);
						String str3 = localIIlllllllIlIllIl.getFieldname();
						if (((String) localObject1).equals(str3)) {
							localObject2 = localIIlllllllIlIllIl
									.getFieldvalue();
							arrayOfMethod2[j].invoke(paramObject1,
									new Object[] { localObject2 });
							break;
						}
					}
				}
			}
			return paramObject1;
		} catch (SecurityException localSecurityException) {
			localSecurityException.printStackTrace();
		} catch (IllegalArgumentException localIllegalArgumentException) {
			localIllegalArgumentException.printStackTrace();
		} catch (IllegalAccessException localIllegalAccessException) {
			localIllegalAccessException.printStackTrace();
		} catch (InvocationTargetException localInvocationTargetException) {
			localInvocationTargetException.printStackTrace();
		}
		return null;
	}

	public static String filter(String paramString) {
		if ((paramString == null) || (paramString.length() == 0))
			return paramString;
		StringBuffer localStringBuffer = null;
		String str = null;
		for (int i = 0; i < paramString.length(); ++i) {
			str = null;
			switch (paramString.charAt(i)) {
			case '<':
				str = "&lt;";
				break;
			case '>':
				str = "&gt;";
			}
			if (localStringBuffer == null)
				if (str != null) {
					localStringBuffer = new StringBuffer(
							paramString.length() + 50);
					if (i > 0)
						localStringBuffer.append(paramString.substring(0, i));
					localStringBuffer.append(str);
				} else if (str == null)
					localStringBuffer.append(paramString.charAt(i));
				else
					localStringBuffer.append(str);
		}
		return ((localStringBuffer == null) ? paramString : localStringBuffer
				.toString());
	}

	private static String _$1(String paramString) {
		if ((paramString == null) || ("".equals(paramString)))
			return null;
		return paramString.substring(3).toLowerCase();
	}

	public static Field getDeclaredField(Object paramObject, String paramString)
			throws NoSuchFieldException {
		Assert.notNull(paramObject);
		Assert.hasText(paramString);
		return getDeclaredField(paramObject.getClass(), paramString);
	}

	public static Field getDeclaredField(Class paramClass, String paramString)
			throws NoSuchFieldException {
		Assert.notNull(paramClass);
		Assert.hasText(paramString);
		Class localClass = paramClass;
		if (localClass != Object.class) {
			try {
				return localClass.getDeclaredField(paramString);
			} catch (NoSuchFieldException localNoSuchFieldException) {
				localClass = localClass.getSuperclass();
				return getDeclaredField(localClass, paramString);
			}
		}
		throw new NoSuchFieldException("No such field: " + paramClass.getName()
				+ '.' + paramString);
	}

	public static Object forceGetProperty(Object paramObject, String paramString)
			throws NoSuchFieldException {
		Assert.notNull(paramObject);
		Assert.hasText(paramString);
		Field localField = getDeclaredField(paramObject, paramString);
		boolean bool = localField.isAccessible();
		localField.setAccessible(true);
		Object localObject = null;
		try {
			localObject = localField.get(paramObject);
		} catch (IllegalAccessException localIllegalAccessException) {
			logger.info("error wont' happen");
		}
		localField.setAccessible(bool);
		return localObject;
	}

	public static void forceSetProperty(Object paramObject1,
			String paramString, Object paramObject2)
			throws NoSuchFieldException {
		Assert.notNull(paramObject1);
		Assert.hasText(paramString);
		Field localField = getDeclaredField(paramObject1, paramString);
		boolean bool = localField.isAccessible();
		localField.setAccessible(true);
		try {
			localField.set(paramObject1, paramObject2);
		} catch (IllegalAccessException localIllegalAccessException) {
			logger.info("Error won't happen");
		}
		localField.setAccessible(bool);
	}

	public static Object invokePrivateMethod(Object paramObject,
			String paramString, Object[] paramArrayOfObject)
			throws NoSuchMethodException {
		Assert.notNull(paramObject);
		Assert.hasText(paramString);
		Class[] arrayOfClass = new Class[paramArrayOfObject.length];
		for (int i = 0; i < paramArrayOfObject.length; ++i)
			arrayOfClass[i] = paramArrayOfObject[i].getClass();
		Class localClass1 = paramObject.getClass();
		Method localMethod = null;
		Class localClass2 = localClass1;
		if (localClass2 != Object.class)
			try {
				localMethod = localClass2.getDeclaredMethod(paramString,
						arrayOfClass);
			} catch (NoSuchMethodException localNoSuchMethodException) {
				while (true)
					localClass2 = localClass2.getSuperclass();
			}
		if (localMethod == null)
			throw new NoSuchMethodException("No Such Method:"
					+ localClass1.getSimpleName() + paramString);
		boolean bool = localMethod.isAccessible();
		localMethod.setAccessible(true);
		Object localObject = null;
		try {
			localObject = localMethod.invoke(paramObject, paramArrayOfObject);
		} catch (Exception localException) {
			ReflectionUtils.handleReflectionException(localException);
		}
		localMethod.setAccessible(bool);
		return localObject;
	}

	public static List<Field> getFieldsByType(Object paramObject,
			Class paramClass) {
		ArrayList localArrayList = new ArrayList();
		Field[] arrayOfField1 = paramObject.getClass().getDeclaredFields();
		Field[] arrayOfField2 = arrayOfField1;
		int i = arrayOfField2.length;
		for (int j = 0; j < i; ++j) {
			Field localField = arrayOfField2[j];
			if (localField.getType().isAssignableFrom(paramClass))
				localArrayList.add(localField);
		}
		return localArrayList;
	}

	public static Class getPropertyType(Class paramClass, String paramString)
			throws NoSuchFieldException {
		return getDeclaredField(paramClass, paramString).getType();
	}

	public static String getGetterName(Class paramClass, String paramString) {
		Assert.notNull(paramClass, "Type required");
		Assert.hasText(paramString, "FieldName required");
		if (paramClass.getName().equals("boolean"))
			return "is" + StringUtils.capitalize(paramString);
		return "get" + StringUtils.capitalize(paramString);
	}

	public static Method getGetterMethod(Class paramClass, String paramString) {
		try {
			return paramClass.getMethod(getGetterName(paramClass, paramString),
					new Class[0]);
		} catch (NoSuchMethodException localNoSuchMethodException) {
			logger.error(localNoSuchMethodException.getMessage(),
					localNoSuchMethodException);
		}
		return null;
	}

	static {
		_$1.setMinimumIntegerDigits(1);
		_$1.setMinimumFractionDigits(2);
		_$1.setMaximumFractionDigits(2);
	}
}