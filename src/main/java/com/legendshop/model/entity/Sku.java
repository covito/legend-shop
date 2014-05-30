package com.legendshop.model.entity;

import java.util.Date;

public class Sku
  implements BaseEntity
{
  private static final long serialVersionUID = -2102752405219316621L;
  private Long skuId;
  private Long prodId;
  private String properties;
  private Float price;
  private Long stocks;
  private Long actualStocks;
  private String name;
  private Short status;
  private Date skuDeliveryTime;
  private String outerId;
  private Date modifyDate;
  private Date recDate;

  public Long getSkuId()
  {
    return this.skuId;
  }

  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  public Long getProdId()
  {
    return this.prodId;
  }

  public void setProdId(Long prodId) {
    this.prodId = prodId;
  }

  public String getProperties()
  {
    return this.properties;
  }

  public void setProperties(String properties) {
    this.properties = properties;
  }

  public Float getPrice()
  {
    return this.price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Long getStocks()
  {
    return this.stocks;
  }

  public void setStocks(Long stocks) {
    this.stocks = stocks;
  }

  public Long getActualStocks()
  {
    return this.actualStocks;
  }

  public void setActualStocks(Long actualStocks) {
    this.actualStocks = actualStocks;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Short getStatus()
  {
    return this.status;
  }

  public void setStatus(Short status) {
    this.status = status;
  }

  public Date getSkuDeliveryTime()
  {
    return this.skuDeliveryTime;
  }

  public void setSkuDeliveryTime(Date skuDeliveryTime) {
    this.skuDeliveryTime = skuDeliveryTime;
  }

  public String getOuterId()
  {
    return this.outerId;
  }

  public void setOuterId(String outerId) {
    this.outerId = outerId;
  }

  public Date getModifyDate()
  {
    return this.modifyDate;
  }

  public void setModifyDate(Date modifyDate) {
    this.modifyDate = modifyDate;
  }

  public Date getRecDate()
  {
    return this.recDate;
  }

  public void setRecDate(Date recDate) {
    this.recDate = recDate;
  }

  public Long getId()
  {
    return this.skuId;
  }
}