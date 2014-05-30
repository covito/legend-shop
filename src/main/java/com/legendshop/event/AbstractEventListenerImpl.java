package com.legendshop.event;

public abstract class AbstractEventListenerImpl<T>
  implements BaseEventListener<T>
{
  private String _$2;
  private Integer _$1 = Integer.valueOf(100);

  public Integer getOrder()
  {
    return this._$1;
  }

  public void setOrder(Integer paramInteger)
  {
    this._$1 = paramInteger;
  }

  public String getEventId()
  {
    return this._$2;
  }

  public void setEventId(String paramString)
  {
    this._$2 = paramString;
  }
}