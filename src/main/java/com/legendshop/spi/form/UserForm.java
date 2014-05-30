package com.legendshop.spi.form;

import com.legendshop.model.ErrorType;
import com.legendshop.model.ValidationMessage;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.util.AppUtils;
import java.util.Date;

public class UserForm
{
  private static final long serialVersionUID = -4363234793127853507L;
  private String userId;
  private String name;
  private String password;
  private String passwordOld;
  private String enabled = "1";
  private String note;
  private Integer gradeId;
  private String userName;
  private String nickName;
  private String userMail;
  private String userAdds;
  private String userTel;
  private String userPostcode;
  private String msn;
  private String qq;
  private String fax;
  private Date modifyTime;
  private Date userRegtime;
  private String userRegip;
  private Date userLasttime;
  private String userLastip;
  private String userMemo;
  private String sex;
  private String birthDate;
  private String userMobile;
  private String userBirthYear;
  private String userBirthMonth;
  private String userBirthDay;
  private ShopDetail shopDetail = new ShopDetail();

  public String getEnabled()
  {
    return this.enabled;
  }

  public void setEnabled(String enabled)
  {
    this.enabled = enabled;
  }

  public String getFax()
  {
    return this.fax;
  }

  public void setFax(String fax)
  {
    this.fax = fax;
  }

  public Integer getGradeId()
  {
    return this.gradeId;
  }

  public void setGradeId(Integer gradeId)
  {
    this.gradeId = gradeId;
  }

  public Date getModifyTime()
  {
    return this.modifyTime;
  }

  public void setModifyTime(Date modifyTime)
  {
    this.modifyTime = modifyTime;
  }

  public String getMsn()
  {
    return this.msn;
  }

  public void setMsn(String msn)
  {
    this.msn = msn;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getNickName()
  {
    return this.nickName;
  }

  public void setNickName(String nickName)
  {
    this.nickName = nickName;
  }

  public String getNote()
  {
    return this.note;
  }

  public void setNote(String note)
  {
    this.note = note;
  }

  public String getPassword()
  {
    return this.password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getQq()
  {
    return this.qq;
  }

  public void setQq(String qq)
  {
    this.qq = qq;
  }

  public ShopDetail getShopDetail()
  {
    return this.shopDetail;
  }

  public void setShopDetail(ShopDetail shopDetail)
  {
    this.shopDetail = shopDetail;
  }

  public String getUserAdds()
  {
    return this.userAdds;
  }

  public void setUserAdds(String userAdds)
  {
    this.userAdds = userAdds;
  }

  public String getUserId()
  {
    return this.userId;
  }

  public void setUserId(String userId)
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

  public String getUserMemo()
  {
    return this.userMemo;
  }

  public void setUserMemo(String userMemo)
  {
    this.userMemo = userMemo;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
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

  public String getPasswordOld()
  {
    return this.passwordOld;
  }

  public void setPasswordOld(String passwordOld)
  {
    this.passwordOld = passwordOld;
  }

  public String getSex()
  {
    return this.sex;
  }

  public void setSex(String sex)
  {
    this.sex = sex;
  }

  public String getBirthDate()
  {
    return this.birthDate;
  }

  public void setBirthDate(String birthDate)
  {
    this.birthDate = birthDate;
  }

  public String getUserBirthYear()
  {
    return this.userBirthYear;
  }

  public void setUserBirthYear(String userBirthYear)
  {
    this.userBirthYear = userBirthYear;
  }

  public String getUserBirthMonth()
  {
    return this.userBirthMonth;
  }

  public void setUserBirthMonth(String userBirthMonth)
  {
    this.userBirthMonth = userBirthMonth;
  }

  public String getUserBirthDay()
  {
    return this.userBirthDay;
  }

  public void setUserBirthDay(String userBirthDay)
  {
    this.userBirthDay = userBirthDay;
  }

  public String getUserMobile()
  {
    return this.userMobile;
  }

  public void setUserMobile(String userMobile)
  {
    this.userMobile = userMobile;
  }

  public ValidationMessage validate() {
    ValidationMessage message = new ValidationMessage();
    if (AppUtils.isBlank(this.name))
      message.addError("name", ErrorType.NULL, "name can not be null");

    if (AppUtils.isBlank(this.password))
      message.addError("password", ErrorType.NULL, "password can not be null");

    return message;
  }
}