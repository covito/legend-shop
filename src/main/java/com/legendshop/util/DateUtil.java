package com.legendshop.util;

import java.io.PrintStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtil {
	public static final String CM_LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String CM_SHORT_DATE_FORMAT = "yyyyMMdd";
	public static final String CM_SHORT_MONTH_FORMAT = "yyyy-MM";
	public static final String CM_SHORT_YEAR_FORMAT = "yyyy";
	public static final String YEAR_MONTH = "yyyyMM";
	public static final String[] MONTH = { "January", "February", "March",
			"April", "May", "June", "July", "August", "September", "October",
			"November", "December" };
	public static final String[] DAY = { "Sunday", "Monday", "Tuesday",
			"Wednesday", "Thursday", "Friday", "Saturday" };
	public static DateFormat dateFormat = DateFormat.getDateInstance(0);

	public static String getToday() {
		java.util.Date localDate = new java.util.Date();
		String str = DateToString(localDate, "yyyyMMdd");
		return str;
	}

	public static long getTodayInTimeFormat() {
		java.util.Date localDate = new java.util.Date();
		long l = localDate.getTime();
		return l;
	}

	public static String getNowYear() {
		java.util.Date localDate = new java.util.Date();
		String str = DateToString(localDate, "yyyy");
		return str;
	}

	public static Timestamp getNowTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static String getMonth() {
		java.util.Date localDate = new java.util.Date();
		String str = DateToString(localDate, "yyyy-MM");
		return str;
	}

	public static String getMonth(String paramString) {
		java.util.Date localDate = new java.util.Date();
		String str = DateToString(localDate, paramString);
		return str;
	}

	public static String getNextMonth() {
		java.util.Date localDate = new java.util.Date();
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(localDate);
		localCalendar.add(2, 1);
		String str = DateToString(localCalendar.getTime(), "yyyy-MM");
		return str;
	}

	public static String getNextMonth(String paramString) {
		java.util.Date localDate = new java.util.Date();
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(localDate);
		localCalendar.add(2, 1);
		String str = DateToString(localCalendar.getTime(), paramString);
		return str;
	}

	public static java.sql.Date getMonthDate(java.util.Date paramDate,
			int paramInt) {
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(paramDate);
		localCalendar.add(2, paramInt);
		java.util.Date localDate = localCalendar.getTime();
		return new java.sql.Date(localDate.getTime());
	}

	public static String getUpMonth() {
		java.util.Date localDate = new java.util.Date();
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(localDate);
		localCalendar.add(2, -1);
		String str = DateToString(localCalendar.getTime(), "yyyy-MM");
		return str;
	}

	public static String getUpMonth(String paramString1, String paramString2,
			String paramString3) {
		java.sql.Date localDate = getDate(paramString1, paramString2, "01");
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(localDate);
		localCalendar.add(2, -1);
		String str = DateToString(localCalendar.getTime(), paramString3);
		return str;
	}

	public static String getNextMonth(String paramString1, String paramString2,
			String paramString3) {
		java.sql.Date localDate = getDate(paramString1, paramString2, "01");
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(localDate);
		localCalendar.add(2, 1);
		String str = DateToString(localCalendar.getTime(), paramString3);
		return str;
	}

	public static List getYear(java.util.Date paramDate, int paramInt) {
		String str;
		ArrayList localArrayList = new ArrayList();
		Calendar localCalendar = Calendar.getInstance();
		int i = Math.abs(paramInt);
		if (paramInt >= 0)
			for (int j = 0; j < paramInt; ++j) {
				localCalendar.setTime(paramDate);
				localCalendar.add(1, j);
				str = DateToString(localCalendar.getTime(), "yyyy");
				localArrayList.add(str);
			}
		else
			for (int k = 1; k <= i; ++k) {
				localCalendar.setTime(paramDate);
				localCalendar.add(1, -k);
				str = DateToString(localCalendar.getTime(), "yyyy");
				localArrayList.add(str);
			}
		return localArrayList;
	}

	public static String getTomorrow() {
		java.util.Date localDate = new java.util.Date();
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(localDate);
		localCalendar.add(5, 1);
		String str = DateToString(localCalendar.getTime(), "yyyyMMdd");
		return str;
	}

	public static String getDayAfterTomorrow() {
		java.util.Date localDate = new java.util.Date();
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(localDate);
		localCalendar.add(5, 2);
		String str = DateToString(localCalendar.getTime(), "yyyyMMdd");
		return str;
	}

	public static String getYesterday() {
		java.util.Date localDate = new java.util.Date();
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(localDate);
		localCalendar.add(5, -1);
		String str = DateToString(localCalendar.getTime(), "yyyyMMdd");
		return str;
	}

	public static String getFullDateString(String paramString) {
		java.util.Date localDate = StringToDate(paramString);
		return dateFormat.format(localDate);
	}

	public static String DateToString(java.util.Date paramDate,
			String paramString) {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				paramString);
		return localSimpleDateFormat.format(paramDate);
	}

	public static java.util.Date StringToDate(String paramString) {
		java.util.Date localDate = new java.util.Date();
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"yyyyMMdd");
		try {
			localDate = localSimpleDateFormat.parse(paramString);
		} catch (ParseException localParseException) {
			localParseException.printStackTrace();
		}
		return localDate;
	}

	public static java.util.Date StringToDate(String paramString1,
			String paramString2) {
		java.util.Date localDate = new java.util.Date();
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				paramString2);
		try {
			localDate = localSimpleDateFormat.parse(paramString1);
		} catch (ParseException localParseException) {
			localParseException.printStackTrace();
		}
		return localDate;
	}

	public static String getEndDate(String paramString, int paramInt) {
		Calendar localCalendar = Calendar.getInstance();
		java.util.Date localDate = StringToDate(paramString);
		localCalendar.setTime(localDate);
		localCalendar.add(5, paramInt);
		return DateToString(localCalendar.getTime(), "yyyyMMdd");
	}

	public static String getEndDateForSQLDate(String paramString, int paramInt) {
		Calendar localCalendar = Calendar.getInstance();
		java.util.Date localDate = StringToDateByFormat(paramString, "yyyyMMdd");
		localCalendar.setTime(localDate);
		localCalendar.add(5, paramInt);
		return DateToString(localCalendar.getTime(), "yyyyMMdd");
	}

	public static java.util.Date StringToDateByFormat(String paramString1,
			String paramString2) {
		java.util.Date localDate = new java.util.Date();
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				paramString2);
		try {
			localDate = localSimpleDateFormat.parse(paramString1);
		} catch (ParseException localParseException) {
			localParseException.printStackTrace();
		}
		return localDate;
	}

	public static List getEndWeekDayOfMonth(String paramString1,
			String paramString2) {
		ArrayList localArrayList = new ArrayList();
		String str = "";
		int i = daysInMonth(paramString1, paramString2);
		int j = 0;
		for (int k = 1; k <= i; ++k) {
			j = getWeekOfMonth(paramString1, paramString2, String.valueOf(k));
			if (j == 5)
				if (k < 10)
					localArrayList.add(paramString1 + paramString2 + "0"
							+ String.valueOf(k));
				else
					localArrayList.add(paramString1 + paramString2
							+ String.valueOf(k));
		}
		for (int k = 0; k < localArrayList.size(); ++k)
			System.out.println("end week list[" + k + "]:"
					+ localArrayList.get(k));
		return localArrayList;
	}

	public static int daysInMonth(String paramString1, String paramString2) {
		int i = Integer.parseInt(paramString1);
		int j = Integer.parseInt(paramString2);
		GregorianCalendar localGregorianCalendar = new GregorianCalendar(i, j,
				0);
		int[] arrayOfInt = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		arrayOfInt[1] += ((localGregorianCalendar
				.isLeapYear(localGregorianCalendar.get(1))) ? 1 : 0);
		return arrayOfInt[localGregorianCalendar.get(2)];
	}

	public static int getWeekOfMonth(String paramString1, String paramString2,
			String paramString3) {
		java.sql.Date localDate = getDate(paramString1, paramString2,
				paramString3);
		int i = 0;
		try {
			Calendar localCalendar = Calendar.getInstance();
			localCalendar.setTime(localDate);
			i = localCalendar.get(7);
			if (i <= 1)
				i = 7;
			else
				i -= 1;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return i;
	}

	public static java.sql.Date getDate(String paramString1,
			String paramString2, String paramString3) {
		java.sql.Date localDate = null;
		try {
			String str = paramString1 + "-" + paramString2 + "-" + paramString3;
			SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			java.util.Date localDate1 = localSimpleDateFormat.parse(str);
			localDate = new java.sql.Date(localDate1.getTime());
		} catch (Exception localException) {
			System.out.println("Exception " + localException);
		}
		return localDate;
	}

	public static String getFirstDayOfMonth() {
		StringBuffer localStringBuffer = new StringBuffer();
		String str = getToday();
		localStringBuffer.append(str.substring(0, 6)).append("01");
		return localStringBuffer.toString();
	}

	public static long getFirstDayOfMonthInTimeFormat() {
		StringBuffer localStringBuffer = new StringBuffer();
		String str = getToday();
		localStringBuffer.append(str.substring(0, 6)).append("01");
		long l = StringToDateByFormat(localStringBuffer.toString(), "yyyyMMdd")
				.getTime();
		return l;
	}

	public static String getFirstDayOfOffsetMonth(int paramInt) {
		StringBuffer localStringBuffer = new StringBuffer();
		java.util.Date localDate = new java.util.Date();
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(localDate);
		localCalendar.add(2, paramInt);
		String str = DateToString(localCalendar.getTime(), "yyyyMM");
		localStringBuffer.append(str).append("01");
		return localStringBuffer.toString();
	}

	public static long StingToLong(String paramString) {
		return StringToDateByFormat(paramString, "yyyyMMdd").getTime();
	}

	public static long StingToLong(String paramString1, String paramString2) {
		return StringToDateByFormat(paramString1, paramString2).getTime();
	}

	public static String getEndDateOfUpMonth(java.util.Date paramDate) {
		StringBuffer localStringBuffer = new StringBuffer();
		String str1 = DateToString(paramDate, "yyyyMMdd");
		String str2 = getUpMonth(str1.substring(0, 4), str1.substring(4, 6),
				"yyyyMM");
		localStringBuffer.append(str2).append(
				daysInMonth(str2.substring(0, 4), str2.substring(4)));
		str1 = localStringBuffer.toString();
		localStringBuffer = null;
		return str1;
	}

	public static String getEndDateOfNextMonth(java.util.Date paramDate) {
		StringBuffer localStringBuffer = new StringBuffer();
		String str1 = DateToString(paramDate, "yyyyMMdd");
		String str2 = getNextMonth(str1.substring(0, 4), str1.substring(4, 6),
				"yyyyMM");
		localStringBuffer.append(str2).append(
				daysInMonth(str2.substring(0, 4), str2.substring(4)));
		str1 = localStringBuffer.toString();
		localStringBuffer = null;
		return str1;
	}

	public static java.util.Date add(java.util.Date paramDate, int paramInt,
			long paramLong) {
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(paramDate);
		localCalendar.add(paramInt, (int) paramLong);
		return localCalendar.getTime();
	}

	public static String getFirstDayOfOffsetMonth(String paramString1,
			String paramString2, int paramInt) {
		java.util.Date localDate = StringToDate(paramString1, paramString2);
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(localDate);
		localCalendar.set(5, 1);
		localCalendar.add(2, paramInt);
		String str = DateToString(localCalendar.getTime(), "yyyyMMdd");
		return str;
	}

	public static java.util.Date getFirstDayOfMonth(String paramString1,
			String paramString2) {
		return null;
	}

	public static java.util.Date getTimeMonthsAgo(int paramInt) {
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(new java.util.Date());
		localCalendar.add(2, 0 - paramInt);
		return localCalendar.getTime();
	}
}