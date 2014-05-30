package com.legendshop.spi.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface CommonPageService
{
  public abstract String getTop(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);

  public abstract String getCopy(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);

  public abstract String getTopUserInfo(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);
}