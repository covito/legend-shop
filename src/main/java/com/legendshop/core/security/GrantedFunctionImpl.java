package com.legendshop.core.security;

public class GrantedFunctionImpl
  implements GrantedFunction, Comparable<GrantedFunction>
{
  private static final long serialVersionUID = -5538145818148135353L;
  private String _$1;

  public GrantedFunctionImpl(String paramString)
  {
    this._$1 = paramString;
  }

  protected GrantedFunctionImpl()
  {
    throw new IllegalArgumentException("Cannot use default constructor");
  }

  public String getFunction()
  {
    return this._$1;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject instanceof String)
      return paramObject.equals(this._$1);
    if (paramObject instanceof GrantedFunction)
    {
      GrantedFunction localGrantedFunction = (GrantedFunction)paramObject;
      return this._$1.equals(localGrantedFunction.getFunction());
    }
    return false;
  }

  public int hashCode()
  {
    return this._$1.hashCode();
  }

  public String toString()
  {
    return this._$1;
  }

  public int compareTo(GrantedFunction paramGrantedFunction)
  {
    return paramGrantedFunction.getFunction().compareTo(this._$1);
  }
}