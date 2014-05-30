package com.legendshop.model;

import com.legendshop.model.entity.Basket;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubListForm
  implements Serializable
{
  private static final long serialVersionUID = 7550068509283340968L;
  private Long subId;
  private String userName;
  private String orderName;
  private Date subDate;
  private String subNumber;
  private String subCheck;
  private String subType;
  private Double subTotal;
  private Long payId;
  private String payTypeId;
  private Integer status;
  private String payTypeName;
  private String shopName;
  private List<Basket> basketList;
  private Long basketId;
  private Double total;
  private Double cash;
  private Double carriage;
  private Long prodId;
  private String pic;
  private Integer basketCount;
  private String prodName;
  private String attribute;

  public Long getSubId()
  {
    return this.subId;
  }

  public void setSubId(Long subId) {
    this.subId = subId;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getOrderName() {
    return this.orderName;
  }

  public void setOrderName(String orderName) {
    this.orderName = orderName;
  }

  public Date getSubDate() {
    return this.subDate;
  }

  public void setSubDate(Date subDate) {
    this.subDate = subDate;
  }

  public String getSubNumber() {
    return this.subNumber;
  }

  public void setSubNumber(String subNumber) {
    this.subNumber = subNumber;
  }

  public String getSubCheck() {
    return this.subCheck;
  }

  public void setSubCheck(String subCheck) {
    this.subCheck = subCheck;
  }

  public String getSubType() {
    return this.subType;
  }

  public void setSubType(String subType) {
    this.subType = subType;
  }

  public Double getTotal() {
    return this.total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public Long getPayId() {
    return this.payId;
  }

  public void setPayId(Long payId) {
    this.payId = payId;
  }

  public String getPayTypeId() {
    return this.payTypeId;
  }

  public void setPayTypeId(String payTypeId) {
    this.payTypeId = payTypeId;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getPayTypeName() {
    return this.payTypeName;
  }

  public void setPayTypeName(String payTypeName) {
    this.payTypeName = payTypeName;
  }

  public String getShopName() {
    return this.shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public Double getSubTotal() {
    return this.subTotal;
  }

  public void setSubTotal(Double subTotal) {
    this.subTotal = subTotal;
  }

  public Double getCarriage() {
    return this.carriage;
  }

  public void setCarriage(Double carriage) {
    this.carriage = carriage;
  }

  public Long getProdId() {
    return this.prodId;
  }

  public void setProdId(Long prodId) {
    this.prodId = prodId;
  }

  public String getPic() {
    return this.pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public Integer getBasketCount() {
    return this.basketCount;
  }

  public void setBasketCount(Integer basketCount) {
    this.basketCount = basketCount;
  }

  public String getProdName() {
    return this.prodName;
  }

  public void setProdName(String prodName) {
    this.prodName = prodName;
  }

  public Double getCash() {
    return this.cash;
  }

  public void setCash(Double cash) {
    this.cash = cash;
  }

  public Long getBasketId() {
    return this.basketId;
  }

  public void setBasketId(Long basketId) {
    this.basketId = basketId;
  }

  public List<Basket> getBasketList() {
    return this.basketList;
  }

  public void setBasketList(List<Basket> basketList) {
    this.basketList = basketList;
  }

  public void addBasket(Basket basket)
  {
    if (this.basketList == null)
      this.basketList = new ArrayList();

    this.basketList.add(basket);
  }

  public SubListForm clone()
  {
    SubListForm sub = new SubListForm();
    sub.setSubId(this.subId);
    sub.setUserName(this.userName);
    sub.setSubTotal(this.subTotal);
    sub.setOrderName(this.orderName);
    sub.setSubDate(this.subDate);
    sub.setSubNumber(this.subNumber);
    sub.setSubCheck(this.subCheck);
    sub.setSubType(this.subType);
    sub.setPayId(this.payId);
    sub.setPayTypeId(this.payTypeId);
    sub.setStatus(this.status);
    sub.setPayTypeName(this.payTypeName);
    sub.setShopName(this.shopName);
    return sub;
  }

  public String getAttribute() {
    return this.attribute;
  }

  public void setAttribute(String attribute) {
    this.attribute = attribute;
  }
}