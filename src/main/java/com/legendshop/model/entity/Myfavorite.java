package com.legendshop.model.entity;

import java.util.Date;

public class Myfavorite
  implements BaseEntity
{
  private static final long serialVersionUID = -4386671446122853240L;
  private String id;
  private Long prodId;
  private String prodName;
  private Date addtime;
  private String userId;
  private String userName;
  private String pic;
  private Integer comments;
  private Integer stocks;
  private Integer buys;
  private Double cash;
  private Double price;

  public Myfavorite(String id, String prodName, Double price, Date addtime)
  {
    this.id = id;
    this.prodName = prodName;
    this.addtime = addtime;
    this.price = price;
  }

  public Myfavorite(String id, Long prodId, String prodName, Double price, Date addtime, String pic, Integer comments, Integer stocks, Integer buys, Double cash) {
    this.id = id;
    this.prodId = prodId;
    this.prodName = prodName;
    this.addtime = addtime;
    this.price = price;
    this.pic = pic;
    this.comments = comments;
    this.stocks = stocks;
    this.buys = buys;
    this.cash = cash;
  }

  public Double getCash()
  {
    return this.cash;
  }

  public void setCash(Double cash) {
    this.cash = cash;
  }

  public Myfavorite()
  {
  }

  public Myfavorite(String id)
  {
    this.id = id;
  }

  public String getId()
  {
    return this.id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public Long getProdId()
  {
    return this.prodId;
  }

  public void setProdId(Long prodId)
  {
    this.prodId = prodId;
  }

  public String getProdName()
  {
    return this.prodName;
  }

  public void setProdName(String prodName)
  {
    this.prodName = prodName;
  }

  public Date getAddtime()
  {
    return this.addtime;
  }

  public void setAddtime(Date addtime)
  {
    this.addtime = addtime;
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

  public Double getPrice()
  {
    return this.price;
  }

  public void setPrice(Double price)
  {
    this.price = price;
  }

  public String getPic() {
    return this.pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public Integer getComments() {
    return this.comments;
  }

  public void setComments(Integer comments) {
    this.comments = comments;
  }

  public Integer getStocks() {
    return this.stocks;
  }

  public void setStocks(Integer stocks) {
    this.stocks = stocks;
  }

  public Integer getBuys() {
    return this.buys;
  }

  public void setBuys(Integer buys) {
    this.buys = buys;
  }
}