package com.legendshop.core.cache;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class CacheNameAndItemWrapper
  implements Serializable
{
  private String _$2;
  private Object _$1;

  public CacheNameAndItemWrapper(String paramString, Object paramObject)
  {
    this._$2 = paramString;
    this._$1 = paramObject;
  }

  public String getCacheName()
  {
    return this._$2;
  }

  public void setCacheName(String paramString)
  {
    this._$2 = paramString;
  }

  public Object getKey()
  {
    return this._$1;
  }

  public void setKey(Object paramObject)
  {
    this._$1 = paramObject;
  }

  public int hashCode()
  {
    return new HashCodeBuilder(17, 31).append(this._$2).append(this._$1).toHashCode();
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    if (paramObject == this)
      return true;
    if (paramObject.getClass() != super.getClass())
      return false;
    CacheNameAndItemWrapper localCacheNameAndItemWrapper = (CacheNameAndItemWrapper)paramObject;
    return new EqualsBuilder().append(this._$2, localCacheNameAndItemWrapper.getCacheName()).append(this._$1, localCacheNameAndItemWrapper.getKey()).isEquals();
  }

  public String toString()
  {
    return this._$2 + this._$1;
  }
}