package com.legendshop.spi.form;

import java.util.Date;

public class MemberForm
{
  private static final long serialVersionUID = -3445526260441415877L;
  private Double total;
  private Long payType;
  private String basketId;
  private String other;
  private Integer userId;
  private String userName;
  private String orderName;
  private String userPass;
  private String userMail;
  private String userAdds;
  private String payTypeName;
  private String userTel;
  private Date userRegtime;
  private String userRegip;
  private Date userLasttime;
  private String userLastip;
  private String userBuymoney;
  private String userPostcode;
  private String userNamec;
  private String userType;

  public String getUserAdds()
  {
    return this.userAdds;
  }

  public void setUserAdds(String userAdds)
  {
    this.userAdds = userAdds;
  }

  public String getUserBuymoney()
  {
    return this.userBuymoney;
  }

  public void setUserBuymoney(String userBuymoney)
  {
    this.userBuymoney = userBuymoney;
  }

  public Integer getUserId()
  {
    return this.userId;
  }

  public void setUserId(Integer userId)
  {
    this.userId = userId;
  }

  public String getUserLastip()
  {
    return this.userLastip;
  }

  public void setUserLastip(String userLastip)
  {
    this.userLastip = userLastip;
  }

  public Date getUserLasttime()
  {
    return this.userLasttime;
  }

  public void setUserLasttime(Date userLasttime)
  {
    this.userLasttime = userLasttime;
  }

  public String getUserMail()
  {
    return this.userMail;
  }

  public void setUserMail(String userMail)
  {
    this.userMail = userMail;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getUserNamec()
  {
    return this.userNamec;
  }

  public void setUserNamec(String userNamec)
  {
    this.userNamec = userNamec;
  }

  public String getUserPass()
  {
    return this.userPass;
  }

  public void setUserPass(String userPass)
  {
    this.userPass = userPass;
  }

  public String getUserPostcode()
  {
    return this.userPostcode;
  }

  public void setUserPostcode(String userPostcode)
  {
    this.userPostcode = userPostcode;
  }

  public String getUserRegip()
  {
    return this.userRegip;
  }

  public void setUserRegip(String userRegip)
  {
    this.userRegip = userRegip;
  }

  public Date getUserRegtime()
  {
    return this.userRegtime;
  }

  public void setUserRegtime(Date userRegtime)
  {
    this.userRegtime = userRegtime;
  }

  public String getUserTel()
  {
    return this.userTel;
  }

  public void setUserTel(String userTel)
  {
    this.userTel = userTel;
  }

  public String getUserType()
  {
    return this.userType;
  }

  public void setUserType(String userType)
  {
    this.userType = userType;
  }

  public Long getPayType()
  {
    return this.payType;
  }

  public void setPayType(Long payType)
  {
    this.payType = payType;
  }

  public String getOther()
  {
    return this.other;
  }

  public void setOther(String other)
  {
    this.other = other;
  }

  public String getBasketId()
  {
    return this.basketId;
  }

  public void setBasketId(String basketId)
  {
    this.basketId = basketId;
  }

  public String getOrderName()
  {
    return this.orderName;
  }

  public void setOrderName(String orderName)
  {
    this.orderName = orderName;
  }

  public Double getTotal()
  {
    return this.total;
  }

  public void setTotal(Double total)
  {
    this.total = total;
  }

  public String getPayTypeName()
  {
    return this.payTypeName;
  }

  public void setPayTypeName(String payTypeName)
  {
    this.payTypeName = payTypeName;
  }
}