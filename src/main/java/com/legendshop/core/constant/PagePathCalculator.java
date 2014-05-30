package com.legendshop.core.constant;

import com.legendshop.core.helper.ThreadLocalContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PagePathCalculator {
	public static String calculateBackendPath(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, String paramString,
			PageDefinition paramPageDefinition) {
		StringBuilder localStringBuilder = new StringBuilder("/pages/backend/");
		localStringBuilder.append(ThreadLocalContext.getBackEndType(
				paramHttpServletRequest, paramHttpServletResponse, paramString,
				paramPageDefinition));
		localStringBuilder.append(paramString);
		return localStringBuilder.toString();
	}

	public static String calculatePluginBackendPath(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, String paramString1,
			String paramString2, PageDefinition paramPageDefinition) {
		StringBuilder localStringBuilder = new StringBuilder("/plugins/");
		localStringBuilder.append(paramString1);
		localStringBuilder.append("/jsp/backend/");
		localStringBuilder.append(ThreadLocalContext.getBackEndType(
				paramHttpServletRequest, paramHttpServletResponse,
				paramString2, paramPageDefinition));
		localStringBuilder.append(paramString2);
		return localStringBuilder.toString();
	}

	public static String calculatePluginFronendPath(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, String paramString1,
			String paramString2, PageDefinition paramPageDefinition) {
		StringBuilder localStringBuilder = new StringBuilder("/plugins/");
		localStringBuilder.append(paramString1);
		localStringBuilder.append("/jsp/frontend/");
		localStringBuilder.append(ThreadLocalContext.getFrontType(
				paramHttpServletRequest, paramHttpServletResponse,
				paramString2, paramPageDefinition));
		localStringBuilder.append(paramString2);
		return localStringBuilder.toString();
	}

	public static String calculateFronendPath(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, String paramString,
			PageDefinition paramPageDefinition) {
		StringBuilder localStringBuilder = new StringBuilder("/pages/frontend/");
		localStringBuilder.append(ThreadLocalContext.getFrontType(
				paramHttpServletRequest, paramHttpServletResponse, paramString,
				paramPageDefinition));
		localStringBuilder.append("/");
		localStringBuilder.append(paramString);
		return localStringBuilder.toString();
	}

	public static String calculateActionPath(String paramString1,
			String paramString2) {
		return paramString1 + paramString2;
	}

	public static String calculateTilesPath(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, String paramString,
			PageDefinition paramPageDefinition) {
		return paramString+ThreadLocalContext.getFrontType(paramHttpServletRequest,
						paramHttpServletResponse, paramString,
						paramPageDefinition);
	}
}