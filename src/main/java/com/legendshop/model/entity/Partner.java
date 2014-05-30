package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

public class Partner
  implements BaseEntity
{
  private Long partnerId;
  private String partnerName;
  private String password;
  private String title;
  private String homepage;
  private String userId;
  private String userName;
  private Long shopId;
  private String bankName;
  private String bankNo;
  private String bankUser;
  private String location;
  private String contact;
  private String image;
  private String image1;
  private String image2;
  private String phone;
  private String address;
  private String other;
  private String mobile;
  private String showInfo;
  private String status;
  private String display;
  private Integer commentGood;
  private Integer commentNone;
  private Integer commentBad;
  private Date modifyTime;
  private Date createTime;
  protected MultipartFile imageFile;
  protected MultipartFile imageFile1;
  protected MultipartFile imageFile2;

  public MultipartFile getImageFile()
  {
    return this.imageFile;
  }

  public void setImageFile(MultipartFile imageFile) {
    this.imageFile = imageFile;
  }

  public MultipartFile getImageFile1() {
    return this.imageFile1;
  }

  public void setImageFile1(MultipartFile imageFile1) {
    this.imageFile1 = imageFile1;
  }

  public MultipartFile getImageFile2() {
    return this.imageFile2;
  }

  public void setImageFile2(MultipartFile imageFile2) {
    this.imageFile2 = imageFile2;
  }

  public Long getPartnerId()
  {
    return this.partnerId;
  }

  public void setPartnerId(Long partnerId)
  {
    this.partnerId = partnerId;
  }

  public String getPartnerName()
  {
    return this.partnerName;
  }

  public void setPartnerName(String partnerName)
  {
    this.partnerName = partnerName;
  }

  public String getPassword()
  {
    return this.password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getHomepage()
  {
    return this.homepage;
  }

  public void setHomepage(String homepage)
  {
    this.homepage = homepage;
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

  public String getBankName()
  {
    return this.bankName;
  }

  public void setBankName(String bankName)
  {
    this.bankName = bankName;
  }

  public String getBankNo()
  {
    return this.bankNo;
  }

  public void setBankNo(String bankNo)
  {
    this.bankNo = bankNo;
  }

  public String getBankUser()
  {
    return this.bankUser;
  }

  public void setBankUser(String bankUser)
  {
    this.bankUser = bankUser;
  }

  public String getLocation()
  {
    return this.location;
  }

  public void setLocation(String location)
  {
    this.location = location;
  }

  public String getContact()
  {
    return this.contact;
  }

  public void setContact(String contact)
  {
    this.contact = contact;
  }

  public String getImage()
  {
    return this.image;
  }

  public void setImage(String image)
  {
    this.image = image;
  }

  public String getImage1()
  {
    return this.image1;
  }

  public void setImage1(String image1)
  {
    this.image1 = image1;
  }

  public String getImage2()
  {
    return this.image2;
  }

  public void setImage2(String image2)
  {
    this.image2 = image2;
  }

  public String getPhone()
  {
    return this.phone;
  }

  public void setPhone(String phone)
  {
    this.phone = phone;
  }

  public String getAddress()
  {
    return this.address;
  }

  public void setAddress(String address)
  {
    this.address = address;
  }

  public String getOther()
  {
    return this.other;
  }

  public void setOther(String other)
  {
    this.other = other;
  }

  public String getMobile()
  {
    return this.mobile;
  }

  public void setMobile(String mobile)
  {
    this.mobile = mobile;
  }

  public String getStatus()
  {
    return this.status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public String getDisplay()
  {
    return this.display;
  }

  public void setDisplay(String display)
  {
    this.display = display;
  }

  public Integer getCommentGood()
  {
    return this.commentGood;
  }

  public void setCommentGood(Integer commentGood)
  {
    this.commentGood = commentGood;
  }

  public Integer getCommentNone()
  {
    return this.commentNone;
  }

  public void setCommentNone(Integer commentNone)
  {
    this.commentNone = commentNone;
  }

  public Integer getCommentBad()
  {
    return this.commentBad;
  }

  public void setCommentBad(Integer commentBad)
  {
    this.commentBad = commentBad;
  }

  public Date getModifyTime()
  {
    return this.modifyTime;
  }

  public void setModifyTime(Date modifyTime)
  {
    this.modifyTime = modifyTime;
  }

  public Date getCreateTime()
  {
    return this.createTime;
  }

  public void setCreateTime(Date createTime)
  {
    this.createTime = createTime;
  }

  public Serializable getId() {
    return this.partnerId;
  }

  public String getShowInfo() {
    return this.showInfo;
  }

  public void setShowInfo(String showInfo) {
    this.showInfo = showInfo;
  }
}