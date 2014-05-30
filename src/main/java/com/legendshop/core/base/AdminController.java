package com.legendshop.core.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface AdminController<E, K> extends Controller
{
  public abstract String query(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString, E paramE);

  public abstract String save(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, E paramE);

  public abstract String delete(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, K paramK);

  public abstract String load(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);

  public abstract String update(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, K paramK);
}