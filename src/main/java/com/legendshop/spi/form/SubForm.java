package com.legendshop.spi.form;

public class SubForm
{
  private static final long serialVersionUID = 2769000419985544497L;
  private String userName;
  private String shopName;
  private String subNumber;
  private Integer status;
  private String subCheck = "N";
  private String curPageNO = "1";

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

  public String getSubNumber()
  {
    return this.subNumber;
  }

  public void setSubNumber(String subNumber)
  {
    this.subNumber = subNumber;
  }

  public Integer getStatus()
  {
    return this.status;
  }

  public void setStatus(Integer status)
  {
    this.status = status;
  }

  public String getSubCheck()
  {
    return this.subCheck;
  }

  public void setSubCheck(String subCheck)
  {
    this.subCheck = subCheck;
  }

  public String getCurPageNO()
  {
    return this.curPageNO;
  }

  public void setCurPageNO(String curPageNO)
  {
    this.curPageNO = curPageNO;
  }
}