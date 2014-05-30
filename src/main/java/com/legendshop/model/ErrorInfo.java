package com.legendshop.model;

import java.io.Serializable;

public class ErrorInfo
  implements Serializable
{
  private static final long serialVersionUID = -8291549830638525008L;
  private String filed;
  private ErrorType type;
  private String desc;

  public ErrorInfo(String filed, ErrorType type, String desc)
  {
    this.filed = filed;
    this.type = type;
    this.desc = desc;
  }

  public String getFiled()
  {
    return this.filed;
  }

  public void setFiled(String filed)
  {
    this.filed = filed;
  }

  public String getDesc()
  {
    return this.desc;
  }

  public void setDesc(String desc)
  {
    this.desc = desc;
  }

  public ErrorType getType()
  {
    return this.type;
  }

  public void setType(ErrorType type)
  {
    this.type = type;
  }
}