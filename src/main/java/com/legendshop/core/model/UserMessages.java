package com.legendshop.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserMessages
  implements Serializable
{
  private static final long serialVersionUID = 2811715881322185950L;
  public static String MESSAGE_KEY = "User_Messages";
  private String _$4;
  private String _$3;
  private String _$2;
  private List<CallBackEntity> _$1 = new ArrayList();

  public UserMessages()
  {
  }

  public UserMessages(String paramString1, String paramString2, String paramString3)
  {
    this._$4 = paramString1;
    this._$3 = paramString2;
    this._$2 = paramString3;
  }

  public String getTitle()
  {
    return this._$3;
  }

  public void setTitle(String paramString)
  {
    this._$3 = paramString;
  }

  public String getDesc()
  {
    return this._$2;
  }

  public void setDesc(String paramString)
  {
    this._$2 = paramString;
  }

  public List<CallBackEntity> getCallBackList()
  {
    return this._$1;
  }

  public void addCallBackList(String paramString1, String paramString2, String paramString3)
  {
    CallBackEntity localCallBackEntity = new CallBackEntity(paramString1, paramString2, paramString3);
    this._$1.add(localCallBackEntity);
  }

  public void addCallBackList(String paramString1, String paramString2)
  {
    CallBackEntity localCallBackEntity = new CallBackEntity(paramString1, paramString2, null);
    this._$1.add(localCallBackEntity);
  }

  public void addCallBackList(String paramString)
  {
    CallBackEntity localCallBackEntity = new CallBackEntity(paramString, null, null);
    this._$1.add(localCallBackEntity);
  }

  public boolean hasError()
  {
    return (this._$1.size() > 0);
  }

  public void setCallBackList(List<CallBackEntity> paramList)
  {
    this._$1 = paramList;
  }

  public String getCode()
  {
    return this._$4;
  }

  public void setCode(String paramString)
  {
    this._$4 = paramString;
  }

  public String toString()
  {
    return "code = " + this._$4 + ",title = " + this._$3 + ",desc = " + this._$2;
  }
}