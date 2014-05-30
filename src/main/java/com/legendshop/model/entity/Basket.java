package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

public class Basket
  implements BaseEntity
{
  private static final long serialVersionUID = -2428049829073308967L;
  private Double total;
  private Double carriage;
  private String attribute;
  private Long basketId;
  private Long prodId;
  private String pic;
  private String userName;
  private Integer basketCount;
  private String basketCheck;
  private String prodName;
  private Double price;
  private Double cash;
  private String subNumber;
  private Date basketDate;
  private Date lastUpdateDate;
  private String shopName;

  public Basket()
  {
  }

  public Basket(Long basketId, Double cash, Double carriage, Long prodId, String pic, Integer basketCount, String prodName, String attribute)
  {
    this.basketId = basketId;
    this.cash = cash;
    this.carriage = carriage;
    this.prodId = prodId;
    this.pic = pic;
    this.basketCount = basketCount;
    this.prodName = prodName;
    this.attribute = attribute;
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

  public void setProdId(Long prodId)
  {
    this.prodId = prodId;
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

  public void setProdName(String prodName)
  {
    this.prodName = prodName;
  }

  public Double getPrice()
  {
    return this.price;
  }

  public void setPrice(Double price)
  {
    this.price = price;
  }

  public Double getCash()
  {
    return this.cash;
  }

  public void setCash(Double cash)
  {
    this.cash = cash;
  }

  public String getSubNumber()
  {
    return this.subNumber;
  }

  public void setSubNumber(String subNumber)
  {
    this.subNumber = subNumber;
  }

  public Date getBasketDate()
  {
    return this.basketDate;
  }

  public void setBasketDate(Date basketDate)
  {
    this.basketDate = basketDate;
  }

  public Double getTotal()
  {
    return this.total;
  }

  public void setTotal(Double total)
  {
    this.total = total;
  }

  public String getShopName()
  {
    return this.shopName;
  }

  public void setShopName(String shopName)
  {
    this.shopName = shopName;
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

  public void setPic(String pic)
  {
    this.pic = pic;
  }

  public Date getLastUpdateDate()
  {
    return this.lastUpdateDate;
  }

  public void setLastUpdateDate(Date lastUpdateDate)
  {
    this.lastUpdateDate = lastUpdateDate;
  }

  public Serializable getId() {
    return this.basketId;
  }
}