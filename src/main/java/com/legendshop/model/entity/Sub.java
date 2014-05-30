package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Sub extends AbstractEntity
  implements BaseEntity
{
  private static final long serialVersionUID = -4030702631697447802L;
  private Long subId;
  private String prodName;
  private String userName;
  private String orderName;
  private Date subDate;
  private Date payDate;
  private Date updateDate;
  private String subNumber;
  private String subCheck;
  private String subType;
  private Double total;
  private Double actualTotal;
  private String subMail;
  private String subTel;
  private Long payId;
  private String payTypeId;
  private Integer status;
  private String payTypeName;
  private String subAdds;
  private String subPost;
  private String other;
  private String shopName;
  private List<Basket> basket;
  private List<PayType> payType;
  private Long scoreId;
  private Long score;
  private Long dvyTypeId;
  private String deliveryFlowId;
  private String tradeNo;

  public Long getSubId()
  {
    return this.subId;
  }

  public void setSubId(Long subId)
  {
    this.subId = subId;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getOrderName()
  {
    return this.orderName;
  }

  public void setOrderName(String orderName)
  {
    this.orderName = orderName;
  }

  public Date getSubDate()
  {
    return this.subDate;
  }

  public void setSubDate(Date subDate)
  {
    this.subDate = subDate;
  }

  public String getSubNumber()
  {
    return this.subNumber;
  }

  public void setSubNumber(String subNumber)
  {
    this.subNumber = subNumber;
  }

  public String getSubCheck()
  {
    return this.subCheck;
  }

  public void setSubCheck(String subCheck)
  {
    this.subCheck = subCheck;
  }

  public Double getTotal()
  {
    return this.total;
  }

  public void setTotal(Double total)
  {
    this.total = total;
  }

  public String getSubMail()
  {
    return this.subMail;
  }

  public void setSubMail(String subMail)
  {
    this.subMail = subMail;
  }

  public String getSubTel()
  {
    return this.subTel;
  }

  public void setSubTel(String subTel)
  {
    this.subTel = subTel;
  }

  public Long getPayId()
  {
    return this.payId;
  }

  public void setPayId(Long payId)
  {
    this.payId = payId;
  }

  public String getSubAdds()
  {
    return this.subAdds;
  }

  public void setSubAdds(String subAdds)
  {
    this.subAdds = subAdds;
  }

  public String getSubPost()
  {
    return this.subPost;
  }

  public void setSubPost(String subPost)
  {
    this.subPost = subPost;
  }

  public String getOther()
  {
    return this.other;
  }

  public void setOther(String other)
  {
    this.other = other;
  }

  public String getShopName()
  {
    return this.shopName;
  }

  public void setShopName(String shopName)
  {
    this.shopName = shopName;
  }

  public List<Basket> getBasket()
  {
    return this.basket;
  }

  public void setBasket(List<Basket> basket)
  {
    this.basket = basket;
  }

  public String getPayTypeName()
  {
    return this.payTypeName;
  }

  public void setPayTypeName(String payTypeName)
  {
    this.payTypeName = payTypeName;
  }

  public Integer getStatus()
  {
    return this.status;
  }

  public void setStatus(Integer status)
  {
    this.status = status;
  }

  public List<PayType> getPayType()
  {
    return this.payType;
  }

  public void setPayType(List<PayType> payType)
  {
    this.payType = payType;
  }

  public Date getPayDate()
  {
    return this.payDate;
  }

  public void setPayDate(Date payDate)
  {
    this.payDate = payDate;
  }

  public String getProdName()
  {
    return this.prodName;
  }

  public void setProdName(String prodName)
  {
    this.prodName = prodName;
  }

  public Date getUpdateDate()
  {
    return this.updateDate;
  }

  public void setUpdateDate(Date updateDate)
  {
    this.updateDate = updateDate;
  }

  public String getPayTypeId()
  {
    return this.payTypeId;
  }

  public void setPayTypeId(String payTypeId)
  {
    this.payTypeId = payTypeId;
  }

  public Long getScoreId()
  {
    return this.scoreId;
  }

  public void setScoreId(Long scoreId)
  {
    this.scoreId = scoreId;
  }

  public Long getScore()
  {
    return this.score;
  }

  public void setScore(Long score)
  {
    this.score = score;
  }

  public Double getActualTotal()
  {
    return this.actualTotal;
  }

  public void setActualTotal(Double actualTotal)
  {
    this.actualTotal = actualTotal;
  }

  public String getTradeNo()
  {
    return this.tradeNo;
  }

  public void setTradeNo(String tradeNo)
  {
    this.tradeNo = tradeNo;
  }

  public String getSubType()
  {
    return this.subType;
  }

  public void setSubType(String subType)
  {
    this.subType = subType;
  }

  public Long getDvyTypeId()
  {
    return this.dvyTypeId;
  }

  public void setDvyTypeId(Long dvyTypeId)
  {
    this.dvyTypeId = dvyTypeId;
  }

  public String getDeliveryFlowId()
  {
    return this.deliveryFlowId;
  }

  public void setDeliveryFlowId(String deliveryFlowId)
  {
    this.deliveryFlowId = deliveryFlowId;
  }

  public Serializable getId() {
    return this.subId;
  }
}