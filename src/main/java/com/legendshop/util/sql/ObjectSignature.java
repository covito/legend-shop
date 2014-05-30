package com.legendshop.util.sql;

public class ObjectSignature
{
  protected String objClassName;
  protected String methodName = null;

  public ObjectSignature(String paramString)
  {
    this.objClassName = paramString;
  }

  public String getObjectClassName()
  {
    return this.objClassName;
  }

  public static String toSignature(String paramString1, String paramString2)
  {
    return paramString1 + '.' + paramString2;
  }

  public String getMethodName()
  {
    return this.methodName;
  }

  public void setMethodName(String paramString)
  {
    this.methodName = paramString;
  }

  public String toSignature()
  {
    return toSignature(getObjectClassName(), this.methodName);
  }

  protected ObjectSignature()
  {
    this.objClassName = null;
  }
}