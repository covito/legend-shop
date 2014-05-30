package com.legendshop.spi.service;

public abstract interface CommonUtil
{
  public abstract void saveAdminRight(String paramString);

  public abstract void saveUserRight(String paramString);

  public abstract void removeAdminRight(String paramString);

  public abstract void removeUserRight(String paramString);
}