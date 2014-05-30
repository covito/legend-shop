package com.legendshop.spi.service;

import com.legendshop.spi.form.SearchForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface BusinessService extends BaseService
{
  public abstract String getFriendlink(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);

  public abstract String getLeague(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);

  public abstract String getNewsforCommon(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);

  public abstract void getVisitedShop(HttpServletRequest paramHttpServletRequest);

  public abstract String search(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, SearchForm paramSearchForm);
}