package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

public class GroupProduct extends AbstractEntity
  implements BaseEntity
{
  private static final long serialVersionUID = 5542789588379673036L;
  private Product product;
  private Long prodId;
  private Long partnerId;
  private String successCause;
  private String buyCondition;
  private Integer visualBuys;
  private Integer maxBuys;
  private Long dvyTypeId;
  private Double maxMoney;
  private Date couponStartTime;
  private Date couponEndTime;
  private Boolean soldOut;
  private Integer salesMin;
  private Integer salesNum;
  private Integer salesMax;
  private Boolean isPost;

  public GroupProduct()
  {
  }

  public GroupProduct(String name, Long productId)
  {
    this.product = new Product();
    this.product.setName(name);
  }

  public Long getPartnerId()
  {
    return this.partnerId;
  }

  public void setPartnerId(Long partnerId)
  {
    this.partnerId = partnerId;
  }

  public String getSuccessCause()
  {
    return this.successCause;
  }

  public void setSuccessCause(String successCause)
  {
    this.successCause = successCause;
  }

  public String getBuyCondition()
  {
    return this.buyCondition;
  }

  public void setBuyCondition(String buyCondition)
  {
    this.buyCondition = buyCondition;
  }

  public Integer getVisualBuys()
  {
    return this.visualBuys;
  }

  public void setVisualBuys(Integer visualBuys)
  {
    this.visualBuys = visualBuys;
  }

  public Integer getMaxBuys()
  {
    return this.maxBuys;
  }

  public void setMaxBuys(Integer maxBuys)
  {
    this.maxBuys = maxBuys;
  }

  public Long getDvyTypeId()
  {
    return this.dvyTypeId;
  }

  public void setDvyTypeId(Long dvyTypeId)
  {
    this.dvyTypeId = dvyTypeId;
  }

  public Double getMaxMoney()
  {
    return this.maxMoney;
  }

  public void setMaxMoney(Double maxMoney)
  {
    this.maxMoney = maxMoney;
  }

  public Date getCouponStartTime()
  {
    return this.couponStartTime;
  }

  public void setCouponStartTime(Date couponStartTime)
  {
    this.couponStartTime = couponStartTime;
  }

  public Date getCouponEndTime()
  {
    return this.couponEndTime;
  }

  public void setCouponEndTime(Date couponEndTime)
  {
    this.couponEndTime = couponEndTime;
  }

  public Boolean getSoldOut()
  {
    return this.soldOut;
  }

  public void setSoldOut(Boolean soldOut)
  {
    this.soldOut = soldOut;
  }

  public Integer getSalesMin()
  {
    return this.salesMin;
  }

  public void setSalesMin(Integer salesMin)
  {
    this.salesMin = salesMin;
  }

  public Integer getSalesNum()
  {
    return this.salesNum;
  }

  public void setSalesNum(Integer salesNum)
  {
    this.salesNum = salesNum;
  }

  public Integer getSalesMax()
  {
    return this.salesMax;
  }

  public void setSalesMax(Integer salesMax)
  {
    this.salesMax = salesMax;
  }

  public Long getProdId()
  {
    return this.prodId;
  }

  public void setProdId(Long prodId)
  {
    this.prodId = prodId;
  }

  public Product getProduct() {
    return this.product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public void setPost(boolean isPost) {
    this.isPost = Boolean.valueOf(isPost);
  }

  public Boolean getIsPost()
  {
    return this.isPost;
  }

  public void setIsPost(Boolean isPost)
  {
    this.isPost = isPost;
  }

  public Serializable getId() {
    return this.prodId;
  }

  public String getUserName()
  {
    if (this.product == null)
      return null;

    return this.product.getUserName();
  }

  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    Product prod = getProduct();
    if (prod != null)
      sb.append(" [prodId=").append(this.prodId).append(", partnerId=").append(this.partnerId).append(", isPost=").append(this.isPost).append(", name=").append(prod.getName()).append(", userName=").append(prod.getUserName()).append("] ");
    else {
      sb.append(" [prodId=").append(this.prodId).append(", partnerId=").append(this.partnerId).append(", isPost=").append(this.isPost).append("] ");
    }

    return sb.toString();
  }
}