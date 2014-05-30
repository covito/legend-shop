package com.legendshop.core.helper;

import javax.servlet.http.HttpServletRequest;

public interface Checker<T> {
	public boolean check(T paramT, HttpServletRequest paramHttpServletRequest);
}