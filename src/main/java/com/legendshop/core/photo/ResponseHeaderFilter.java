package com.legendshop.core.photo;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class ResponseHeaderFilter implements Filter {
	FilterConfig _$1;

	public void doFilter(ServletRequest paramServletRequest,
			ServletResponse paramServletResponse, FilterChain paramFilterChain)
			throws IOException, ServletException {
		HttpServletResponse localHttpServletResponse = (HttpServletResponse) paramServletResponse;
		Enumeration localEnumeration = this._$1.getInitParameterNames();
		while (localEnumeration.hasMoreElements()) {
			String str = (String) localEnumeration.nextElement();
			localHttpServletResponse.setHeader(str,
					this._$1.getInitParameter(str));
		}
		paramFilterChain
				.doFilter(paramServletRequest, localHttpServletResponse);
	}

	public void init(FilterConfig paramFilterConfig) {
		this._$1 = paramFilterConfig;
	}

	public void destroy() {
		this._$1 = null;
	}
}