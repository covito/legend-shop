package com.legendshop.model.entity;

public class MyPersonInfo
  implements BaseEntity
{
  private static final long serialVersionUID = -4386671445232853240L;
  private String id;
  private String nickName;
  private String userName;
  private String realName;
  private String userMail;
  private String userMobile;
  private String idCard;
  private String userAdds;
  private String birthDate;
  private String sex;
  private String qq;
  private String areaid;
  private String provinceid;
  private String cityid;

  public String getId()
  {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNickName() {
    return this.nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getRealName() {
    return this.realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public String getUserMail() {
    return this.userMail;
  }

  public void setUserMail(String userMail) {
    this.userMail = userMail;
  }

  public String getUserMobile() {
    return this.userMobile;
  }

  public void setUserMobile(String userMobile) {
    this.userMobile = userMobile;
  }

  public String getIdCard() {
    return this.idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  public String getUserAdds() {
    return this.userAdds;
  }

  public void setUserAdds(String userAdds) {
    this.userAdds = userAdds;
  }

  public String getBirthDate() {
    return this.birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  public String getSex() {
    return this.sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getQq() {
    return this.qq;
  }

  public void setQq(String qq) {
    this.qq = qq;
  }

  public String getAreaid() {
    return this.areaid;
  }

  public void setAreaid(String areaid) {
    this.areaid = areaid;
  }

  public String getProvinceid() {
    return this.provinceid;
  }

  public void setProvinceid(String provinceid) {
    this.provinceid = provinceid;
  }

  public String getCityid() {
    return this.cityid;
  }

  public void setCityid(String cityid) {
    this.cityid = cityid;
  }
}