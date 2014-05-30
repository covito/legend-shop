package com.legendshop.core.dao.jdbc.dialect;

public abstract interface Dialect
{
  public abstract String getLimitString(String paramString, int paramInt1, int paramInt2);

  public abstract String getLimitString(String paramString, boolean paramBoolean);
}