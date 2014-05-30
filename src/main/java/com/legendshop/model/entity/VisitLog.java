package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

public class VisitLog
  implements BaseEntity
{
  private static final long serialVersionUID = -6138702495183168329L;
  private Long visitId;
  private String ip;
  private String country;
  private String area;
  private String userName;
  private String shopName;
  private String productName;
  private String productId;
  private String page;
  private Date date;
  private Integer visitNum;
  private Date startTime;
  private Date endTime;

  public Date getStartTime()
  {
    return this.startTime;
  }

  public void setStartTime(Date startTime)
  {
    this.startTime = startTime;
  }

  public Date getEndTime()
  {
    return this.endTime;
  }

  public void setEndTime(Date endTime)
  {
    this.endTime = endTime;
  }

  public VisitLog()
  {
  }

  public VisitLog(String ip, String page, Date date)
  {
    this.ip = ip;
    this.page = page;
    this.date = date;
  }

  public VisitLog(String ip, String country, String area, String userName, String shopName, String productName, String page, Date date)
  {
    this.ip = ip;
    this.country = country;
    this.area = area;
    this.userName = userName;
    this.shopName = shopName;
    this.productName = productName;
    this.page = page;
    this.date = date;
  }

  public Long getVisitId()
  {
    return this.visitId;
  }

  public void setVisitId(Long visitId)
  {
    this.visitId = visitId;
  }

  public String getIp()
  {
    return this.ip;
  }

  public void setIp(String ip)
  {
    this.ip = ip;
  }

  public String getCountry()
  {
    return this.country;
  }

  public void setCountry(String country)
  {
    this.country = country;
  }

  public String getArea()
  {
    return this.area;
  }

  public void setArea(String area)
  {
    this.area = area;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getShopName()
  {
    return this.shopName;
  }

  public void setShopName(String shopName)
  {
    this.shopName = shopName;
  }

  public String getProductName()
  {
    return this.productName;
  }

  public void setProductName(String productName)
  {
    this.productName = productName;
  }

  public String getPage()
  {
    return this.page;
  }

  public void setPage(String page)
  {
    this.page = page;
  }

  public Date getDate()
  {
    return this.date;
  }

  public void setDate(Date date)
  {
    this.date = date;
  }

  public String getProductId()
  {
    return this.productId;
  }

  public void setProductId(String productId)
  {
    this.productId = productId;
  }

  public Integer getVisitNum()
  {
    return this.visitNum;
  }

  public void setVisitNum(Integer visitNum)
  {
    this.visitNum = visitNum;
  }

  public Serializable getId() {
    return this.visitId;
  }
}