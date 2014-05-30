package com.legendshop.core.cache;

import com.legendshop.util.AppUtils;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IdListRel
  implements Serializable
{
  private static final long serialVersionUID = 1299645041971703402L;
  private final Serializable _$2;
  private List<CacheNameAndItemWrapper> _$1 = null;

  public IdListRel(Serializable paramSerializable)
  {
    this._$2 = paramSerializable;
  }

  public Object get()
  {
    return this._$2;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(this._$2);
    if (AppUtils.isNotBlank(this._$1))
    {
      Iterator localIterator = this._$1.iterator();
      while (localIterator.hasNext())
      {
        CacheNameAndItemWrapper localCacheNameAndItemWrapper = (CacheNameAndItemWrapper)localIterator.next();
        localStringBuffer.append("[").append(localCacheNameAndItemWrapper.getCacheName()).append(",").append(localCacheNameAndItemWrapper.getKey()).append("]");
      }
    }
    return localStringBuffer.toString();
  }

  public List<CacheNameAndItemWrapper> getRelObject()
  {
    return this._$1;
  }

  public boolean addRelObject(String paramString, Object paramObject)
  {
    if (this._$1 == null)
      this._$1 = new ArrayList(5);
    CacheNameAndItemWrapper localCacheNameAndItemWrapper = new CacheNameAndItemWrapper(paramString, paramObject);
    if (!(this._$1.contains(localCacheNameAndItemWrapper)))
    {
      this._$1.add(localCacheNameAndItemWrapper);
      return true;
    }
    return false;
  }

  public static void main(String[] paramArrayOfString)
  {
    CacheNameAndItemWrapper localCacheNameAndItemWrapper1 = new CacheNameAndItemWrapper("a", "b");
    CacheNameAndItemWrapper localCacheNameAndItemWrapper2 = new CacheNameAndItemWrapper("a", "b");
    CacheNameAndItemWrapper localCacheNameAndItemWrapper3 = new CacheNameAndItemWrapper("a1", "c");
    System.out.println(localCacheNameAndItemWrapper1.equals(localCacheNameAndItemWrapper2));
    System.out.println(localCacheNameAndItemWrapper1.equals(localCacheNameAndItemWrapper3));
    System.out.println(localCacheNameAndItemWrapper1.equals(null));
  }
}