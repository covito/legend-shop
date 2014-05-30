package com.legendshop.core.constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PageDefinition
{
  public static final int FRONT_PAGE = 1;
  public static final int BACK_PAGE = 2;
  public static final int TILES = 3;
  public static final int FOWARD = 4;
  public static final int REDIRECT = 5;
  public static final String LOGIN_PATH = "login.";
  public static final String ERROR_PAGE_PATH = "/common/error";
  public static final String DEFAULT_THEME_PATH = "default";

  public abstract String getValue(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);

  public abstract String getNativeValue();

  public abstract String getValue(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString, PageDefinition paramPageDefinition);
}