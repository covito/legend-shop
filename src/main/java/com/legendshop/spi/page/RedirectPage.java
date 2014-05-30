package com.legendshop.spi.page;

import com.legendshop.core.constant.PageDefinition;
import com.legendshop.core.constant.PagePathCalculator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public enum RedirectPage implements PageDefinition {
	VARIABLE, VIEWS;

	private final String value = name();

	public String getValue(HttpServletRequest request,
			HttpServletResponse response) {
		return getValue(request, response, this.value, this);
	}

	public String getValue(HttpServletRequest request,
			HttpServletResponse response, String path,
			PageDefinition pageDefinition) {
		return PagePathCalculator.calculateActionPath("redirect:", path);
	}

	public String getNativeValue() {
		return this.value;
	}
}