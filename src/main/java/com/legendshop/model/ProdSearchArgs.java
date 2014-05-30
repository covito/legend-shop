package com.legendshop.model;

import java.io.Serializable;

public class ProdSearchArgs
  implements Serializable
{
  private static final long serialVersionUID = 9071833733004312945L;
  private String orderDir;
  private String orderBy;
  private boolean hasProd;

  public String getOrderDir()
  {
    return this.orderDir;
  }

  public void setOrderDir(String orderDir)
  {
    this.orderDir = orderDir;
  }

  public String getOrderBy()
  {
    return this.orderBy;
  }

  public void setOrderBy(String orderBy)
  {
    this.orderBy = orderBy;
  }

  public boolean isHasProd()
  {
    return this.hasProd;
  }

  public void setHasProd(boolean hasProd)
  {
    this.hasProd = hasProd;
  }
}