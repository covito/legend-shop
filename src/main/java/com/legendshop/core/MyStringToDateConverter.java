package com.legendshop.core;

import com.legendshop.util.AppUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;

public class MyStringToDateConverter implements Converter<String, Date> {
	public Date convert(String paramString) {
		if (AppUtils.isBlank(paramString))
			return null;
		try {
			SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			return localSimpleDateFormat.parse(paramString);
		} catch (ParseException localParseException) {
			localParseException.printStackTrace();
		}
		return null;
	}
}