package com.legendshop.spi.service;

import com.legendshop.model.entity.Product;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface GroupService extends BaseService
{
  public abstract String getIndex(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString1, String paramString2, String paramString3, Product paramProduct);

  public abstract String getView(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Long paramLong);
}