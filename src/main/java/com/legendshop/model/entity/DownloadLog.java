package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

public class DownloadLog
  implements BaseEntity
{
  private static final long serialVersionUID = 5134672215418217047L;
  private Long dlId;
  private String ip;
  private String country;
  private String area;
  private String userName;
  private String shopName;
  private String fileName;
  private Date date;

  public Long getDlId()
  {
    return this.dlId;
  }

  public void setDlId(Long dlId)
  {
    this.dlId = dlId;
  }

  public String getIp()
  {
    return this.ip;
  }

  public void setIp(String ip)
  {
    this.ip = ip;
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

  public String getFileName()
  {
    return this.fileName;
  }

  public void setFileName(String fileName)
  {
    this.fileName = fileName;
  }

  public Date getDate()
  {
    return this.date;
  }

  public void setDate(Date date)
  {
    this.date = date;
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

  public Serializable getId() {
    return this.dlId;
  }
}