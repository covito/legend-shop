package com.legendshop.spi.locator;

import com.legendshop.core.constant.PageDefinition;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface ServiceLocator<T>
{
  public abstract T getConcreteService(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, PageDefinition paramPageDefinition);
}