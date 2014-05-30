package com.legendshop.core.page;

import java.util.Locale;

public abstract interface PageProvider
{
  public abstract String getBar(String paramString);

  public abstract String getBar(Locale paramLocale, String paramString);
}