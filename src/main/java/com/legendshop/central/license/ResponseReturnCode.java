package com.legendshop.central.license;

public abstract interface ResponseReturnCode
{
  public static final int UPGRADE_OK = 1;
  public static final int KEY_UNMATCH = 2;
  public static final int UPGRADE_FAIL = 3;
}