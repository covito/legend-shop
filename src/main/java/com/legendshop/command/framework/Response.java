package com.legendshop.command.framework;

import java.util.Map;

public class Response extends DataTransferObject
{
  public static final int SUCCESS = 0;
  public static final int PARTIAL_SUCCESS = -1;
  public static final int APPLICATION_LEVEL_ERROR = -2;
  public static final int SYSTEM_LEVEL_ERROR = -3;
  private int _$3 = 0;
  private State _$2;

  public Response()
  {
    this._$2 = new StateImpl();
  }

  public Response(Map paramMap)
  {
    super(paramMap);
  }

  public void setReturnCode(int paramInt)
  {
    this._$3 = paramInt;
  }

  public int getReturnCode()
  {
    return this._$3;
  }

  public State getState()
  {
    return this._$2;
  }

  public boolean isSuccessful()
  {
    return (0 == this._$3);
  }
}