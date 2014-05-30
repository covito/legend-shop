package com.legendshop.model.entity;

import java.io.Serializable;

public class PagerForm
  implements Serializable
{
  private static final long serialVersionUID = -1699328271492126860L;
  private String curPageNO;

  public String getCurPageNO()
  {
    return this.curPageNO;
  }

  public void setCurPageNO(String curPageNO)
  {
    this.curPageNO = curPageNO;
  }
}