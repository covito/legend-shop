package com.legendshop.util;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

public class TimerUtil {
	private static final Logger _$2 = Logger.getRootLogger();
	private static String _$1 = "yyyy-MM-dd";
	public static String TIME_MIN = "MIN";
	public static String TIME_HOUR = "HOUR";
	public static String TIME_DAY = "DAY";
	public static String TIME_MONTH = "MONTH";
	public static String TIME_YEAR = "YEAR";
	public static String MID_DATA_FORMAT;
	public static Calendar calendar = new GregorianCalendar();
	public static DateFormat dateFormat;
	public static String JAVA_DATE_FORMAT = "yyyy:MM:dd HH:mm:ss";
	public static String ORACLE_DATE_FORMAT = "YYYY:MM:DD HH24:mi:ss";
	public static String MSSQLSERVER_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public TimerUtil() {
	}

	public TimerUtil(String paramString) {
		_$1 = paramString;
	}

	public String getStrCurrentDate() {
		String str = null;
		try {
			SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(_$1);
			str = localSimpleDateFormat.format(new Date());
		} catch (Exception localException) {
			_$2.error(localException.getMessage());
		}
		return str;
	}

	public long getTimeToLong(String paramString) {
		Date localDate = null;
		try {
			SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(_$1);
			ParsePosition localParsePosition = new ParsePosition(0);
			localDate = localSimpleDateFormat.parse(paramString,
					localParsePosition);
		} catch (Exception localException) {
			_$2.error(localException.getMessage());
		}
		return localDate.getTime();
	}

	public static Date getCurrentDate() {
		return new Date();
	}

	public static Date getNowDateShort() {
		Date localDate1 = new Date();
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		String str = localSimpleDateFormat.format(localDate1);
		ParsePosition localParsePosition = new ParsePosition(0);
		Date localDate2 = localSimpleDateFormat.parse(str, localParsePosition);
		return localDate2;
	}

	public static String getStrDate() {
		Date localDate = new Date();
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String str = localSimpleDateFormat.format(localDate);
		return str;
	}

	public static String getStrDateShort() {
		Date localDate = new Date();
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		String str = localSimpleDateFormat.format(localDate);
		return str;
	}

	public static Date strToDate(String paramString) {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		ParsePosition localParsePosition = new ParsePosition(0);
		Date localDate = localSimpleDateFormat.parse(paramString,
				localParsePosition);
		return localDate;
	}

	public static String dateToStr(Date paramDate) {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String str = localSimpleDateFormat.format(paramDate);
		return str;
	}

	public static String dateToStrShort(Date paramDate) {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		String str = localSimpleDateFormat.format(paramDate);
		return str;
	}

	public static Date strToDateShort(String paramString) {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		ParsePosition localParsePosition = new ParsePosition(0);
		Date localDate = localSimpleDateFormat.parse(paramString,
				localParsePosition);
		return localDate;
	}

	public static Date getLastDate(long paramLong) {
		Date localDate1 = new Date();
		long l = localDate1.getTime() - 122400000L * paramLong;
		Date localDate2 = new Date(l);
		return localDate2;
	}

	public static Date getDate(Date paramDate, Integer paramInteger,
			String paramString) {
		if (paramInteger != null)
			return getDate(paramDate, paramInteger.intValue(), paramString);
		return paramDate;
	}

	public static Date getDate(Date paramDate, int paramInt, String paramString) {
		calendar.setTime(paramDate);
		if (paramString.equalsIgnoreCase(TIME_MIN))
			calendar.add(12, paramInt);
		else if (paramString.equalsIgnoreCase(TIME_HOUR))
			calendar.add(11, paramInt);
		else if (paramString.equalsIgnoreCase(TIME_DAY))
			calendar.add(5, paramInt);
		else if (paramString.equalsIgnoreCase(TIME_MONTH))
			calendar.add(2, paramInt);
		else if (paramString.equalsIgnoreCase(TIME_YEAR))
			calendar.add(1, paramInt);
		return calendar.getTime();
	}

	public static String getOracleDateStr(Date paramDate) {
		if (paramDate == null)
			return null;
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				JAVA_DATE_FORMAT);
		String str = localSimpleDateFormat.format(paramDate);
		return str;
	}

	public static String getSqlServerDateStr(Date paramDate) {
		if (paramDate == null)
			return null;
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				MSSQLSERVER_DATE_FORMAT);
		String str = localSimpleDateFormat.format(paramDate);
		return str;
	}

	public static void main(String[] paramArrayOfString) {
		TimerUtil localTimerUtil = new TimerUtil("yyyy-MM-dd HH:mm:ss");
		Date localDate1 = new Date();
		Date localDate2 = getCurrentDate();
		Date localDate3 = strToDateShort("2006-12-12");
		long l = localDate3.getTime();
		System.out.println("getNowDay==" + localTimerUtil);
		System.out.println("nowLong==" + l);
		System.out.println("date   ==" + dateToStr(localDate1));
		System.out.println("date111=="
				+ dateToStr(getDate(localDate1, 100, TIME_YEAR)));
		System.out.println("-----------------");
		Date localDate4 = getNowDateShort();
		System.out.println(" today = " + localDate4);
		Date localDate5 = strToDateShort("2013-09-16");
		Date localDate6 = strToDateShort("2013-09-17");
		System.out.println("Start Date = " + localDate5 + ", endDate = "
				+ localDate6);
		System.out.println(" startDate.after(today) = "
				+ localDate5.after(localDate4));
		System.out.println("endDate.before(today) = "
				+ localDate6.before(localDate4));
	}

	static {
		MID_DATA_FORMAT = "yyyy-MM-dd";
		dateFormat = new SimpleDateFormat(MID_DATA_FORMAT);
	}
}