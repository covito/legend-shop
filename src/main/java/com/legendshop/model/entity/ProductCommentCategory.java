package com.legendshop.model.entity;

import java.io.Serializable;

public class ProductCommentCategory
  implements Serializable
{
  private static final long serialVersionUID = 4592618215956934080L;
  private Long prodId;
  private int total;
  private int high;
  private int medium;
  private int low;
  private double highRate;
  private double mediumRate;
  private double lowRate;

  public Long getProdId()
  {
    return this.prodId;
  }

  public void setProdId(Long prodId)
  {
    this.prodId = prodId;
  }

  public int getTotal()
  {
    return this.total;
  }

  public void setTotal(int total)
  {
    this.total = total;
  }

  public int getHigh()
  {
    return this.high;
  }

  public void setHigh(int high)
  {
    this.high = high;
  }

  public int getMedium()
  {
    return this.medium;
  }

  public void setMedium(int medium)
  {
    this.medium = medium;
  }

  public int getLow()
  {
    return this.low;
  }

  public void setLow(int low)
  {
    this.low = low;
  }

  public double getHighRate()
  {
    return this.highRate;
  }

  public void setHighRate(double highRate)
  {
    this.highRate = highRate;
  }

  public double getMediumRate()
  {
    return this.mediumRate;
  }

  public void setMediumRate(double mediumRate)
  {
    this.mediumRate = mediumRate;
  }

  public double getLowRate()
  {
    return this.lowRate;
  }

  public void setLowRate(double lowRate)
  {
    this.lowRate = lowRate;
  }
}