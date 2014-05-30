package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

public class Coupon
  implements BaseEntity
{
  private Long couponId;
  private String userId;
  private String userName;
  private Long shopId;
  private Long partnerId;
  private Long prodId;
  private Long subId;
  private String type;
  private Long score;
  private String secret;
  private String status;
  private String ip;
  private String smsStatus;
  private String smsContent;
  private Date expireTime;
  private Date consumeTime;
  private Date createTime;
  private Date smsTime;
  private Integer buyId;

  public Long getCouponId()
  {
    return this.couponId;
  }

  public void setCouponId(Long couponId)
  {
    this.couponId = couponId;
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

  public Long getShopId()
  {
    return this.shopId;
  }

  public void setShopId(Long shopId)
  {
    this.shopId = shopId;
  }

  public Long getPartnerId()
  {
    return this.partnerId;
  }

  public void setPartnerId(Long partnerId)
  {
    this.partnerId = partnerId;
  }

  public Long getProdId()
  {
    return this.prodId;
  }

  public void setProdId(Long prodId)
  {
    this.prodId = prodId;
  }

  public Long getSubId()
  {
    return this.subId;
  }

  public void setSubId(Long subId)
  {
    this.subId = subId;
  }

  public String getType()
  {
    return this.type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public Long getScore()
  {
    return this.score;
  }

  public void setScore(Long score)
  {
    this.score = score;
  }

  public String getSecret()
  {
    return this.secret;
  }

  public void setSecret(String secret)
  {
    this.secret = secret;
  }

  public String getStatus()
  {
    return this.status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public String getIp()
  {
    return this.ip;
  }

  public void setIp(String ip)
  {
    this.ip = ip;
  }

  public String getSmsStatus()
  {
    return this.smsStatus;
  }

  public void setSmsStatus(String smsStatus)
  {
    this.smsStatus = smsStatus;
  }

  public String getSmsContent()
  {
    return this.smsContent;
  }

  public void setSmsContent(String smsContent)
  {
    this.smsContent = smsContent;
  }

  public Date getExpireTime()
  {
    return this.expireTime;
  }

  public void setExpireTime(Date expireTime)
  {
    this.expireTime = expireTime;
  }

  public Date getConsumeTime()
  {
    return this.consumeTime;
  }

  public void setConsumeTime(Date consumeTime)
  {
    this.consumeTime = consumeTime;
  }

  public Date getCreateTime()
  {
    return this.createTime;
  }

  public void setCreateTime(Date createTime)
  {
    this.createTime = createTime;
  }

  public Date getSmsTime()
  {
    return this.smsTime;
  }

  public void setSmsTime(Date smsTime)
  {
    this.smsTime = smsTime;
  }

  public Integer getBuyId()
  {
    return this.buyId;
  }

  public void setBuyId(Integer buyId)
  {
    this.buyId = buyId;
  }

  public Serializable getId() {
    return this.couponId;
  }
}