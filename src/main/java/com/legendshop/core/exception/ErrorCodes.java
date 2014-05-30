package com.legendshop.core.exception;

public abstract interface ErrorCodes
{
  public static final String NORMAL_STAUTS = "200";
  public static final String SYSTEM_ERROR = "999";
  public static final String BUSINESS_ERROR = "998";
  public static final String SYSTEM_UNINSTALLED = "901";
  public static final String RESOURCE_CONFLICT = "409";
  public static final String INTERNAL_ERROR = "500";
  public static final String UNAUTHORIZED = "401";
  public static final String INSUFFICIENT_PERMISSIONS = "402";
  public static final String ENTITY_NO_FOUND = "404";
  public static final String NON_NULLABLE = "405";
  public static final String LIMITATION_ERROR = "416";
  public static final String AUDITING = "502";
  public static final String TIME_OUT = "503";
  public static final String SAVE_ERROR = "601";
  public static final String UPDATE_ERROR = "602";
  public static final String INVALID_TOKEN = "603";
  public static final String INVALID_FORMAT = "604";
  public static final String NOT_ENOUGH_SCORE = "605";
  public static final String NOT_ENOUGH_STOCKS = "606";
}