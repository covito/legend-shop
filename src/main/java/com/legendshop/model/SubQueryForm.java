package com.legendshop.model;

import java.io.Serializable;

public class SubQueryForm
  implements Serializable
{
  private static final long serialVersionUID = 453773941274461497L;
  Integer orderType;
  Integer orderActiveStatus;
  Integer kwType;
  String kwText;

  public Integer getOrderType()
  {
    return this.orderType;
  }

  public void setOrderType(Integer orderType)
  {
    this.orderType = orderType;
  }

  public Integer getOrderActiveStatus()
  {
    return this.orderActiveStatus;
  }

  public void setOrderActiveStatus(Integer orderActiveStatus)
  {
    this.orderActiveStatus = orderActiveStatus;
  }

  public Integer getKwType()
  {
    return this.kwType;
  }

  public void setKwType(Integer kwType)
  {
    this.kwType = kwType;
  }

  public String getKwText()
  {
    return this.kwText;
  }

  public void setKwText(String kwText)
  {
    this.kwText = kwText;
  }
}