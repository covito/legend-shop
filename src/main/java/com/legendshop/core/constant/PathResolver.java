package com.legendshop.core.constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathResolver {
	private static final Logger logger = LoggerFactory
			.getLogger(PathResolver.class);

	public static String getPath(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse,
			PageDefinition paramPageDefinition) {
		String str = paramPageDefinition.getValue(paramHttpServletRequest,
				paramHttpServletResponse);
		if (logger.isDebugEnabled())
			logger.debug("enter page {},  path = {}", paramPageDefinition, str);
		return str;
	}

	public static String getPath(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, String paramString,
			PageDefinition paramPageDefinition) {
		String str = paramPageDefinition.getValue(paramHttpServletRequest,
				paramHttpServletResponse, paramString, null);
		if (logger.isDebugEnabled())
			logger.debug("enter page {},  path = {}", paramPageDefinition, str);
		return str;
	}
}