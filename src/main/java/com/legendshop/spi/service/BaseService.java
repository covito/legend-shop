package com.legendshop.spi.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface BaseService
{
  public abstract Object getSessionAttribute(HttpServletRequest paramHttpServletRequest, String paramString);

  public abstract void setSessionAttribute(HttpServletRequest paramHttpServletRequest, String paramString, Object paramObject);

  public abstract void getAndSetAdvertisement(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString1, String paramString2);

  public abstract void getAndSetOneAdvertisement(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString1, String paramString2);

  public abstract String checkPrivilege(HttpServletRequest paramHttpServletRequest, String paramString1, String paramString2);
}