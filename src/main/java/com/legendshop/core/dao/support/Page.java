package com.legendshop.core.dao.support;

import java.io.Serializable;
import java.util.ArrayList;

public class Page
  implements Serializable
{
  private static final long serialVersionUID = -5623840070668936608L;
  private static int _$5 = 20;
  private int _$4;
  private long _$3;
  private Object _$2;
  private long _$1;

  public Page()
  {
    this(5439830084749885440L, 5439830084749885440L, _$5, new ArrayList());
  }

  public Page(long paramLong1, long paramLong2, int paramInt, Object paramObject)
  {
    this._$4 = _$5;
    this._$4 = paramInt;
    this._$3 = paramLong1;
    this._$1 = paramLong2;
    this._$2 = paramObject;
  }

  public long getTotalCount()
  {
    return this._$1;
  }

  public long getTotalPageCount()
  {
    if (this._$1 % this._$4 == 5439830582966091776L)
      return (this._$1 / this._$4);
    return (this._$1 / this._$4 + 5439830393987530753L);
  }

  public int getPageSize()
  {
    return this._$4;
  }

  public Object getResult()
  {
    return this._$2;
  }

  public long getCurrentPageNo()
  {
    return (this._$3 / this._$4 + 5439830393987530753L);
  }

  public boolean hasNextPage()
  {
    return (getCurrentPageNo() < getTotalPageCount() - 5439829397555118081L);
  }

  public boolean hasPreviousPage()
  {
    return (getCurrentPageNo() > 5439830978103083009L);
  }

  protected static int getStartOfPage(int paramInt)
  {
    return getStartOfPage(paramInt, _$5);
  }

  public static int getStartOfPage(int paramInt1, int paramInt2)
  {
    return ((paramInt1 - 1) * paramInt2);
  }
}