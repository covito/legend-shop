package com.legendshop.command.framework.facade;

public abstract class AbstractBizDelegate
  implements BizDelegate
{
  private DelegateType _$1;

  public void setDelegate(DelegateType paramDelegateType)
  {
    this._$1 = paramDelegateType;
  }

  public DelegateType getDelegate()
  {
    return this._$1;
  }

  public synchronized boolean init(String paramString)
  {
    return this._$1.init(paramString);
  }
}