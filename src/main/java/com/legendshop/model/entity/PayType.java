package com.legendshop.model.entity;

import java.io.Serializable;

public class PayType extends AbstractEntity
  implements BaseEntity
{
  private static final long serialVersionUID = 173116392190218430L;
  private Long payId;
  private String userName;
  private String payTypeId;
  private String payTypeName;
  private String partner;
  private String validateKey;
  private String sellerEmail;
  private String memo;

  public PayType()
  {
  }

  public PayType(Long payId, String userName, String payTypeId)
  {
    this.payId = payId;
    this.userName = userName;
    this.payTypeId = payTypeId;
  }

  public PayType(Long payId, String userName, String payTypeId, String payTypeName, String partner, String validateKey, String sellerEmail, String memo)
  {
    this.payId = payId;
    this.userName = userName;
    this.payTypeId = payTypeId;
    this.payTypeName = payTypeName;
    this.partner = partner;
    this.validateKey = validateKey;
    this.sellerEmail = sellerEmail;
    this.memo = memo;
  }

  public Long getPayId()
  {
    return this.payId;
  }

  public void setPayId(Long payId)
  {
    this.payId = payId;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getPayTypeId()
  {
    return this.payTypeId;
  }

  public void setPayTypeId(String payTypeId)
  {
    this.payTypeId = payTypeId;
  }

  public String getPayTypeName()
  {
    return this.payTypeName;
  }

  public void setPayTypeName(String payTypeName)
  {
    this.payTypeName = payTypeName;
  }

  public String getPartner()
  {
    return this.partner;
  }

  public void setPartner(String partner)
  {
    this.partner = partner;
  }

  public String getValidateKey()
  {
    return this.validateKey;
  }

  public void setValidateKey(String validateKey)
  {
    this.validateKey = validateKey;
  }

  public String getSellerEmail()
  {
    return this.sellerEmail;
  }

  public void setSellerEmail(String sellerEmail)
  {
    this.sellerEmail = sellerEmail;
  }

  public String getMemo()
  {
    return this.memo;
  }

  public void setMemo(String memo)
  {
    this.memo = memo;
  }

  public Serializable getId() {
    return this.payId;
  }
}