package com.legendshop.core.constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public enum CommonPage implements PageDefinition {
	FAIL, ERROR_PAGE;

	private String value=name();

	public String getValue(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) {
		return getValue(paramHttpServletRequest, paramHttpServletResponse,
				this.value, this);
	}

	public String getValue(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, String paramString,
			PageDefinition paramPageDefinition) {
		return PagePathCalculator.calculateFronendPath(paramHttpServletRequest,
				paramHttpServletResponse, paramString, paramPageDefinition);
	}

	public String getNativeValue() {
		return this.value;
	}
}