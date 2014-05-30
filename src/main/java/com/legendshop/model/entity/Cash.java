package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

public class Cash
  implements BaseEntity
{
  private Long cashId;
  private String userId;
  private String userName;
  private String code;
  private String shopId;
  private Long prodId;
  private String partnerId;
  private Long subId;
  private String detail;
  private Double money;
  private String status;
  private Date beginTime;
  private Date endTime;
  private String ip;
  private Date createTime;

  public Long getCashId()
  {
    return this.cashId;
  }

  public void setCashId(Long cashId)
  {
    this.cashId = cashId;
  }

  public String getUserId()
  {
    return this.userId;
  }

  public void setUserId(String userId)
  {
    this.userId = userId;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getCode()
  {
    return this.code;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  public String getShopId()
  {
    return this.shopId;
  }

  public void setShopId(String shopId)
  {
    this.shopId = shopId;
  }

  public Long getProdId()
  {
    return this.prodId;
  }

  public void setProdId(Long prodId)
  {
    this.prodId = prodId;
  }

  public String getPartnerId()
  {
    return this.partnerId;
  }

  public void setPartnerId(String partnerId)
  {
    this.partnerId = partnerId;
  }

  public Long getSubId()
  {
    return this.subId;
  }

  public void setSubId(Long subId)
  {
    this.subId = subId;
  }

  public String getDetail()
  {
    return this.detail;
  }

  public void setDetail(String detail)
  {
    this.detail = detail;
  }

  public Double getMoney()
  {
    return this.money;
  }

  public void setMoney(Double money)
  {
    this.money = money;
  }

  public String getStatus()
  {
    return this.status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public Date getBeginTime()
  {
    return this.beginTime;
  }

  public void setBeginTime(Date beginTime)
  {
    this.beginTime = beginTime;
  }

  public Date getEndTime()
  {
    return this.endTime;
  }

  public void setEndTime(Date endTime)
  {
    this.endTime = endTime;
  }

  public String getIp()
  {
    return this.ip;
  }

  public void setIp(String ip)
  {
    this.ip = ip;
  }

  public Date getCreateTime()
  {
    return this.createTime;
  }

  public void setCreateTime(Date createTime)
  {
    this.createTime = createTime;
  }

  public Serializable getId() {
    return this.cashId;
  }
}