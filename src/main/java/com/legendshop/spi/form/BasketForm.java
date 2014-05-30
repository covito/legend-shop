package com.legendshop.spi.form;

import java.util.Date;

public class BasketForm
{
  private static final long serialVersionUID = 5076753905519824021L;
  private Integer count;
  private Double carriage;
  private String attribute;
  private Long basketId;
  private Long prodId;
  private String pic;
  private String userName;
  private Integer basketCount;
  private Date basketDate;
  private String basketCheck;
  private String prodName;
  private Double cash;
  private String subNumber;
  private String daili;

  public BasketForm()
  {
  }

  public BasketForm(Long basketId)
  {
    this.basketId = basketId;
  }

  public Long getBasketId()
  {
    return this.basketId;
  }

  public void setBasketId(Long basketId)
  {
    this.basketId = basketId;
  }

  public Long getProdId()
  {
    return this.prodId;
  }

  public void setProdId(Long hwId)
  {
    this.prodId = hwId;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public Integer getBasketCount()
  {
    return this.basketCount;
  }

  public void setBasketCount(Integer basketCount)
  {
    this.basketCount = basketCount;
  }

  public Date getBasketDate()
  {
    return this.basketDate;
  }

  public void setBasketDate(Date basketDate)
  {
    this.basketDate = basketDate;
  }

  public String getBasketCheck()
  {
    return this.basketCheck;
  }

  public void setBasketCheck(String basketCheck)
  {
    this.basketCheck = basketCheck;
  }

  public String getProdName()
  {
    return this.prodName;
  }

  public void setProdName(String hwName)
  {
    this.prodName = hwName;
  }

  public Double getCash()
  {
    return this.cash;
  }

  public void setCash(Double hwCash)
  {
    this.cash = hwCash;
  }

  public String getSubNumber()
  {
    return this.subNumber;
  }

  public void setSubNumber(String subNumber)
  {
    this.subNumber = subNumber;
  }

  public String getDaili()
  {
    return this.daili;
  }

  public void setDaili(String daili)
  {
    this.daili = daili;
  }

  public Integer getCount()
  {
    return this.count;
  }

  public void setCount(Integer count)
  {
    this.count = count;
  }

  public Double getCarriage()
  {
    return this.carriage;
  }

  public void setCarriage(Double carriage)
  {
    this.carriage = carriage;
  }

  public String getAttribute()
  {
    return this.attribute;
  }

  public void setAttribute(String attribute)
  {
    this.attribute = attribute;
  }

  public String getPic()
  {
    return this.pic;
  }

  public void setPic(String hwPic)
  {
    this.pic = hwPic;
  }
}