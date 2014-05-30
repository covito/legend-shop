package com.legendshop.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.oro.text.regex.MalformedPatternException;

public class AppUtils {
	private static Logger logger = Logger.getLogger(AppUtils.class);

	public static Locale getLocaleFromCookie(
			HttpServletRequest paramHttpServletRequest) {
		Locale localLocale = null;
		String str1 = null;
		String str2 = null;
		Cookie[] arrayOfCookie = paramHttpServletRequest.getCookies();
		if (arrayOfCookie != null)
			for (int i = 0; i < arrayOfCookie.length; ++i) {
				Cookie localObject = arrayOfCookie[i];
				if ("Language".equals(localObject.getName())) {
					str2 = localObject.getValue();
					logger.debug("Found language cookie with value = " + str2);
				} else if (localObject.getName().equals("Country")) {
					str1 = localObject.getValue();
					logger.debug("Found country cookie with value = " + str1);
				}
			}
		if ((str1 != null) && (str2 != null))
			localLocale = new Locale(str2, str1);
		return localLocale;
	}

	public static String getDisplayDate(Calendar paramCalendar) {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"MM/dd/yyyy");
		if (paramCalendar != null)
			return localSimpleDateFormat.format(paramCalendar.getTime());
		return "";
	}

	public static Calendar str2Calendar(String paramString) {
		Calendar localCalendar = null;
		if (isNotBlank(paramString))
			try {
				SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
						"MM/dd/yyyy");
				Date localDate = localSimpleDateFormat.parse(paramString);
				localCalendar = Calendar.getInstance();
				localCalendar.setTime(localDate);
			} catch (ParseException localParseException) {
			}
		return localCalendar;
	}

	public static String getCurrentDate() {
		return getDisplayDate(GregorianCalendar.getInstance());
	}

	public static String getUrlRoot(HttpServletRequest paramHttpServletRequest) {
		String str1 = paramHttpServletRequest.getServerName();
		int i = paramHttpServletRequest.getServerPort();
		String str2 = "http://" + str1 + ":" + i;
		logger.debug("URL root: " + str2);
		return str2;
	}

	public static String getGBString(String paramString) {
		try {
			return new String(paramString.getBytes("ISO-8859-1"), "GB2312");
		} catch (Exception localException) {
		}
		return null;
	}

	public static String getISOString(String paramString) {
		try {
			return new String(paramString.getBytes("GB2312"), "ISO-8859-1");
		} catch (Exception localException) {
		}
		return null;
	}

	public static boolean isBlank(String paramString) {
		return ((paramString == null) || (paramString.trim().length() <= 0));
	}

	public static boolean isNotBlank(String paramString) {
		return (!(isBlank(paramString)));
	}

	public static boolean isBlank(Object[] paramArrayOfObject) {
		return ((paramArrayOfObject == null) || (paramArrayOfObject.length <= 0));
	}

	public static boolean isNotBlank(Object[] paramArrayOfObject) {
		return (!(isBlank(paramArrayOfObject)));
	}

	public static boolean isBlank(Object paramObject) {
		return ((paramObject == null) || ("".equals(paramObject)));
	}

	public static boolean isNotBlank(Object paramObject) {
		return (!(isBlank(paramObject)));
	}

	public static boolean isBlank(Collection paramCollection) {
		return ((paramCollection == null) || (paramCollection.size() <= 0));
	}

	public static boolean isNotBlank(Collection paramCollection) {
		return (!(isBlank(paramCollection)));
	}

	public static boolean isBlank(Set paramSet) {
		return ((paramSet == null) || (paramSet.size() <= 0));
	}

	public static boolean isNotBlank(Set paramSet) {
		return (!(isBlank(paramSet)));
	}

	public static boolean isBlank(Serializable paramSerializable) {
		return (paramSerializable == null);
	}

	public static boolean isNotBlank(Serializable paramSerializable) {
		return (!(isBlank(paramSerializable)));
	}

	public static boolean isBlank(Map paramMap) {
		return ((paramMap == null) || (paramMap.size() <= 0));
	}

	public static boolean isNotBlank(Map paramMap) {
		return (!(isBlank(paramMap)));
	}

	public static String[] list2Strings(List<String> paramList) {
		String[] arrayOfString = null;
		try {
			if (paramList == null)
				return null;
			arrayOfString = new String[paramList.size()];
			for (int i = 0; i < paramList.size(); ++i)
				arrayOfString[i] = ((String) paramList.get(i));
		} catch (Exception localException) {
			logger.error("list is null: " + localException);
		}
		return arrayOfString;
	}

	public static String list2String(List<Object> paramList) {
		if (isBlank(paramList))
			return "";
		StringBuffer localStringBuffer = new StringBuffer();
		localStringBuffer.append(paramList.get(0));
		for (int i = 1; i < paramList.size(); ++i) {
			localStringBuffer.append(",");
			localStringBuffer.append(paramList.get(i));
		}
		return localStringBuffer.toString();
	}

	public static List<String> Strings2List(String[] paramArrayOfString) {
		ArrayList localArrayList = new ArrayList();
		try {
			if (paramArrayOfString == null)
				return null;
			for (int i = 0; i < paramArrayOfString.length; ++i)
				localArrayList.add(paramArrayOfString[i]);
		} catch (Exception localException) {
			logger.error("list is null: " + localException);
		}
		return localArrayList;
	}

	public static String[] getStrings(String paramString) {
		List localList = getStringCollection(paramString);
		if (localList.size() == 0)
			return null;
		return ((String[]) localList.toArray(new String[localList.size()]));
	}

	public static List<String> getStringCollection(String paramString) {
		ArrayList localArrayList = new ArrayList();
		if (paramString == null)
			return localArrayList;
		StringTokenizer localStringTokenizer = new StringTokenizer(paramString,
				",");
		localArrayList = new ArrayList();
		while (localStringTokenizer.hasMoreTokens())
			localArrayList.add(localStringTokenizer.nextToken());
		return localArrayList;
	}

	public static String[] searchByKeyword(String paramString) {
		Pattern.compile("[' ']+");
		Pattern localPattern = Pattern
				.compile("[.。！？#@#￥$%&*()（）=《》<>‘、’；：\"\\?!:']");
		Matcher localMatcher = localPattern.matcher(paramString);
		String str1 = localMatcher.replaceAll(" ").replaceAll("，", ",");
		String str2 = StringUtils.replace(str1, " ", ",");
		String[] arrayOfString = StringUtils.split(str2, ",");
		return arrayOfString;
	}

	public static String formatNumber(Long paramLong) {
		if (paramLong == null)
			return null;
		NumberFormat localNumberFormat = NumberFormat.getIntegerInstance();
		localNumberFormat.setMinimumIntegerDigits(8);
		localNumberFormat.setGroupingUsed(false);
		return localNumberFormat.format(paramLong);
	}

	public static Long getCRC32(String paramString) {
		CRC32 localCRC32 = new CRC32();
		localCRC32.update(paramString.getBytes());
		return Long.valueOf(localCRC32.getValue());
	}

	public static void main(String[] paramArrayOfString) {
		System.out.println(getCRC32("123"));
	}

	public static String convertTemplate(String paramString1,
			String paramString2, Map paramMap) throws MalformedPatternException {
		String str = null;
		StringBuffer localStringBuffer = new StringBuffer();
		try {
			File localFile = new File(paramString1);
			if (!(localFile.exists()))
				return localStringBuffer.toString();
			FileInputStream localFileInputStream = new FileInputStream(
					localFile);
			BufferedReader localBufferedReader = new BufferedReader(
					new InputStreamReader(localFileInputStream, "UTF-8"));
			str = new String();
			while ((str = localBufferedReader.readLine()) != null)
				localStringBuffer.append(StringUtil.convert(str, paramString2,
						paramMap) + "\n");
			localBufferedReader.close();
			localFileInputStream.close();
		} catch (IOException localIOException) {
			System.out.println("got an IOException error!");
			localIOException.printStackTrace();
		}
		return localStringBuffer.toString();
	}

	public static String convertTemplate(String paramString, Map paramMap)
			throws MalformedPatternException {
		return convertTemplate(paramString, "\\#[a-zA-Z]+\\#", paramMap);
	}

	public static String arrayToString(String[] paramArrayOfString) {
		if (paramArrayOfString.length == 0)
			return "";
		StringBuffer localStringBuffer = new StringBuffer();
		localStringBuffer.append(paramArrayOfString[0]);
		for (int i = 1; i < paramArrayOfString.length; ++i) {
			localStringBuffer.append(",");
			localStringBuffer.append(paramArrayOfString[i]);
		}
		return localStringBuffer.toString();
	}

	public static String getDefaultValue(String paramString1,
			String paramString2) {
		if (isNotBlank(paramString1))
			return paramString1;
		return paramString2;
	}
}